package Console;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


import model.game.Player;




public class InfoPanel extends JFrame  implements ActionListener{
	private JButton start;
	private JTextField username;
	private JTextField username2;
	private JLabel player1;
	private JLabel player2;
	private String s1;
	private String s2;
	private Engine engine;
	private JLabel background;
	public InfoPanel(Engine engine) {
		// create a new instance of Supermarket
		//supermarket = new Supermarket();
		this.engine = engine;
		setTitle("Welcome to Our Game");		
		this.setLayout(null);
		this.setResizable(true);
		//setBounds(50, 50, 800, 600); instead of setsize if you want to be accurate with setting parameters of jframe
		this.setSize(1200,700);
		getContentPane().setBackground(Color.BLACK);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		background = new JLabel(new ImageIcon("marvelvsdc1.png"));
		background.setBounds(0, 0, 1200, 700);
		setContentPane(background);
		Components();		
	}
	public void Components() {
		player1 = new JLabel("Player1's Name");
		player1.setBounds(400,265,200,60);
		player1.setFont(new Font("Player1's Name", Font.BOLD, 24));
		player1.setForeground(Color.YELLOW);		
		this.getContentPane().add(player1);
		player2 = new JLabel("Player2's Name");
		player2.setBounds(400,325,200,120);
		player2.setForeground(Color.YELLOW);
		player2.setFont(new Font("Player2's Name", Font.BOLD, 24));
		this.getContentPane().add(player2);
		username = new JTextField();
		username.setBounds(600,280,200,30);
		this.getContentPane().add(username);
		username2 = new JTextField();
		username2.setBounds(600,370,200,30);
		this.getContentPane().add(username2);
		start = new JButton("Start");
		start.setBounds(600, 440, 200, 30);
		start.setBackground(Color.yellow);
		start.addActionListener(this);
		this.getContentPane().add(start);
		//must be written in order to show what I need .
		this.setLayout(null);
		this.setSize(getMaximumSize());
		this.setVisible(true);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		s1= username.getText();//settext// set its text to the product's info
		//btnProduct.setText(product.toString());
		s2 = username2.getText();
		if (e.getSource() == start) {
			if (s1.equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Player1's Name",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (s2.equals("")) {
				JOptionPane.showMessageDialog(this, "Enter Player2's Name",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				dispose();
				engine.Begin(s1, s2);
			}
		}
		
	}
}
