import java.awt.Color;
import java.awt.Component;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Style1
*  File: 	Style1.java
*  Description:	Style1
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Style1 implements BoardStyle {
	//rectangle pit
	private Color player1;
	private Color player2;
	
	private Pit[] pitArray; // 0-6 player1, 7-13 player2, 6 & 13 scoring
	private Color[] playerColors;
	
	public Style1(){
		player1 = new Color(255, 51, 51);
		player2 = new Color(255, 153, 51);
		
		playerColors = new Color[2];
		playerColors[0] = player1;
		playerColors[1] = player2;
		
		pitArray = new Pit[14]; 

		for(int i = 0; i<14; i++){ 
			if(i <6)
				pitArray[i] = new RectanglePit(i,100,player1); 
			else if(i >=7 && i!=13)
				pitArray[i] = new RectanglePit(i,100,player2);
			else if(i == 13)
				pitArray[i] = new CirclePit(i,100,player2);
			else if(i==6)
				pitArray[i] = new CirclePit(i,100,player1);
		}
	}

	public String toString() {
		return "This is the style1 object!";
	}

	@Override
	public Pit[] getPits() {
		return this.pitArray;
	}

	@Override
	public Color[] getColors() {
		return this.getColors();
	}

}
