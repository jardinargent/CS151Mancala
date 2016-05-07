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

	private Pit[] pitArray; // 0-6 player1, 7-13 player2
	private Color[] playerColors;

	public Style2() {
		player1 = new Color(0, 172, 230);
		player2 = new Color(0, 51, 102);

		playerColors = new Color[2];
		playerColors[0] = player1;
		playerColors[1] = player2;

		pitArray = new Pit[14];

		for (int i = 0; i < 14; i++) {
			if (i < 6 && i != 6){
				pitArray[i] = new CirclePit(i, 100, player1, player2,1);
				pitArray[i].isPlaying(true);
			}
			else if (i >= 7 && i != 13){
				pitArray[i] = new CirclePit(i, 100, player2, player1,2);
				pitArray[i].isPlaying(false);
			}
			else if (i == 13){
				pitArray[i] = new RectanglePit(i, 100, player2, player1,2);
				pitArray[i].isPlaying(false);
			}
			else if (i == 6){
				pitArray[i] = new RectanglePit(i, 100, player1, player2,1);
				pitArray[i].isPlaying(true);
			}
		}
	}
	public String toString() {
		return "This is the style2 object!";
	}

	@Override
	public Pit[] getPits() {
		return this.pitArray;
	}

	public Color[] getColors() {
		return this.playerColors;
	}
}
