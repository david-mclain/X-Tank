package XTank;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
	
	public Player(int playerNumber) {
		//super(playerNumber);
		this.setPlayerNumber(playerNumber);
		this.setHealth(DEFAULT_HEALTH);
		tank = new Tank(playerNumber);
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
			
		}
	}
	
	public ImageIcon getImage() {
		return tank.getImage();
	}
	
	public Tank getTank() {
		return tank;
	}
	
}
