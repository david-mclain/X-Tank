package XTank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player {
	
	private static final int DEFAULT_HEALTH = 5;
	
	private int playerNumber;
	private int health;
	private Scanner input;
	private PrintWriter output;
	private Tank tank;
	private Bullet bullet;
	
	private List<GameObject> playerObjects;
	
	public Player(int playerNumber) {
		//super(playerNumber);
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
		return "{ playerNumber : " + playerNumber + "; " + super.toString() + "health : " + health + "; }";
	}
	
	public void setInput(Scanner input) {  this.input = input;  }
	public void setOutput(PrintWriter output) {  this.output = output;  }
	public Scanner getInput() {  return input;  }
	public PrintWriter getOutput() {  return output;  }

	public void setImageAndMove(int i) {
		this.tank.setImageAndMove(i);
	}
	
	public void processMove(int x) {
		if (x <= 4) {
			tank.processMove(x);
		}
		else if (x == 10) {
			System.out.println("shoot");
			playerObjects.remove(bullet);
			bullet = new Bullet(tank.getX(), tank.getY(), tank.getDir());
			playerObjects.add(bullet);
			//game.addObject(bullet);
		}
	}
	
	public List<GameObject> getObjects() {
		return playerObjects;
	}
	
	public ImageIcon getImage() {
		return tank.getImage();
	}
	
	public Tank getTank() {
		return tank;
	}

	public void update() {
		if (bullet != null)
			bullet.travel();		
	}
	
}
