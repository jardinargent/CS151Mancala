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
	
	private Component[] pitArray; // 0-5 player1, 6-11 player2
	private Color[] playerColors;
	
	public Style1(){
		player1 = new Color(255, 51, 51);
		player2 = new Color(255, 153, 51);
		
		playerColors = new Color[2];
		playerColors[0] = player1;
		playerColors[1] = player2;
		
		pitArray = new Component[14]; 

		for(int i = 0; i<14; i++){ 
			if(i == 0)
				pitArray[i] = new CirclePit(i,100,player1); 
			else if(i <=6 && i!=0)
				pitArray[i] = new RectanglePit(i,100,player1); 
			else if(i >=7 && i!=13)
				pitArray[i] = new RectanglePit(i,100,player2);
			else if(i == 13)
				pitArray[i] = new CirclePit(i,100,player2); 
		}
	}

	public String toString() {
		return "This is the style1 object!";
	}

	@Override
	public Component[] getPits() {
		return this.pitArray;
	}

	@Override
	public Color[] getColors() {
		return this.getColors();
	}

}
