package XTank;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private Player[] players;
	private int curPlayers;
	private final int MAX_PLAYERS = 4;
	private List<GameObject> gameObjects;
	private static Game game;
	private Rectangle barrier;
	
	private Game() {
		curPlayers = 0;
		gameObjects = new ArrayList<>();
		players = new Player[4];
		barrier = new Rectangle(300, 100, 100, 25);
	}
	
	public static Game getGame() {
		if (game == null) {
			game = new Game();
		}
		return game;
	}
	
	public int getCurPlayer() {  return curPlayers + 1;  }
	
	public void addPlayer(Player player) {
		players[curPlayers++] = player;
	}
	
	public void addObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	public void refresh() {
		gameObjects.clear();
		for (Player p : players) {
			if (p != null) {
				gameObjects.addAll(p.getObjects());
			}
		}
		checkHitboxes();
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	private void checkHitboxes() {
		for (GameObject obj : gameObjects) {
			// If the type is a bullet, check if its hits a player
			if (obj instanceof Bullet) {
				Bullet bullet = (Bullet) obj;
				for (Player p : players) {
					// if bullet collides with hitbox
					if (p != null && bullet.getHitBox().intersects(p.getTank().getHitBox())) {
						System.out.println("DIE");
						 p.removeBullet(bullet);
						 p.setHealth(p.getHealth() - 1);
					}
				}
			}
		}
	}
	
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	/*
	 * class Game 
{
	// Board cells numbered 0-8, top to bottom, left to right; null if empty
	private Player[] board = new Player[9];
	private Player player1 = null;
	private Player player2 = null;
	private Player currentPlayer = null;
	
	public Player[] getBoard() {return board;}
	public Player getCurrentPlayer() {return currentPlayer;}
	public void setCurrentPlayer(Player player) {currentPlayer = player;}
	public Player getPlayer1(){return player1;}
	public void setPlayer1(Player player) {player1 = player;}
	public Player getPlayer2(){return player2;}
	public void setPlayer2(Player player) {player2= player;}
}
	 * 
	 */

	public boolean playersFull() {
		return this.curPlayers == MAX_PLAYERS;
	}
}
