package XTank;

public class Player extends GameObject {
	
	private static final int DEFAULT_HEALTH = 5;
	
	private int playerNumber;
	private int health;
	
	public Player(int x, int  y, int angle, int playerNumber) {
		super(x, y, angle);
		this.setPlayerNumber(playerNumber);
		this.setHealth(DEFAULT_HEALTH);
	}

	public int getPlayerNumber() {  return playerNumber;  }

	public void setPlayerNumber(int playerNumber) {  this.playerNumber = playerNumber;  }

	public int getHealth() {  return health;  }

	public void setHealth(int health) {  this.health = health;  }
	
	public String toString() {
		return "{ playerNumber : " + playerNumber + "; " + super.toString() + "health : " + health + " }";
	}
	
}
