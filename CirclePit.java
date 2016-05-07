import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

class CirclePit extends JPanel implements Pit {
	private int pitNum;
	private int pitWidth;
	private Ellipse2D.Double pit;
	private Color pitColor;
	private Color oColor;
	private String pitName = "";
	private int stoneNum;
	private int playNum; 
	private boolean inPlay; 

	public CirclePit(int pitNum, int pitWidth, Color pitColor, Color oColor, int player) {
		this.pitNum = pitNum;
		this.pitWidth = pitWidth;
		this.pitColor = pitColor;
		this.oColor = oColor;
		playNum = player; 

		pit = new Ellipse2D.Double(0, 0, pitWidth, pitWidth);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		//System.out.println("Pit NUM: " + pitNum);
		g2D.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(inPlay)
			g2D.setColor(pitColor);
		else
			g2D.setColor(Color.GRAY);
		g2D.fill(pit);
		g2D.setStroke(new BasicStroke(1));
		

		if (pitNum == 6)
			pitName = "Mancala 1";

		else if (pitNum == 13)
			pitName = "Mancala 2";

		else {
			if (pitNum < 6)
				pitName = "A" + String.valueOf(7 - pitNum);
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

	public void updateStoneCount(int stone) {
		stoneNum = stone;
		//System.out.println("calling Repaint " + pitNum);
	}

	public int getPitNum() {
		return pitNum;
	}
	
	public void isPlaying(boolean inPlay){
		this.inPlay = inPlay; 
	}
	
	public int player() {
		return playNum;
	}
}