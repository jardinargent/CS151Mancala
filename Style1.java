/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Style1
*  File: 	Style1.java
*  Description:	Creates Board with Circular Mancalas and Rectangular Pits 
*  Date:	5/2/2016
*  @author  Team we.excelAt(ood)
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.awt.Color;
import java.awt.Component;

public class Style1 implements BoardStyle {
	//rectangle pit
	private Color player1;
	private Color player2;

	private Pit[] pitArray; // 0-6 player1, 7-13 player2, 6 & 13 scoring
	private Color[] playerColors;

	/**
	 * Constructor initializes style with Circular mancalas and 
	 * Rectangular pits 
	 */
	public Style1() {
		player1 = new Color(255, 80, 80);
		player2 = new Color(38, 38, 38);

		playerColors = new Color[2];
		playerColors[0] = player1;
		playerColors[1] = player2;

		pitArray = new Pit[14];

		for (int i = 0; i < 14; i++) {
			if (i < 6){
				pitArray[i] = new RectanglePit(i, 100, player1, player2, 1);
				pitArray[i].isPlaying(true);
			}
			else if (i >= 7 && i != 13){
				pitArray[i] = new RectanglePit(i, 100, player2, player1,2);
				pitArray[i].isPlaying(false);
			}
			else if (i == 13){
				pitArray[i] = new CirclePit(i, 100, player2, player1,2);
				pitArray[i].isPlaying(false);
			}
			else if (i == 6){
				pitArray[i] = new CirclePit(i, 100, player1, player2,1);
				pitArray[i].isPlaying(true);
			}
		}
	}

	/**
	 * Returns string to indicate the style this is
	 * @return String returns whether this is style1 or style2
	 */
	public String toString() {
		return "Style 1 chosen: Circular Mancala | Rectangular Pit";
	}

	/**
	 * Returns the array of pits 
	 * @return array of all Pits on game board 
	 */
	@Override
	public Pit[] getPits() {
		return this.pitArray;
	}

	/**
	 * Returns the color array for this style
	 * @return color array
	 */
	@Override
	public Color[] getColors() {
		return this.playerColors;
	}

}
