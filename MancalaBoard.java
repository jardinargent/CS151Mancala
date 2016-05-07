import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	MancalaBoard
*  File: 	MancalaBoard.java
*  Description:	Class serves as the view portion of MVC
*  Date:	5/2/2016
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class MancalaBoard implements ChangeListener{

	private MancalaGame game;
	private BoardStyle style;
	private JFrame mainFrame;
	private JPanel mainPanel; 
	private JPanel contentPanel;
	private JPanel labelPanel; 
	private JLabel[] labelArray; 
	
	public MancalaBoard(MancalaGame game) {
		this.game = game;
		System.out.println("Makes a new board!");
		style = game.getChosenStyle();
		createBoard();
	}

	private void createBoard() {
		mainFrame = new JFrame();
		mainFrame.setSize(1000, 250);
		mainFrame.add(createContent());
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private Component createContent() {
		//createSmallLabels(); 
		contentPanel = new JPanel();
		contentPanel.setSize(1000, 250);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new GridLayout(2,8,3,3)); 
		
		Component[] pitArray = this.style.getPits(); 

		for(int i = 0; i<=6; i++){ 
			contentPanel.add(pitArray[i]);
		}
		
		contentPanel.add(new ScorePanel(game.getPlayer1())); 
		contentPanel.add(new ScorePanel(game.getPlayer2())); 
		
		for(int i = 7; i<=13; i++){ 
			contentPanel.add(pitArray[i]);
		}	
		return contentPanel;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
