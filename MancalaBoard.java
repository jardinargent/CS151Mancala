/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	MancalaBoard
*  File: 	MancalaBoard.java
*  Description:	Class serves as the view and controller portion of MVC
*  Date:	5/2/2016
*  @author  Team we.excelAt(ood)
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaBoard implements ChangeListener {

	private MancalaGame game;
	private BoardStyle style;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel contentPanel;
	private JPanel labelPanel;
	private JPanel controlPanel; 
	private JPanel functionPanel; 
	private JLabel[] labelArray;
	private ScorePanel player1;
	private ScorePanel player2;
	private JButton undo;
	private JButton endTurn;
	private JButton restart; 
	private JButton changeBoard; 
	private JButton quit;
	private JLabel messageLabel;
	private Pit[] pitArray;
	private Color[] colorArray;

	/**
	 * Constructor initializes a MancalaBoard
	 * @param game the MancalaGame this board is associated with
	 */
	public MancalaBoard(MancalaGame game) {
		this.game = game;
		game.attach(this);
		
		 /*try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception ex) {
        	 ex.printStackTrace();
         }*/
		 
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setSize(1000, 420);
		mainPanel.add(new TitlePanel(), BorderLayout.NORTH);

		player1 = new ScorePanel(game.getPlayer1());
		player2 = new ScorePanel(game.getPlayer2());

		//System.out.println("Makes a new board!");
		style = game.getChosenStyle();
		pitArray = this.style.getPits();
		colorArray = this.style.getColors();
		createBoard();
	}

	private void createBoard() {
		mainFrame = new JFrame();
		mainFrame.setSize(1000, 420);
		mainFrame.setTitle("Mancala- From, Team we.excelAt(ood)");

		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(createContent());
		
		mainPanel.add(createButtonPanel(),BorderLayout.SOUTH);
		
		mainFrame.add(mainPanel);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private Component createButtonPanel() {
		Dimension d = new Dimension(1000,100); 
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,1));
		
		controlPanel = new JPanel();
		controlPanel.setSize(d);
		controlPanel.setLayout(new FlowLayout());
		
		controlPanel = new JPanel();
		controlPanel.setSize(d);
		controlPanel.setLayout(new FlowLayout());
		
		labelPanel = new JPanel(); 
		labelPanel.setSize(d);
		labelPanel.setLayout(new FlowLayout());
		
		functionPanel = new JPanel(); 
		functionPanel.setSize(d);
		functionPanel.setLayout(new FlowLayout()); 
		
		messageLabel = new JLabel("   Player 1's Turn");
		messageLabel.setFont(new Font("Calibri", Font.BOLD, 14));

		undo = new JButton("UNDO GAME");
		undo.setFont(new Font("Calibri", Font.BOLD, 15));
		undo.setBackground(colorArray[0]);
		undo.setForeground(colorArray[1]);
		undo.setFocusable(false);
		undo.setEnabled(false); //because no undoBoard when game starts!
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.undo();
				for (int i = 0; i < 14; i++) {
					pitArray[i].updateStoneCount(game.getPit(i, false));
				}

				stateChanged(new ChangeEvent(this));
			}

		});

		endTurn = new JButton("END TURN");
		endTurn.setFont(new Font("Calibri", Font.BOLD, 15));
		endTurn.setBackground(colorArray[0]);
		endTurn.setForeground(colorArray[1]);
		endTurn.setEnabled(false);
		endTurn.setFocusable(false);
		endTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.endTurn();
				int cPlayer = game.getCurrentPlayer().getPlayNum(); 
				for(int i = 0; i<14; i++){ 
					if(pitArray[i].player() == cPlayer){
						pitArray[i].isPlaying(true);
					}
					else 
						pitArray[i].isPlaying(false);
				}
				stateChanged(new ChangeEvent(this));
			}
		});
		restart = new JButton("RESTART"); 
		restart.setFont(new Font("Calibri",Font.BOLD,15));
		restart.setFocusable(false);
		restart.setBackground(new Color(51, 153, 102));
		restart.setForeground(Color.WHITE);
		restart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.restart();
				System.out.println("Restarting game!");
				for(int i = 0; i<14; i++){ 
					pitArray[i].updateStoneCount(game.getPit(i, false));
					if(i<=6)
						pitArray[i].isPlaying(true);
					else 
						pitArray[i].isPlaying(false);
				}
				game.alert();
			}			
		});
		
		changeBoard =  new JButton("CHANGE BOARD"); 
		changeBoard.setFont(new Font("Calibri",Font.BOLD,15));
		changeBoard.setBackground(new Color(128, 128, 128));
		changeBoard.setForeground(Color.WHITE);
		changeBoard.setFocusable(false);		
		changeBoard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu(new MancalaGame());
				mainFrame.dispose();
			}
			
		});
		
		quit = new JButton("QUIT");
		quit.setFont(new Font("Calibri", Font.BOLD, 15));
		quit.setFocusable(false);
		quit.setForeground(Color.WHITE);
		quit.setBackground(new Color(255, 77, 77));
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Thank You!");
				mainFrame.dispose();
			}

		});
		labelPanel.add(messageLabel);
		controlPanel.add(undo);
		controlPanel.add(endTurn);
		functionPanel.add(restart);
		functionPanel.add(changeBoard); 
		functionPanel.add(quit);
		
		buttonPanel.add(labelPanel);
		buttonPanel.add(controlPanel);
		buttonPanel.add(functionPanel); 
		
		return buttonPanel;

	}

	private Component createContent() {
		//createSmallLabels();
		contentPanel = new JPanel();
		contentPanel.setSize(1000, 250);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new GridLayout(2, 8, 3, 3));

		//player 2's pits
		for (int i = 13; i >= 7; i--) {
			pitArray[i].updateStoneCount(game.getPit(i, false));
			contentPanel.add((Component) pitArray[i]);
		}

		contentPanel.add(player2);
		contentPanel.add(player1);

		//player1's pits
		for (int i = 0; i <= 6; i++) {
			pitArray[i].updateStoneCount(game.getPit(i, false));
			contentPanel.add((Component) pitArray[i]);
		}

		addListeners(pitArray);


		return contentPanel;
	}
	private void addListeners(Pit[] pitArray) {
		Pit[] p = pitArray;

		for (int i = 0; i < pitArray.length; i++) {
			JPanel pan = (JPanel) p[i];
			pan.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					System.out.println("Pit MousePressed!!");
					int pit = ((Pit) pan).getPitNum();
					game.update(pit);
					for (int i = 0; i < 14; i++) {
						pitArray[i].updateStoneCount(game.getPit(i, false));
						if (i == 6)
							player1.refresh(game.getPit(6, false));
						if (i == 13)
							player2.refresh(game.getPit(13, false));
					}
				}
			});
		}

	}

	/**
	 * When the state changes, the content panel repaints
	 * 
	 * @param e  The ChangeEvent that captures a change 
	 * 			(in this case, moving mancala stones calls the stateChanged) 
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println("Repainting!");
		contentPanel.repaint();
		if (game.getCurrentPlayer().getUndos() == 0 || game.noUndoBoard()) {
			//System.out.println(game.getCurrentPlayer().getName());
			undo.setEnabled(false);
		} else
			undo.setEnabled(true);
		
		messageLabel.setForeground(Color.BLACK);

		if (game.extraTurn()) {
			messageLabel.setText("   " + game.getCurrentPlayer().getName() + "- EXTRA TURN!");
		} else if (game.invalidPrompted == true) {
			messageLabel.setText("   INVALID MOVE!");
			messageLabel.setForeground(Color.RED);
			messageLabel.repaint();
			game.invalidPrompted = false;
		} else if (game.getEndTurn()) {
			messageLabel.setText("   " + game.getCurrentPlayer().getName() + "'s Turn (END TURN)");
			restart.setEnabled(false);
		} else {
			restart.setEnabled(true);
			messageLabel.setText("   " + game.getCurrentPlayer().getName() + "'s Turn");
		}
		/*         MessageLabel  End     */
		if (game.extraTurn() || game.madeMove==false) {
			endTurn.setEnabled(false);
		} else
			endTurn.setEnabled(true);

		if (game.isPlayer1()) {
			undo.setBackground(colorArray[0]);
			undo.setForeground(colorArray[1]);
			endTurn.setBackground(colorArray[0]);
			endTurn.setForeground(colorArray[1]);
		} else {
			undo.setBackground(colorArray[1]);
			undo.setForeground(colorArray[0]);
			endTurn.setBackground(colorArray[1]);
			endTurn.setForeground(colorArray[0]);
		}

		if (game.gameOver()) {
			if(game.getWinner().equals(""))
				messageLabel.setText(" The game is a TIE!");
			else 
				messageLabel.setText(game.getWinner() + " won the game!!");
			undo.setEnabled(false);
			endTurn.setEnabled(false);
		}

	}
}
