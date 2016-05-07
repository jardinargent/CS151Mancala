import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JPanel;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Style2
*  File: 	Style2.java
*  Description:	Style2
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Style2 implements BoardStyle {
//circlePit and colors: blue, yellow
	private Color player1;
	private Color player2;
	
	private Component[] pitArray; // 0-5 player1, 6-11 player2
	private Color[] playerColors;

	public Style2() {
		player1 = new Color(51, 173, 255);
		player2 = new Color(255, 255, 102);
		
		playerColors = new Color[2];
		playerColors[0] = player1;
		playerColors[1] = player2;
		
		pitArray = new Component[14]; 

		for(int i = 0; i<14; i++){ 
			if(i == 0)
				pitArray[i] = new RectanglePit(i,100,player1); 
			else if(i <=6 && i!=0)
				pitArray[i] = new CirclePit(i,100,player1); 
			else if(i >=7 && i!=13)
				pitArray[i] = new CirclePit(i,100,player2);
			else if(i == 13)
				pitArray[i] = new RectanglePit(i,100,player2);
		}
	}
	public String toString() {
		return "This is the style2 object!";
	}
	
	@Override
	public Component[] getPits() {
		return this.pitArray;
	}
	
	public Color[] getColors(){
		return this.playerColors;
	}
}
