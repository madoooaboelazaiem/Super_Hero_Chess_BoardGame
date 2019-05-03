package Console;


import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
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




public class Engine {
	private Game game;
	private InfoPanel again;
	public Engine() {
		again = new InfoPanel(this);
	}
	public void Begin(String name1, String name2) {
		Player p1 = new Player(name1);
		Player p2 = new Player(name2);
		game = new Game(p1, p2);
		
		new GameView(this);
	}
	public Game getGame() {
		return game;
	}
	public String getPlayer1Name() {
		return game.getPlayer1().getName();
	}

	public int getPlayer1PayLoad() {
		return game.getPlayer1().getPayloadPos();
	}

	public String getPlayer2Name() {
		return game.getPlayer2().getName();
	}

	public int getPlayer2PayLoad() {
		return game.getPlayer2().getPayloadPos();
	}
	public String getCurrentPlayer() {
		if(this.getGame().getCurrentPlayer() == game.getPlayer1() ) {
			return  "avengers.jpg";
		}
		else
		return  "dc.jpg";
	}
	public String getCurrentPlayer1() {
		if(this.getGame().getCurrentPlayer() == game.getPlayer1() ) {
			return  "Player 1 (" + game.getPlayer1().getName() + ")'s Turn";
		}
		else
		return  "Player 2 (" + game.getPlayer2().getName() + ")'s Turn";
	}
	public String Herosimage(int i, int j) {
		Piece p = game.getCellAt(i, j).getPiece();
		return getimage(p);
	}
	public String getimage(Piece x) {
		if (x == null) {
			return "brick.png";
		}
		if (x instanceof SideKickP1) {
			return "Sidekick1.png";
		}
		if (x instanceof SideKickP2) {
			return "sidekick2.png";
		}

		if (x.getOwner() == game.getPlayer1() && x instanceof Armored && ((Armored) x).isArmorUp()) {
			return "captainamericaarmorup.png";
		}
		if (x.getOwner() == game.getPlayer1() && x instanceof Armored && (!((Armored) x).isArmorUp())) {
			return "captainamericaarmordown.png";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Armored && ((Armored) x).isArmorUp()) {
			return "wonderwomanarmorup.png";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Armored && (!((Armored) x).isArmorUp())) {
			return "wonderwomanarmordown.png";
		}

		if (x.getOwner() == game.getPlayer1() && x instanceof Speedster) {
			return "quicksilver.png";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Speedster) {
			return "Flashicon.png";
		}

		if (x.getOwner() == game.getPlayer1() && x instanceof Super) {
			return "HULKicon.png";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Super) {
			return "Supermanicon.png";
		}

		if (x.getOwner() == game.getPlayer1() && x instanceof Tech) {
			return "Ironmanicon.png";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Tech) {
			return "batmanicon.png";
		}

		if (x.getOwner() == game.getPlayer1() && x instanceof Medic) {
			return "vision.png";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Medic) {
			return "greenlantern.png";
		}

		if (x.getOwner() == game.getPlayer1() && x instanceof Ranged) {
			return "hawkeye.jpg";
		}
		if (x.getOwner() == game.getPlayer2() && x instanceof Ranged) {
			return "greenarrowicon.png";
		}

		return null;
	}
	public String move(Direction r, int i, int j,Piece x,Point p,int i2 ,int j2)
			throws UnallowedMovementException, OccupiedCellException,
			WrongTurnException {
		game.getCellAt(i, j).getPiece().move(r);
		if (game.getPlayer1().getPayloadPos() == 6) {
			return "(" + game.getPlayer1().getName() + ") wins";
		}
		if (game.getPlayer2().getPayloadPos() == 6) {
			return "(" + game.getPlayer2().getName() + ") wins";
		}
		return null;
	}
	public String usePower(Direction r, int i, int j,Piece z,Piece zz,int i3 , int j3 ,int i2,int j2)
			throws UnallowedMovementException, OccupiedCellException,
			WrongTurnException, InvalidPowerUseException  {
		Point p = new Point();
		p.x = i3;
		p.y = j3;
		Piece x = game.getCellAt(i, j).getPiece();
		Piece y = game.getCellAt(i2, j2).getPiece();
		if (x instanceof ActivatablePowerHero) {
			ActivatablePowerHero v = (ActivatablePowerHero) x;
			if(v instanceof Ranged || v instanceof Super) {
			v.usePower(r, null, null);}
			if(v instanceof Medic) {
				if(game.getCurrentPlayer()==game.getPlayer1()) {
				v.usePower(r, z, null);}
				else  {
					v.usePower(r, zz, null);
				}
			}
			if(v instanceof Tech) {
				
				v.usePower(null, y, p);
			}

			if (game.getPlayer1().getPayloadPos() == 6) {
				return "(" + game.getPlayer1().getName() + ") wins";
				
			}
			if (game.getPlayer2().getPayloadPos() == 6) {
				return "(" + game.getPlayer2().getName() + ") wins";
			}
		}
		return null;
	}
	public static void main(String[] args) {
		Engine x = new Engine();
	}
	
}
