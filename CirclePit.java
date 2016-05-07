import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

class CirclePit extends JPanel{
		private int pitNum; 
		boolean inPlay; 
		private int pitWidth;
		private Ellipse2D.Double pit; 
		private Color pitColor; 
		
		public CirclePit(int pitNum, int pitWidth,Color c){ 
			this.pitNum = pitNum; 
			this.pitWidth = pitWidth; 
			this.pitColor = c; 
			
			pit = new Ellipse2D.Double(0, 0, pitWidth, pitWidth); 
		}
		
		/*public void inPlay(boolean playing){
			this.inPlay = playing; 
		}*/
		
		@Override
		public void paintComponent(Graphics g){
			Graphics2D g2D = (Graphics2D) g;
			
				g2D.setColor(pitColor);
				g2D.fill(pit); 
		}
		
		public int getPitNum(){
			return pitNum; 
		}
	}