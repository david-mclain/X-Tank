package XTank;

import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Player extends GameObject {
	
	private static final int DEFAULT_HEALTH = 5;
	
	private int playerNumber;
	private int health;
	private Scanner input;
	private PrintWriter output;
	
	public Player(int playerNumber) {
		super(playerNumber);
		this.setPlayerNumber(playerNumber);
		this.setHealth(DEFAULT_HEALTH);
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

	@Override
	public ImageIcon getImage() {
		//if ()
		return null;
	}
	
}
