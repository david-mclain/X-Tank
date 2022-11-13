/**
 * 
 * @author David McLain
 * 
 * Player class is used for keeping track of players actions
 *
 */

package XTank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private static final int DEFAULT_HEALTH = 5;
	
	private int playerNumber;
	private int health;
	private DataInputStream input;
	private DataOutputStream output;
	private Tank tank;
	private ArrayList<Bullet> bullets;
	/**
	 * Instantiates new player with specified number
	 * @param playerNumber - number of player
	 */
	public Player(int playerNumber) {
		bullets = new ArrayList<Bullet>();
		this.setPlayerNumber(playerNumber);
		this.setHealth(DEFAULT_HEALTH);
		tank = new Tank(playerNumber);
	}
	/**
	 * Returns current player's number
	 * @return current player's number
	 */
	public int getPlayerNumber() { return playerNumber; }
	/**
	 * Sets current player's number
	 * @param playerNumber - number to set to player
	 */
	public void setPlayerNumber(int playerNumber) { this.playerNumber = playerNumber; }
	/**
	 * Returns current player's health
	 * @return current player's health
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Sets current player's health
	 * @param health - health to set to player
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * Returns a String representation of player
	 * Example: "1,5," with tank's string representation and and bullets concatenated to it, where 1 is player number and 5 is health 
	 */
	public String toString() {
		String ret = playerNumber + "" + health + "," + tank.toString();
		for (Bullet b : bullets) {
			if (b != null)
				ret += ";" + b.toString();
		}
		return ret;
	}
	/**
	 * Sets current player's DataInputStream
	 * @param DataInputStream - DataInputStream to set to player
	 */
	public void setInput(DataInputStream dataInputStream) {
		this.input = dataInputStream;
	}
	/**
	 * Sets current player's ouput
	 * @param DataOutputStream - DataOutputStream to set to player
	 */
	public void setOutput(DataOutputStream dataOutputStream) {
		this.output = dataOutputStream;
	}
	/**
	 * Returns DataInputStream of player
	 * @return DataInputStream of player
	 */
	public DataInputStream getInput() {
		return input;	
	}
	/**
	 * Returns DataOutputStream of player
	 * @return DataOutputStream of player
	 */
	public DataOutputStream getOutput() {
		return output;
	}
	/**
	 * Moves tank in specified direction
	 * @param i - direction to move tank
	 */
	public void move(int i) {
		this.tank.move(i);
	}
	/**
	 * Processes input from player
	 * @param x - input from player
	 */
	public void processInput(int x) {
		if (x <= 4) {
			tank.processInput(x);
		}
		else if (x == 5 && bullets.size() < 3) {
			Bullet bullet = new Bullet(tank.getX(), tank.getY(), tank.getDir());
			bullets.add(bullet);
		}
	}
	/**
	 * Returns all of player's current bullets
	 * @return all of player's current bullets
	 */
	public List<Bullet> getBullets(){
		return bullets;
	}
	/**
	 * Returns current player's tank
	 * @return current player's tank
	 */
	public Tank getTank() {
		return tank;
	}
	/**
	 * Updates bullets positions
	 */
	public void update() {
		for(Bullet bullet: bullets) {
			if (bullet != null)
				bullet.travel();
		}		
	}
}
