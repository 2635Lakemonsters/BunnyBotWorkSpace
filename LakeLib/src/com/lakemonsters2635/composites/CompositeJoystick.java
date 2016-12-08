package com.lakemonsters2635.composites;

import java.util.HashMap;

import com.lakemonsters2635.sensor.interfaces.IOperator;
import com.lakemonsters2635.sensor.interfaces.BaseSensor;

/**
 * A configurable joystick. Allows the user to build a joystick from a series of axes, buttons, and POVs. All of them may have transforms applied.
 * @author Tristan Thompson
 *
 */
public class CompositeJoystick
{
	HashMap<String, BaseSensor<Double>> axes;
	HashMap<String, IOperator<Double, Double>> axesTransform;
	
	HashMap<String, BaseSensor<Boolean>> buttons;
	HashMap<String, IOperator<Boolean,Boolean>> buttonsTransform;
	
	HashMap<String, BaseSensor<Integer>> POVs;
	HashMap<String, IOperator<Integer, Integer>> POVsTransform;
	public CompositeJoystick()
	{
		axes = new HashMap<String, BaseSensor<Double>>();
		axesTransform = new HashMap<String, IOperator<Double,Double>>();
		
		buttons = new HashMap<String, BaseSensor<Boolean>>();
		buttonsTransform = new HashMap<String, IOperator<Boolean,Boolean>>();
		
		POVs = new HashMap<String, BaseSensor<Integer>>();
		POVsTransform = new HashMap<String, IOperator<Integer,Integer>>();
		
	}
	/**
	 * Add an axis to the joystick. Axes are defined as anything that can be sensed as a Double value.
	 * 
	 * @param axisName Name of the axis
	 * @param axisSensor Thing that senses the axis
	 * @param axisTransform Operation applied on the sensed value
	 */
	public void addAxis(String axisName, BaseSensor<Double> axisSensor, IOperator<Double,Double> axisTransform)
	{
		axes.put(axisName, axisSensor);
		axesTransform.put(axisName, axisTransform);
	}
	public void addAxis(String axisName, BaseSensor<Double> axisSensor)
	{
		axes.put(axisName, axisSensor);
		axesTransform.put(axisName, null);
	}
	/**
	 * Add a button to the joystick. Buttons are defined as anything that can be sensed as a Boolean value.
	 * 
	 * @param buttonName Name of the button
	 * @param buttonSensor Thing that senses the button
	 * @param buttonTransform Operation applied on the sensed value
	 */
	public void addButton(String buttonName, BaseSensor<Boolean> buttonSensor, IOperator<Boolean,Boolean> buttonTransform)
	{
		buttons.put(buttonName, buttonSensor);
		buttonsTransform.put(buttonName, buttonTransform);
	}
	public void addButton(String buttonName, BaseSensor<Boolean> buttonSensor)
	{
		buttons.put(buttonName, buttonSensor);
		axesTransform.put(buttonName, null);
	}
	
	/**
	 * Add a POV to the joystick. POVs are defined as anything that can be sensed as an Integer value.
	 * 
	 * @param POVName Name of the POV
	 * @param POVSensor Thing that senses the POV
	 * @param POVTransform Operation applied on the sensed value
	 */
	public void addPOV(String POVName, BaseSensor<Integer> POVSensor, IOperator<Integer, Integer> POVTransform)
	{
		POVs.put(POVName, POVSensor);
		POVsTransform.put(POVName, POVTransform);
	}
	public void addPOV(String POVName, BaseSensor<Integer> POVSensor)
	{
		POVs.put(POVName, POVSensor);
		POVsTransform.put(POVName, null);
	}
	
	/**
	 * Returns the sensed axis after the operation has been applied to it. If no operation is specified then the raw value is returned.
	 * 
	 * @param axisName Name of the axis
	 * @param modifier Input to the axisTransform
	 * @return Transformed value
	 */
	public Double getAxis(String axisName, Double modifier)
	{
		Double axisValue = axes.get(axisName).sense(null);
		if(axesTransform.get(axisName) != null)
		{
			return axesTransform.get(axisName).operate(axisValue, modifier);
		}
		return axisValue;
	}
	/**
	 * Returns just the sensed value.
	 * @param axisName
	 * @return
	 */
	public Double getAxis(String axisName, Object sensorModifier)
	{
		
		return axes.get(axisName).sense(sensorModifier);
	}
	
	/**
	 * Returns the sensed button after the operation has been applied to it. If no operation is specified then the raw value is returned.
	 * 
	 * @param buttonName Name of the button
	 * @param modifier Input to the buttonTransform
	 * @return Transformed value
	 */
	public Boolean getButton(String buttonName, Boolean modifier, Object sensorModifier)
	{
		//Return the transformed value from sense
		Boolean buttonValue = buttons.get(buttonName).sense(sensorModifier);
		if(buttonsTransform.get(buttonName) != null)
		{
			return buttonsTransform.get(buttonName).operate(buttonValue, modifier);
		}
		return buttonValue;
	}
	public Boolean getButton(String buttonName, Object sensorModifier)
	{
		return buttons.get(buttonName).sense(sensorModifier);
	}
	/**
	 * Returns the sensed POV after the operation has been applied to it. If no operation is specified then the raw value is returned.
	 * 
	 * @param POVName Name of the POV
	 * @param modifier Input to the POVTransform
	 * @return Transformed value
	 */
	public Integer getPOV(String POVName, Integer modifier, Object sensorModifier)
	{
		Integer POVValue = POVs.get(POVName).sense(sensorModifier);
		if(POVsTransform.get(POVName) != null)
		{
			return POVsTransform.get(POVName).operate(POVValue, modifier);
		}
		return POVValue;	
	}
	public Integer getPOV(String POVName, Object sensorModifier)
	{
		return POVs.get(POVName).sense(sensorModifier);
	}
}

