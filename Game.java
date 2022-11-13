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
	
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	
	private Game() {
		curPlayers = 0;
		gameObjects = new ArrayList<>();
		players = new Player[4];
		obstacles = new ArrayList<>();
		obstacles.add(new Rectangle(0, 0, 650, 5));
		obstacles.add(new Rectangle(0, 0, 5, 600));
		obstacles.add(new Rectangle(0, 590, 650, 5));
		obstacles.add(new Rectangle(650, 0, 5, 580));
		obstacles.add(new Rectangle(150, 200, 350, 20));
		obstacles.add(new Rectangle(150, 400, 350, 20));
	}
	
	public static Game getGame() {
		if (game == null) {
			game = new Game();
		}
		return game;
	}
	
	public String getBounds() {
		String ret = "";
		for (Rectangle r : obstacles) {
			ret = ret + (int)r.getX() + "," + (int)r.getY() + "," + (int)r.getWidth() + "," + r.getHeight() + ";";
		}
		return ret;
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
	
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

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

	public void killPlayer(Player player) {
		for (int i = 0; i < players.length; i++)
			if (players[i] == player)
				players[i] = null;
	}

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
