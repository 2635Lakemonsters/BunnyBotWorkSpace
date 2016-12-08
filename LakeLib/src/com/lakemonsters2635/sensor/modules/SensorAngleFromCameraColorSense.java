package com.lakemonsters2635.sensor.modules;

import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


import com.lakemonsters2635.sensor.interfaces.BaseSensor;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorAngleFromCameraColorSense extends BaseSensor<Double>
{
	Image colorFrame;
	Image binaryFrame;
	NIVision.Range HUE_RANGE; //= new NIVision.Range(0, 5);	//Default hue range for yellow tote
	NIVision.Range SAT_RANGE; //= new NIVision.Range(0, 10);	//Default saturation range for yellow tote
	NIVision.Range VAL_RANGE; //= new NIVision.Range(250, 255);	//Default value range for yellow tote
	Double AREA_MINIMUM;
	Double VIEW_ANGLE;
	int CAMERA_RESOLUTION_X;
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	int session;
	Timer visionTimer;
	private Double angleToTargetX = 0.0;
	boolean reportToSmartDashboard;
	boolean enabled;
	CameraServer camera = CameraServer.getInstance();
	/**
	 * 
	 * @param hueRange Range to filter hues
	 * @param saturationRange Range to filter saturation
	 * @param valueRange Range to filter value
	 * @param particleAreaMinimum Minimum area for a particle to not get filtered out
	 * @param cameraViewAngle View angle of the camera
	 * @param cameraResolutionX Amount of pixels in the X direction of the camera. Resolution looks like XxY on the Smart Dashboard.
	 * @param session Camera session
	 * @param reportToSmartDashboard If true, the camera image and angle information will be sent to the Smart Dashboard
	 * @param processingPeriod Amount of time between processes of vision
	 */
	public SensorAngleFromCameraColorSense(Range hueRange, Range saturationRange, Range valueRange, Double particleAreaMinimum,
			Double cameraViewAngle, int cameraResolutionX, int session, boolean reportToSmartDashboard, long processingPeriod)
	{
		super();
		HUE_RANGE = hueRange;
		SAT_RANGE = saturationRange;
		VAL_RANGE = valueRange;
		AREA_MINIMUM = particleAreaMinimum;
		VIEW_ANGLE = cameraViewAngle;
		CAMERA_RESOLUTION_X = cameraResolutionX;		
		this.session = session;
		this.reportToSmartDashboard = reportToSmartDashboard;
        colorFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);

		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
		NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
        enabled = false;
        visionTimer = new Timer();
        //Vision will process every processingPeriod
        visionTimer.schedule(new VisionTask(this), 0L, processingPeriod);
        
        
	}
	/**
	 * 
	 * @param hueRange Range to filter hues
	 * @param saturationRange Range to filter saturation
	 * @param valueRange Range to filter value
	 * @param particleAreaMinimum Minimum area for a particle to not get filtered out
	 * @param cameraViewAngle View angle of the camera
	 * @param cameraResolutionX Amount of pixels in the X direction of the camera. Resolution looks like XxY on the Smart Dashboard.
	 * @param session Camera session
	 * @param sendToSmartDashboard If true, the camera image and angle information will be sent to the Smart Dashboard
	 * 
	 */
	public SensorAngleFromCameraColorSense(Range hueRange, Range saturationRange, Range valueRange, Double particleAreaMinimum,
			Double cameraViewAngle, int cameraResolutionX, int session, boolean sendToSmartDashboard)
	{
		this(hueRange, saturationRange, valueRange, particleAreaMinimum, cameraViewAngle, cameraResolutionX, session, sendToSmartDashboard, 20L);
	}
	

	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>
	{
		double Area;
		double AreaByImageArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectWidth;
		double BoundingRectHeight;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.AreaByImageArea - this.AreaByImageArea);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.AreaByImageArea - r2.AreaByImageArea);
		}
	};
	private class VisionTask extends TimerTask
	{
		SensorAngleFromCameraColorSense visionSensor;
		public VisionTask(SensorAngleFromCameraColorSense visionSensor)
		{
			this.visionSensor = visionSensor;
		}
		@Override
		public void run()
		{
		
			visionSensor.vision();	
		}
		
	}
	
	@Override
	public synchronized Double sense(Object unused) 
	{
		return angleToTargetX;
	}
    public double pixelCoordnateToAimingCoordnate(double pixelCoordnate, double resolution)
    {
    	//Shift pixelCoordnate to the right by half the resolution, effectively transforming the plane of the coordnate from 0 on the lefthand side to -resolution/2 on the lefthand side
    	//Divide by half the resolution to get the ratio between the coordnate position and its distance to 0.
    	return (pixelCoordnate - resolution / 2.0) / (resolution / 2.0);
    }
    public double aimingCoordnateToAngle(double aimingCoordnate, double viewingAngle)
    {
    	//Aiming coordnate is a ratio between the middle and edge distances. Multiplied by the viewing angle/2 gives the ratio between the middle and edge of the view
    	return aimingCoordnate * viewingAngle/2.0;
    }
   
    public synchronized void enable()
    {
    	enabled = true;
    }
    
    public synchronized void disable()
    {
    	enabled = false;
    }
    public void vision()
    {
    	boolean processingEnabled;
    	synchronized(this)
    	{
    		processingEnabled = enabled;
    	}
    	NIVision.IMAQdxGrab(session, colorFrame, 1);
    	if(processingEnabled)
    	{
	     						
	        //Look at the color frame for colors that fit the range. Colors that fit the range will be transposed as a 1 to the binary frame.
			NIVision.imaqColorThreshold(binaryFrame, colorFrame, 255, ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);
			//Filter out particles that are too small
	        NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
			
	        int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);        
			if(numParticles > 0)
			{
				synchronized(this)
			    {
					//Measure particles and sort by particle size
					Vector<ParticleReport> particles = new Vector<ParticleReport>();
					for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
					{
						ParticleReport par = new ParticleReport();
						par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
						par.AreaByImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
						par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
						par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
						par.BoundingRectHeight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT);
						par.BoundingRectWidth = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
						particles.add(par);
		
					}
					particles.sort(null);
					
					//Assume that the largest particle is the best particle
					ParticleReport bestParticle = particles.elementAt(0);
					if(reportToSmartDashboard)
					{
						NIVision.Rect particleRect = new NIVision.Rect((int)bestParticle.BoundingRectTop, (int)bestParticle.BoundingRectLeft, (int)bestParticle.BoundingRectHeight, (int)bestParticle.BoundingRectWidth);		    
				    	NIVision.imaqDrawShapeOnImage(colorFrame, colorFrame, particleRect, NIVision.DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
					}
				    //Calculate angle to center of target
					double bestParticleMidpoint = bestParticle.BoundingRectLeft + bestParticle.BoundingRectWidth/2.0;
				    double bestParticleMidpointAimingCoordnates = pixelCoordnateToAimingCoordnate(bestParticleMidpoint, CAMERA_RESOLUTION_X);			    
			    	angleToTargetX = aimingCoordnateToAngle(bestParticleMidpointAimingCoordnates, VIEW_ANGLE);
			    }
			}
			else
			{
				synchronized(this)
				{
					angleToTargetX = 0.0;
				}
			}
	

    	}
        if(reportToSmartDashboard)
        {
        	camera.setImage(colorFrame);
        }
    }
	@Override
	public void setPIDSourceType(PIDSourceType pidSource)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public PIDSourceType getPIDSourceType()
	{
		return PIDSourceType.kDisplacement;
	}
	@Override
	public double pidGet()
	{
		return sense(null);
	}

	
    
}
