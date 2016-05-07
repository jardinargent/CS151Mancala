/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	MancalaController
*  File: 	MancalaController.java
*  Description:	Main purpose is to handle all the user interface portion of the game,
*  communicates with both Model and View.
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MancalaController implements ActionListener {

	private MancalaGame g;
	private Pit[] p;

	public MancalaController(MancalaGame g, Pit[] p) {
		this.g = g;
		this.p = p;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
