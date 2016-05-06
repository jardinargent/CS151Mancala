/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Menu
*  File: 	Menu.java
*  Description:	Creates the initial menu screen
*  Date:	5/2/2016
*  @version	1
*  @author Team we.excelAt(ood)
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {

	private JFrame frame;
	private JPanel mainPanel;
	private JLabel title;
	private JLabel stoneLabel;
	private JLabel boardLabel;
	private JButton startGame;
	private JButton[] countButton;
	private JButton[] layoutButton;

	private BoardStyle board;
	private int stoneCount;

	public Menu() {
		stoneCount = 0;
		board = null;
		frame =  new JFrame();
		frame.setSize(500, 500);
		frame.getContentPane().add(createPanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Component createPanel() {
		Image menuImage = getImage();
		mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(menuImage, 0, 0, null);
			}
		};
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 500, 500);

		title = new JLabel("  M A N C A L A  ");
		title.setFont(new Font("AR JULIAN", Font.PLAIN, 32));
		title.setForeground(Color.BLACK);
		title.setBounds(100, 20, 300, 80);
		mainPanel.add(title);

		stoneLabel = new JLabel("# of Stones:");
		stoneLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		stoneLabel.setForeground(Color.GRAY);
		stoneLabel.setBounds(80, 120, 150, 150);
		mainPanel.add(stoneLabel);

		JButton three = createButton("3");
		three.setBounds(200, 165, 50, 50);
		mainPanel.add(three);
		countButton = new JButton[2];
		countButton[0] = three;

		JButton four = createButton("4");
		four.setBounds(260, 165, 50, 50);
		mainPanel.add(four);
		countButton[1] = four;

		boardLabel = new JLabel("Layout Type:");
		boardLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		boardLabel.setForeground(Color.GRAY);
		boardLabel.setBounds(80, 180, 150, 150);
		mainPanel.add(boardLabel);

		JButton style1 = createButton("A");
		style1.setBounds(200, 230, 50, 50);
		mainPanel.add(style1);
		layoutButton = new JButton[2];
		layoutButton[0] = style1;

		JButton style2 = createButton("B");
		style2.setBounds(260, 230, 50, 50);
		mainPanel.add(style2);
		layoutButton[1] = style2;

		startGame = new JButton("Start Game");
		startGame.setFont(new Font("AR JULIAN", Font.PLAIN, 16));
		startGame.setBackground(new Color(255, 166, 77));
		startGame.setForeground(Color.WHITE);
		startGame.setBounds(200, 290, 150, 50);
		mainPanel.add(startGame);

		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (stoneCount != 0 && board != null)
					frame.dispose();
			}
		});

		addOptionListeners();

		return mainPanel;
	}

	private void addOptionListeners() {
		Color blue = new Color(0, 64, 128);
		Color original = new Color(255, 77, 77);

		for (int i = 0; i < 2; i++) {
			JButton b = countButton[i];
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton clickButton = (JButton)e.getSource();
					if (b.getBackground().equals(blue)) {
						b.setBackground(original);
						if (Integer.parseInt(clickButton.getText()) == stoneCount) {
							stoneCount = 0;
						}
					} else {
						b.setBackground(blue);
						stoneCount = Integer.parseInt(b.getText());
					}
				}
			});

			JButton l = layoutButton[i];
			l.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton clickButton = (JButton)e.getSource();
					if (l.getBackground().equals(blue)) {
						if (clickButton.getText().equals("A")) {
							if (board.getClass() == Style1.class) {
								board = null;
							}
						}
						if (clickButton.getText().equals("B")) {
							if (board.getClass() == Style2.class) {
								board = null;
							}
						}
						l.setBackground(original);
					} else {
						l.setBackground(blue);
						if (l.getText().equals("A")) {
							board = new Style1();
							System.out.println(board.toString());
						} else if (l.getText().equals("B")) {
							board = new Style2();
							System.out.println(board.toString());
						}
					}
				}
			});
		}
	}

	private JButton createButton(String s) {
		JButton b = new JButton(s);
		b.setFont(new Font("Calibri", Font.PLAIN, 20));
		b.setForeground(Color.WHITE);
		b.setBackground(new Color(255, 77, 77));

		return b;

	}
	private Image getImage() {
		Image img = null;
		try {
			img = ImageIO.read(new File("board.jpg"));
			//img = img.getScaledInstance(500, 500, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	/**
	 * Returns stone count
	 * @return stoneCount returns the user selected stone count
	 */
	public int getStoneCount() {
		return this.stoneCount;
	}

	/**
	 * Returns the style chosen
	 * @return BoardStyle the style chosen
	 */
	public BoardStyle getStyle() {
		return board;
	}
}
