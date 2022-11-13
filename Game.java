/**
 * 
 * @author David McLain
 * 
 * Game class uses Singleton pattern to create a new Game that players will connect to
 * Controls all game state and processes anything that happens in game
 *
 */

package XTank;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private Player[] players;
	private int curPlayers;
	private final int MAX_PLAYERS = 4;
	private static Game game;
	List<Rectangle> obstacles; 
	
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	/**
	 * Instantiates new game
	 * Can only be accessed through static call of getGame();
	 */
	private Game() {
		curPlayers = 0;
		players = new Player[4];
		obstacles = new ArrayList<>();
		obstacles.add(new Rectangle(0, 0, 650, 5));
		obstacles.add(new Rectangle(0, 0, 5, 600));
		obstacles.add(new Rectangle(0, 590, 650, 5));
		obstacles.add(new Rectangle(650, 0, 5, 580));
		obstacles.add(new Rectangle(150, 200, 350, 20));
		obstacles.add(new Rectangle(150, 400, 350, 20));
	}
	/**
	 * Returns current Game
	 * @return current Game
	 */
	public static Game getGame() {
		if (game == null) {
			game = new Game();
		}
		return game;
	}
	/**
	 * Returns next player in game
	 * @return next player in game
	 */
	public int getCurPlayer() {  return curPlayers + 1;  }
	/**
	 * Adds newly instantiated player to game
	 * @param player - player to add to game
	 */
	public void addPlayer(Player player) {
		players[curPlayers++] = player;
	}
	/**
	 * Refreshes game
	 */
	public synchronized void refresh() {
		checkHitboxes();
	}
	/**
	 * Returns array of players
	 * @return array of players
	 */
	public Player[] getPlayers() {
		return players;
	}
	/**
	 * Returns specific player
	 * @param i - player to return
	 * @return specific player
	 */
	public Player getPlayer(int i) {
		return players[i - 1];
	}
	/**
	 * Checks all hitboxes to see if any bullets hit tanks or obstacles
	 */
	private void checkHitboxes() {
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			if (p == null)
				continue;
			List<Bullet> bullets = p.getBullets();
			boolean[] remove = new boolean[bullets.size()];
			if (bullets.size() == 0) {
				continue;
			}
			for (int l = 0; l < bullets.size(); l++) {
				Bullet b = bullets.get(l);
				for (Rectangle r : obstacles) {
					if (b.getHitBox().intersects(r)) {
						remove[l] = true;
					}
				}
				if (remove[l])
					continue;
				for (Player temp : players) {
					if (temp == null) 
						continue;
					if (b.getHitBox().intersects(temp.getTank().getHitBox())) {
						temp.setHealth(temp.getHealth() - 1);
						remove[l] = true;
					}
				}
			}
			for (int k = remove.length - 1; k >= 0; k--) {
				if (remove[k]) {
					bullets.remove(k);
				}
			}
		}
	}
	/**
	 * Returns true if max players joined, false otherwise
	 * @return true if max players joined, false otherwise
	 */
	public boolean playersFull() {
		return this.curPlayers == MAX_PLAYERS;
	}
	/**
	 * Checks if move is in range that player is trying to do
	 * @param x - direction player is trying to move
	 * @param hitbox - hitbox of current player's tank
	 * @return true if move can be done, false otherwise
	 */
	public boolean moveInRange(int x, Rectangle hitbox) {
		Rectangle r = new Rectangle((int)hitbox.getX() + moveX[x - 1], (int)hitbox.getY() + moveY[x - 1], (int)hitbox.getWidth(), (int)hitbox.getHeight());
		for (Rectangle a : obstacles) {
			if (r.intersects(a))
				return false;
		}
		return true;
	}
	/**
	 * Kills specified player
	 * @param player - player to kill
	 */
	public void killPlayer(Player player) {
		for (int i = 0; i < players.length; i++)
			if (players[i] == player)
				players[i] = null;
	}
	/**
	 * Checks if player has won game
	 * @param player - player to compare
	 * @return true if player won, false otherwise
	 */
	public boolean checkWin(Player player) {
		if (curPlayers <= 1)
			return false;
		for (Player p : players) {
			if (p != null && p != player)
				return false;
		}
		return true;
	}
}
