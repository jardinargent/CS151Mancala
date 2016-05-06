import java.awt.Color;
import java.awt.Shape;

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
	private int xLoc;
	private int yLoc;

	private Shape[] pitArray;
	private Color[] playerColors;

	public Style2() {
		player1 = new Color(51, 173, 255);
		player2 = new Color(255, 255, 102);

		playerColors = new Color[2];
		playerColors[0] = player1;
		playerColors[1] = player2;


		designPit();

	}

	@Override
	public void designPit() {

	}

	public String toString() {
		return "This is the style2 object!";
	}

}
