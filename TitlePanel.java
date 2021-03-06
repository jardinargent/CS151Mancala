/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	TitlePanel
*  File: 	TitlePanel.java
*  Description:	Creates the Header Panel in game board
*  Date:	5/2/2016
*  @author  Team we.excelAt(ood)
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {

	JLabel titleLabel;
	/**
	 * Constructor initializes title panel for game board
	 */
	public TitlePanel() {
		setSize(1000, 300);
		titleLabel = new JLabel("M A N C A L A");
		titleLabel.setFont(new Font("AR JULIAN", Font.PLAIN, 32));
		titleLabel.setForeground(Color.BLACK);
		add(titleLabel);
	}
	
	/**
	 * Overrides paint component of JPanel
	 * @param g  Graphics object
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image menuImage = getImage();
		g.drawImage(menuImage, 0, 0, null);
	}

	private Image getImage() {
		Image img = null;
		try {
			img = ImageIO.read(new File("boardT.jpg"));
			img = img.getScaledInstance(1000, 50, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

}
