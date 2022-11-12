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
	List<Rectangle> obstacles; 
	private Rectangle barrier, mapSize;
	
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	
	private Game() {
		curPlayers = 0;
		gameObjects = new ArrayList<>();
		players = new Player[4];
		obstacles = new ArrayList<>();
		obstacles.add(new Rectangle(0, 0, 635, 10));
		obstacles.add(new Rectangle(0, 0, 10, 580));
		obstacles.add(new Rectangle(0, 570, 635, 10));
		obstacles.add(new Rectangle(625, 0, 10, 580));
		barrier = new Rectangle(300, 100, 100, 25);
		mapSize = new Rectangle(0, 0, 635, 580);
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
	
	public synchronized void refresh() {
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
	
	public Player getPlayer(int i) {
		return players[i - 1];
	}
	
	public void updatePlayer() {
		
	}
	
	private void checkHitboxes() {
		for (GameObject obj : gameObjects) {
			// If the type is a bullet, check if its hits a player
			if (obj instanceof Bullet) {
				Bullet bullet = (Bullet) obj;
				// Check if bullet hits any players
				for (Player p : players) {
					// Check if bullet not in bounds
					if (p != null && !bullet.getHitBox().intersects(mapSize)) {
						p.removeBullet(bullet);
					}
					// Check if bullet intersects with player
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

	public boolean moveInRange(int x, Rectangle hitbox) {
		Rectangle r = new Rectangle((int)hitbox.getX() + moveX[x - 1], (int)hitbox.getY() + moveY[x - 1], (int)hitbox.getWidth(), (int)hitbox.getHeight());
		for (Rectangle a : obstacles) {
			if (r.intersects(a))
				return false;
		}
		return true;
	}
}
