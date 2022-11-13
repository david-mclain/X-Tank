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
		obstacles.add(new Rectangle(0, 0, 650, 5));
		obstacles.add(new Rectangle(0, 0, 5, 600));
		obstacles.add(new Rectangle(0, 590, 650, 5));
		obstacles.add(new Rectangle(650, 0, 5, 580));
		obstacles.add(new Rectangle(150, 200, 350, 20));
		obstacles.add(new Rectangle(150, 400, 350, 20));
		barrier = new Rectangle(300, 100, 100, 25);
		mapSize = new Rectangle(0, 0, 635, 580);
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
	
	public void updatePlayer() {
		
	}
	
	private void checkHitboxes() {		
		for (GameObject obj : gameObjects) {
			if (obj instanceof Bullet) {
				Bullet bullet = (Bullet) obj;
				for (Player p : players) {
					if (p != null) {
						for (Rectangle r : obstacles) {
							if (r.intersects(bullet.getHitBox()))
								p.removeBullet(bullet);
						}
					}
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
