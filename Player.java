package XTank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class Player {
	
	private static final int DEFAULT_HEALTH = 5;
	
	private int playerNumber;
	private int health;
	private Scanner input;
	private PrintWriter output;
	private Tank tank;
	private ArrayList<Bullet> bullets;
	
	private List<GameObject> playerObjects;
	
	public Player(int playerNumber) {
		//super(playerNumber);
		bullets = new ArrayList<Bullet>();
		this.setPlayerNumber(playerNumber);
		this.setHealth(DEFAULT_HEALTH);
		playerObjects = new ArrayList<>();
		tank = new Tank(playerNumber);
		playerObjects.add(tank);
	}

	public int getPlayerNumber() {  return playerNumber;  }

	public void setPlayerNumber(int playerNumber) {  this.playerNumber = playerNumber;  }

	public int getHealth() {  return health;  }

	public void setHealth(int health) {  this.health = health;  }
	
	public String toString() {
		return "playerNumber:" + playerNumber + "," + super.toString() + "health:" + health + "," + tank.toString();
	}
	
	public void setInput(Scanner input) {  this.input = input;  }
	public void setOutput(PrintWriter output) {  this.output = output;  }
	public Scanner getInput() {  return input;  }
	public PrintWriter getOutput() {  return output;  }

	public void setImageAndMove(int i) {
		this.tank.setImageAndMove(i);
	}
	
	public void processInput(int x) {
		if (x <= 4) {
			tank.processInput(x);
		}
		// Checks if there are less than 3 bullets from this player currently
		else if (x == 10 && bullets.size() < 3) {
			//playerObjects.remove(bullet);
			Bullet bullet = new Bullet(tank.getX(), tank.getY(), tank.getDir());
			bullets.add(bullet);
			playerObjects.add(bullet);
			//game.addObject(bullet);
		}
	}
	
	public List<GameObject> getObjects() {
		return playerObjects;
	}
	
	public List<Bullet> getBullets(){
		return bullets;
	}
	
	public void removeBullet(Bullet bullet) {
		playerObjects.remove(bullet);
		bullets.remove(bullet);
	}
	
	public ImageIcon getImage() {
		return tank.getImage();
	}
	
	public Tank getTank() {
		return tank;
	}

	public void update() {
		for(Bullet bullet: bullets) {
			if (bullet != null)
				bullet.travel();
		}		
	}
	
}
