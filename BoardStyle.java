import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Shape;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	BoardStyle
*  File: 	BoardStyle.java
*  Description:	Provides an outline for list of methods that need to be
*  in implementing classes
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public interface BoardStyle {
	
	public Component[] getPits(); 
	public Color[] getColors(); 

}
