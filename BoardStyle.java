/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	BoardStyle
*  File: 	BoardStyle.java
*  Description:	Provides outline for the methods required by concrete styles 
*  Date:	5/2/2016
*  @author  Team we.excelAt(ood)
*  @version	1 
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Shape;


public interface BoardStyle {

	public Pit[] getPits();
	public Color[] getColors();

}
