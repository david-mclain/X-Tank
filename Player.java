package XTank;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends GameObject {
	
	private static final int DEFAULT_HEALTH = 5;
	
	private int playerNumber;
	private int health;
	private Scanner input;
	private PrintWriter output;
	
	private ImageIcon[] images;
	
	private ImageIcon curImage;
	
	public Player(int playerNumber) {
		super(playerNumber);
		this.setPlayerNumber(playerNumber);
		this.setHealth(DEFAULT_HEALTH);
		setImages();
		curImage = images[getDir() - 1];
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

	public void setImage(int i) {
		curImage = images[i - 1];
	}
	
	@Override
	public ImageIcon getImage() {
		return curImage;
	}
	
	public ImageIcon getImage(int x) {
		return images[x - 1];
	}
	
	private void setImages() {
		BufferedImage temp = null;
		images = new ImageIcon[4];
		System.out.println(this.playerNumber);
		for (int i = 1; i <= 4; i++) {
			try {
				temp = ImageIO.read(Player.class.getResourceAsStream("tank_" + 1 + "_direction_" + i + ".png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			images[i - 1] = new ImageIcon(temp);
		}
	}
	
}
