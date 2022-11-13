/**
 * 
 * @author David McLain
 * 
 * Tank class is used for creating any Tank that will be used by players in game
 *
 */

package XTank;

import java.awt.Rectangle;

public class Tank extends GameObject {
		
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	/**
	 * Instantiates new Tank
	 * @param x - player number
	 */
	public Tank(int x) {
		super(x);
	}
	/**
	 * Moves tank in specified direction
	 * @param i - direction to move in
	 */
	public void move(int i) {
		setDir(i);
		this.setX(this.getX() + moveX[i - 1]);
		this.setY(this.getY() + moveY[i - 1]);
	}
	/**
	 * Processes move player did
	 * @param i - move from player
	 */
	public void processInput(int i) {
		if (i <= 4) {
			move(i);
		}
	}
	/**
	 * Returns String representation of Tank
	 */
	public String toString() {
		return super.toString();
	}
	/**
	 * Returns current Tank's HitBox
	 * @return current Tank's HitBox
	 */
	public Rectangle getHitBox() {
		return new Rectangle(getX() + 5, getY() + 5, 40, 40);
	}

}
