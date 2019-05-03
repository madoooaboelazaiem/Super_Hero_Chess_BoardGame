package Console;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Image;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.game.Direction;
import model.game.Game;
import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;



public class GameView extends JFrame implements ActionListener, MouseListener {
	private Game game;
	private JButton [][]Heros;
	private JPanel pnlHeros;
	private JLabel player1Name;
	private JLabel player2Name;
	private JProgressBar pointsp1;
	private JProgressBar pointsp2;
	private JLabel move;
	private JPanel Hero1Deads;
	private JPanel Hero2Deads;
	private JButton up;
	private JButton upleft;
	private JButton upright;
	private JButton down;
	private JButton downleft;
	private JButton downright;
	private JButton left;
	private JButton right;
	private JLabel currentplayer;
	private JLabel currentplayer1;
	private JLabel Deadsp1;
	private JLabel Deadsp2;
	private Engine engine;
	private JButton usePower;
	private JLabel direction;
	private JComboBox<Direction> List;
	private JComboBox<Piece> DeadsP1;
	private JComboBox<Piece> DeadsP2;
	private JLabel background;
	int posI,posJ,posI2,posJ2,posI3,posJ3,posI4,posJ4,posI5,posJ5;
	Piece p;
	Piece c;
	Point newp;
	
	
	public  GameView(Engine engine)  { 
	
		this.engine = engine;
		game=engine.getGame();
		this.setTitle("Marvel Vs DC SuperHero Chess");
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		this.setSize(getMaximumSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		background = new JLabel(new ImageIcon("Background.jpg"));
		background.setBounds(0, 0, 1200, 700);
		setContentPane(background);
		pnlHeros = new JPanel();
		this.setVisible(true);
		//pnlProducts = new JPanel();
		// set it to use the GridLayout with 3 columns in width
		//pnlProducts.setLayout(new GridLayout(0, 3));
		// add it in the center of the JFrame
		//add(pnlProducts, BorderLayout.CENTER); 
		pnlHeros.setBounds(450, 150, 500, 400);
		pnlHeros.setLayout(new GridLayout(7, 6));
		Heros = new JButton[7][6];
		for(int i = 0 ; i<7 ; i++) {
			for(int j = 0 ;j<6;j++) {
				Heros[i][j] = new JButton();
				Heros[i][j].setIcon(new ImageIcon("brick.png"));				
				Heros[i][j].addActionListener(this);
				Heros[i][j].addMouseListener(this);
				pnlHeros.add(Heros[i][j]);
				
			}	
			
		}
		pointsp1 = new JProgressBar();
		pointsp1.setBounds(380, 150, 70, 450);
		pointsp1.setMinimum(0);
		pointsp1.setMaximum(6);
		pointsp1.setValue(0);
		pointsp1.setOrientation(JProgressBar.VERTICAL);
		pointsp1.setForeground(Color.RED);
		this.add(pointsp1);
		//txtCart = new JTextArea();
		// force it to have a width of 200 and the same height of the JFrame
		//txtCart.setPreferredSize(new Dimension(200, getHeight()));
		// prevent its contents from being edited
		//txtCart.setEditable(false);
		// use a monospaced font to make it look cooler
		//txtCart.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		// add it to the right of the JFrame
		//add(txtCart, BorderLayout.EAST);
		pointsp2 = new JProgressBar();
		pointsp2.setBounds(950, 150, 70, 450);
		pointsp2.setMinimum(0);
		pointsp2.setMaximum(6);
		pointsp2.setValue(0);
		pointsp2.setOrientation(JProgressBar.VERTICAL);
		pointsp2.setForeground(Color.BLACK);
		this.add(pointsp2);
		DeadsP1 = new JComboBox<Piece>();
		DeadsP1.setBounds(1100, 520, 138, 25);
		this.add(DeadsP1); 
		Hero1Deads = new JPanel(new GridLayout(1, 9));
		Hero1Deads.setBounds(450, 550, 500, 50);
		Hero1Deads.setBackground(Color.BLACK);
		Hero1Deads.addMouseListener(this);
		this.add(Hero1Deads);	
		DeadsP2 = new JComboBox<Piece>();
		DeadsP2.setBounds(1100, 520, 138, 25);
		this.add(DeadsP2); 
		Deadsp2 = new JLabel("DeadsP2");
		Deadsp2.setBounds(1040, 520, 138, 25);
		this.add(Deadsp2);
		Deadsp1 = new JLabel("DeadsP1");
		Deadsp1.setBounds(1040, 520, 138, 25);
		this.add(Deadsp1);
		Hero2Deads = new JPanel(new GridLayout(1, 9));
		Hero2Deads.setBounds(450, 100, 500, 50);
		Hero2Deads.setBackground(Color.BLACK);
		Hero2Deads.addMouseListener(this);
		this.add(Hero2Deads);
		move = new JLabel("Move");
		move.setBounds(1152, 350, 36, 36);
		move.setForeground(Color.RED);
		this.getContentPane().add(move);
		up = new JButton();
		up.setBounds(1150, 300, 36, 36);
		up.setIcon(new ImageIcon("up.png"));
		up.addActionListener(this);
		this.add(up);
		down = new JButton();
		down.setBounds(1150, 400, 36, 36);
		down.setIcon(new ImageIcon("DOWN.png"));
		down.addActionListener(this);
		this.add(down);
		right = new JButton();
		right.setBounds(1200, 350, 36, 36);
		right.setIcon(new ImageIcon("right.png"));
		right.addActionListener(this);
		this.add(right);
		left = new JButton();
		left.setBounds(1100, 350, 36, 36);
		left.setIcon(new ImageIcon("left.png"));
		left.addActionListener(this);
		this.add(left);
		upleft = new JButton();
		upleft.setBounds(1100, 300, 36, 36);
		upleft.setIcon(new ImageIcon("upleft.png"));
		upleft.addActionListener(this);
		this.add(upleft);
		downleft = new JButton();
		downleft.setBounds(1100, 400, 36, 36);
		downleft.setIcon(new ImageIcon("downleft.png"));
		downleft.addActionListener(this);
		this.add(downleft);
		upright = new JButton();
		upright.setBounds(1200, 300, 36, 36);
		upright.setIcon(new ImageIcon("upright.png"));
		upright.addActionListener(this);
		this.add(upright);
		this.add(upleft);
		downright = new JButton();
		downright.setBounds(1200, 400, 36, 36);
		downright.setIcon(new ImageIcon("downright.png"));
		downright.addActionListener(this);
		this.add(downright);
		currentplayer = new JLabel();
		currentplayer.setBounds(35, 50, 300, 600);
		this.add(currentplayer);
		currentplayer1 = new JLabel();
		currentplayer1.setBounds(1100, 450, 500, 25);
		this.add(currentplayer1);
		usePower = new JButton("Use Power");
		usePower.setBounds(1100, 470, 138, 25);
		usePower.addActionListener(this);
		this.add(usePower);
		direction = new JLabel("Direction");
		direction.setBounds(1040, 490, 100, 30);
		this.add(direction);
		List = new JComboBox<Direction>(Direction.values());
		List.setBounds(1100, 490, 138, 25);
		this.add(List);
		player1Name = new JLabel("Player 1 (" + engine.getPlayer1Name()+ ")");
		player1Name.setBounds(620, 560, 500, 100);
		player1Name.setFont(new Font("Player1's Name", Font.BOLD, 24));
		this.add(player1Name);
		player2Name = new JLabel("Player 2 (" + engine.getPlayer2Name()+ ")");
		player2Name.setBounds(625, 35, 500, 100);
		player2Name.setFont(new Font("Player2's Name", Font.BOLD, 24));

		this.add(player2Name);
		Load();	
		validate();	
		add(pnlHeros);
		
	
}
	
	public void Load() {
		if(engine.getGame().checkWinner()) {
			System.exit(0);
		}
		getContentPane().setBackground(Color.BLACK);
		player1Name.setForeground(Color.red);
		player2Name.setForeground(Color.red);
		int p1 = engine.getPlayer1PayLoad();
		int p2 = engine.getPlayer2PayLoad();
		ArrayList<Piece> d1 = engine.getGame().getPlayer1().getDeadCharacters();
		ArrayList<Piece> d2 = engine.getGame().getPlayer2().getDeadCharacters();
		for(int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				posI = i;
				posJ = j;				
				Heros[i][j].setIcon(new ImageIcon(engine.Herosimage(i, j)));
				if(engine.getGame().getCellAt(i, j)!=null) {
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof SideKickP1)
				Heros[i][j].setToolTipText("SideKicP1");
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof SideKickP2)
						Heros[i][j].setToolTipText("SideKickP2");
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Armored && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer2())
						Heros[i][j].setToolTipText("Name:"+"WonderWoman" + "   " +"Type:" + "Armored");
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Armored && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer1() )
						Heros[i][j].setToolTipText("Name:"+"CaptainAmerica" + "   " +"Type:" + "Armored");
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Speedster && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer2())
						Heros[i][j].setToolTipText("Name:"+"Flash" + "   " +"Type:" + "Speedster");
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Speedster && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer1() )
						Heros[i][j].setToolTipText("Name:"+"QuickSilver" + "   " +"Type:" + "Speedster");
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Super && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer2())
						Heros[i][j].setToolTipText("Name:"+"SuperMan" + "   " +"Type:" + "Super" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Super && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer1() )
						Heros[i][j].setToolTipText("Name:"+"Hulk" + "   " +"Type:" + "Super" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Tech && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer2())
						Heros[i][j].setToolTipText("Name:"+"Batman" + "   " +"Type:" + "Tech" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Tech && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer1() )
						Heros[i][j].setToolTipText("Name:"+"Ironman" + "   " +"Type:" + "Tech" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Medic && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer2())
						Heros[i][j].setToolTipText("Name:"+"GreenLantern" + "   " +"Type:" + "Medic" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Medic && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer1() )
						Heros[i][j].setToolTipText("Name:"+"Vision" + "   " +"Type:" + "Medic" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Ranged && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer2())
						Heros[i][j].setToolTipText("Name:"+"GreenArrow" + "   " +"Type:" + "Ranged" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
					if(engine.getGame().getCellAt(i, j).getPiece() instanceof Ranged && engine.getGame().getCellAt(i, j).getPiece().getOwner() == engine.getGame().getPlayer1() )
						Heros[i][j].setToolTipText("Name:"+"Thor" + "   " +"Type:" + "Ranged" +"   "+"PowerUsed:" + "   "+(((ActivatablePowerHero) engine.getGame().getCellAt(i, j).getPiece()).isPowerUsed()));
				}
				}

				
			
		}
		
		
		if(engine.getGame().getCurrentPlayer() == engine.getGame().getPlayer1()) {
		Deadsp1.setVisible(true);
		Deadsp2.setVisible(false);
		DeadsP1.setVisible(true);
		DeadsP2.setVisible(false);
		DeadsP1.removeAllItems();
		DeadsP1.setModel(new DefaultComboBoxModel(d1.toArray()));
		}
		if(engine.getGame().getCurrentPlayer() == engine.getGame().getPlayer2()) {
			Deadsp1.setVisible(false);
			Deadsp2.setVisible(true);
			DeadsP2.setVisible(true);
			DeadsP1.setVisible(false);
			DeadsP2.removeAllItems();
			DeadsP2.setModel(new DefaultComboBoxModel(d2.toArray()));
			}
		
			Hero1Deads.removeAll();
		
		int x = engine.getGame().getPlayer1().getDeadCharacters().size();
		for (int i = 0; i < x; i++) {
			Hero1Deads.add(new JButton(new ImageIcon(engine.getimage(d1.get(i)))));
		}

		Hero2Deads.removeAll();
		int x2 = engine.getGame().getPlayer2().getDeadCharacters().size();
		for (int i = 0; i <x2 ; i++) {
			Hero2Deads.add(new JButton(new ImageIcon(engine.getimage(d2.get(i)))));
		}

		pointsp1.setValue(p1);
		pointsp2.setValue(p2);
		direction.setForeground(Color.red);
		Deadsp2.setForeground(Color.red);
		Deadsp1.setForeground(Color.red);
		currentplayer.setIcon(new ImageIcon(engine.getCurrentPlayer()));
		currentplayer1.setText(((engine.getCurrentPlayer1())));currentplayer1.setForeground(Color.RED);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(p==null) {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (e.getSource() == Heros[i][j]) {
					posI = i;
					posJ = j;
				//	System.out.println(game.getCellAt(posI, posJ));
					p=game.getCellAt(posI, posJ).getPiece();

				}
			}
		}
		}else {
			if(c==null) {//ya3ni lw msh ma7gooza aw meddaas 3aleeha y5osh y check w yegeeb el positions lama yeddaas 3leeha
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 6; j++) {
						if (e.getSource() == Heros[i][j]) {
							posI4 = i;
							posJ4= j;
							c=game.getCellAt(posI4, posJ4).getPiece();

						}
					}
				}
				}else {
					if(newp==null) {
						for (int i = 0; i < 7; i++) {
							for (int j = 0; j < 6; j++) {
								if (e.getSource() == Heros[i][j]) {
									posI5 = i;
									posJ5= j;
									newp=new Point(i,j);

								}
							}
						}
					}
				}
		}
		
		if(e.getSource()== up) {
			try {
				String x = engine.move(Direction.UP, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;
				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
				c = null;
			} catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				p=null;

				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
			
				c = null;}
		}
		if(e.getSource()== down) {
			try {
				String x = engine.move(Direction.DOWN, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
			} catch (UnallowedMovementException e1) {
				p=null;

				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				p=null;

				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
		}
		if(e.getSource()== right) {
			try {
				String x = engine.move(Direction.RIGHT, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
			} catch (UnallowedMovementException e1) {
				p=null;

				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				p=null;

				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
		}
		if(e.getSource()== left) {
			try {
				String x = engine.move(Direction.LEFT, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
				c = null;
			} catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
		}
		if(e.getSource()== upleft) {
			try {
				String x = engine.move(Direction.UPLEFT, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
			} catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
		
				c = null;
				} catch (OccupiedCellException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
			
				c = null;
				} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
		}
		if(e.getSource()== upright) {
			try {
				String x = engine.move(Direction.UPRIGHT, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
			
			} catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
		}
		if(e.getSource()== downleft) {
			try {
				String x = engine.move(Direction.DOWNLEFT, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
			
			} catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
		}
		if(e.getSource()== downright) {
			try {
				String x = engine.move(Direction.DOWNRIGHT, posI, posJ,null,null,posI2,posJ2);
				Load();
				p=null;

				if (x != null) {
					JOptionPane.showMessageDialog(this,x);
				}
				
			} catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "You can't perform an action with this piece as it does not belong to the current player or the direction is invalid.", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (OccupiedCellException e1) {
				// TODO Auto-generated catch block
				p=null;

				JOptionPane.showMessageDialog(this, "The position you are trying to reach is already Occupied", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				p=null;
				
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				c = null;
			}
			
		}
		if (e.getSource() == usePower) {			
			try {				
				if(p instanceof Tech) {
					((Tech) p).usePower(null, c, newp);
					p=null;
					c=null;
					newp=null;
					Load();
				}else {
				String x = engine.usePower((Direction) List.getSelectedItem(), posI, posJ,(Piece) DeadsP1.getSelectedItem(),(Piece) DeadsP2.getSelectedItem(),posI3,posJ3,posI2,posJ2);
				Load();
				p=null;
				if (x != null) {
					JOptionPane.showMessageDialog(this, x);}
				}
			} catch (InvalidPowerUseException e1) {
				
			
				System.out.println(p);
				System.out.println(c.getPosI()+" "+c.getPosJ()+" "+c.getOwner());
//				System.out.println(newp);
				JOptionPane.showMessageDialog(this, "You can't perform this power with this piece as it is invalid or will result in hitting a friendly piece .", "Error",
						JOptionPane.ERROR_MESSAGE);
				p = null;
				c=null;
			
			}
			catch (UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				
				JOptionPane.showMessageDialog(this, "The position you're trying to use your power in is invalid", "Error",JOptionPane.ERROR_MESSAGE);
			p=null;
				c = null;
				} catch (OccupiedCellException e1) {
				// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "The position you are trying to use your power in contatins a friendly piece .", "Error",JOptionPane.ERROR_MESSAGE);
					p=null;
					c = null;
			} catch (WrongTurnException e1) {
				// TODO Auto-generated catch block
				
				JOptionPane.showMessageDialog(this, "The piece you're trying to use is an enemy piece .", "Error",JOptionPane.ERROR_MESSAGE);
				p=null;
				c = null;
			}
		}
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (e.getSource() == Heros[i][j]&& e.getButton() == MouseEvent.BUTTON2) {
					posI2 = i;
					posJ2 = j;
					if(e.getSource()!= usePower) {
					if (e.getSource() == Heros[i][j] && e.getButton() == MouseEvent.BUTTON3) {
						posI3 = i;
						posJ3 = j;
					}
					}
				}
			}
		}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	
			}
	
	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
		
	
	
}