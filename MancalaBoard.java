import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		
		Pit[] pitArray = this.style.getPits(); 
		//player 2's pits
		for(int i = 13; i>=7; i--){
			pitArray[i].updateStoneCount(game.getPit(i, false));
			contentPanel.add((Component) pitArray[i]);
		}	
		
		contentPanel.add(new ScorePanel(game.getPlayer2())); 
		contentPanel.add(new ScorePanel(game.getPlayer1())); 

		//player1's pits 
		for(int i = 0; i<=6; i++){ 
			pitArray[i].updateStoneCount(game.getPit(i, false));
			contentPanel.add((Component) pitArray[i]);
		}
		
		addListeners(pitArray); 
		
		
		return contentPanel;
	}
	private void addListeners(Pit[] pitArray) {
		Pit[] p = pitArray; 
		
		for(int i = 0; i<pitArray.length; i++){ 
				JPanel pan = (JPanel) p[i];  
			pan.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
                    int pit = ((Pit) pan).getPitNum(); 
                    game.move(pit);
                    for(int i = 0; i<14; i++){
                    	pitArray[i].updateStoneCount(game.getPit(i, false)); 
                    }
                    	
                }
			}); 
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
