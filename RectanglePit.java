/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	RectanglePit
*  File: 	RectanglePit.java
*  Description:	Creates Rectangle shaped Pits
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class RectanglePit extends JPanel implements Pit {

	private int pitNum;
	private int pitWidth;
	private Rectangle2D.Double pit;
	private Color pitColor;
	private Color oColor;
	private String pitName = "";
	private int stoneNum;
	private int playNum; 
	private boolean inPlay; 
	
	/**
	 * Constructor initializes a new Rectangular Pit based on the parameters
	 * 
	 * @param pitNum   Stores the number of the pit 
	 * @param pitWidth width of the pit 
	 * @param pitColor Color (changes based on style)
	 * @param oColor   second player's color
	 * @param player   player this pit belongs to 
	 */
	public RectanglePit(int pitNum, int pitWidth, Color pitColor, Color oColor, int player) {
		this.pitNum = pitNum;
		this.pitWidth = pitWidth;
		this.pitColor = pitColor;
		this.oColor = oColor;
		playNum = player;
		pit = new Rectangle2D.Double(0, 0, pitWidth, pitWidth);
	}


	/**
	 * Overrides PaintComponent of the JLabel class to paint a mancala pit
	 * with labels and number of stones/points
	 *  
	 * @param g  Graphics object to paint
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		if(inPlay)
			g2D.setColor(pitColor);
		else 
			g2D.setColor(Color.GRAY);
		
		g2D.fill(pit);
		g2D.setColor(oColor);
		g2D.setStroke(new BasicStroke(5));

		if (pitNum == 6)
			pitName = "Mancala 1";

		else if (pitNum == 13)
			pitName = "Mancala 2";

		else {
			if (pitNum < 6)
				pitName = "A" + String.valueOf(pitNum + 1);
			else
				pitName = "B" + String.valueOf(pitNum - 6);

		}
		g2D.setColor(Color.LIGHT_GRAY);
		g2D.drawString(pitName, 25, 70);

		drawStoneCount(g2D);
	}

	
	private void drawStoneCount(Graphics2D g2D) {
		g2D.setColor(oColor);
		g2D.setFont(new Font("Calibri", Font.BOLD, 20));
		g2D.drawString(String.valueOf(this.stoneNum), 45, 45);
	}

	/**
	 * Update number of stones in pit
	 * 
	 * @param stone number of stones in pit
	 */
	public void updateStoneCount(int stone) {
		stoneNum = stone;
	}

	/**
	 * Returns the pitNum 
	 * @return int pitNum
	 */
	public int getPitNum() {
		return pitNum;
	}
	
	/**
	 * Sets whether the current Pit is in play 
	 * @param inPlay condition for whether the pit's player is 
	 * 		  currently playing 
	 */
	public void isPlaying(boolean inPlay){ 
		this.inPlay = inPlay; 
	}
	
	/**
	 * Returns the player number this pit belongs to 
	 * @return int returns either 1 or 2 for player
	 */
	public int player() {
		return playNum;
	}
}
