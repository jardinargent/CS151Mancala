import java.awt.Component;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	MancalaBoard
*  File: 	MancalaBoard.java
*  Description:	Class serves as the view portion of MVC
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class MancalaBoard {

	private MancalaGame game;
	private BoardStyle style;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel[] pitLabels; //0-5 player1, 6-11 player2
	private Shape mancalaPit1;
	private Shape mancalaPit2;

	public MancalaBoard(MancalaGame game) {
		this.game = game;
		System.out.println("Makes a new board!");
		style = game.getChosenStyle();
		pitLabels = new JLabel[12];
		createBoard();
	}

	private void createBoard() {
		mainFrame = new JFrame();
		mainFrame.setSize(1000, 500);
		mainFrame.getContentPane().add(createContent());
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Component createContent() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(1000, 500);

		return mainPanel;
	}



}
