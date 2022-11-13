/**
 * 
 * @author David McLain
 * 
 * Bullet class is used for any bullets a player shoots in game
 *
 */

package XTank;

import java.awt.Rectangle;

public class Bullet extends GameObject {	
	int[] moveX = {0, 3, 0, -3};
	int[] moveY = {-3, 0, 3, 0};
	/**
	 * Instantiates new bullet in with given location and direction
	 * @param x - x coordinate to create bullet
	 * @param y - y coordinate to create bullet
	 * @param dir - direction for bullet to travel
	 */
	public Bullet(int x, int y, int dir) {
		super(x, y, dir);
	}
	/**
	 * Returns String representation of Bullet
	 */
	public String toString() {
		return super.toString();
	}
	/**
	 * Returns current Bullet's HitBox
	 * @return current Bullet's HitBox
	 */
	public Rectangle getHitBox() {
		return new Rectangle(getX(), getY(), 10, 10);
	}
	/**
	 * Makes bullet travel in position when updated
	 */
	public void travel() {
		this.setX(getX() + moveX[getDir() - 1]);
		this.setY(getY() + moveY[getDir() - 1]);
	}
}
