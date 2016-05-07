import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	
	 	private JLabel nameLabel;
	    private JLabel score; 
	    
	    public ScorePanel (Player p){	    	
	        String playerName = p.getName(); 

	        nameLabel = new JLabel (playerName);
	        score = new JLabel ("0");
	      
	        nameLabel.setFont(new Font("Calibri",Font.PLAIN,18));
	        nameLabel.setForeground(Color.BLACK); 
	        nameLabel.setBackground(Color.WHITE);
	        
	        score.setFont(new Font("Calibri",Font.PLAIN,20));
	        score.setForeground(Color.GRAY);
	        score.setBackground(Color.WHITE);
	        
	        JPanel p1 = new JPanel(null); 
	        p1.setBounds(5,5,120,120); 
	        p1.setBackground(Color.WHITE);
	        
	        nameLabel.setBounds(10,0,60,60);
	        score.setBounds(30,60,60,60);
	        
	        p1.add(nameLabel);
	        p1.add(score);
	        
	        this.setLayout(new BorderLayout());
	        this.add (p1, BorderLayout.CENTER);
	    }
	    public void refresh(int points)
	    {
	    	String s = String.valueOf(points); 
	        score.setText(s);
	    }
}
