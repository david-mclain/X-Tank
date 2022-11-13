/**
 * 
 * @author David McLain
 * 
 * GameObject class is abstract and will be used for making any item in the game
 *
 */

package XTank;

import java.awt.Rectangle;
public abstract class GameObject {
	private int x;
	private int y;
	private int dir;
	/**
	 * Instantiates new GameObject
	 * @param playerNumber - playerNumber to determine starting direction
	 * @throws IllegalArgumentException
	 */
	public GameObject(int playerNumber) throws IllegalArgumentException {
		int x = 0, y = 0, dir = 0;
		if (playerNumber == 1) {
			x = 325;
			y = 50;
			dir = 3;
		}
		else if (playerNumber == 2) {
			x = 550;
			y = 250;
			dir = 4;
		}
		else if (playerNumber == 3) {
			x = 325;
			y = 500;
			dir = 1;
		}
		else if (playerNumber == 4) {
			x = 50;
			y = 250;
			dir = 2;
		}
		setX(x);
		setY(y);
		setDir(dir);
	}
	/**
	 * Instantiates new GameObject
	 * @param x - starting x position
	 * @param y - starting y position
	 * @param dir - starting direction
	 */
	public GameObject(int x, int y, int dir) {
		if (dir == 1) {
			x += 20;
			y -= 10;
		}
		else if (dir == 2) {
			x += 50;
			y += 20;
		}
		else if (dir == 3) {
			x += 20;
			y += 50;
		}
		else {
			x -= 10;
			y += 20;
		}
		setX(x);
		setY(y);
		setDir(dir);
	}
	/**
	 * Returns x coordinate
	 * @return x coordinate
	 */
	public int getX() {  return x;  }
	/**
	 * Sets x coordinate
	 * @param x - coordinate to set x to
	 */
	public void setX(int x) {  this.x = x;  }
	/**
	 * Returns y coordinate
	 * @return y coordinate
	 */
	public int getY() {  return y;   }
	/**
	 * Sets y coordinate
	 * @param y - coordinate to set y to
	 */
	public void setY(int y) {  this.y = y;  }
	/**
	 * Returns direction
	 * @return direction
	 */
	public int getDir() {  return dir;  }
	/**
	 * Sets direction
	 * @param dir - direction to face
	 */
	public void setDir(int dir) {  this.dir = dir;  }
	/**
	 * Returns current GameObject's HitBox
	 * @return current GameObject's HitBox
	 */
	public abstract Rectangle getHitBox();
	/**
	 * Returns String representation of GameObject
	 * Example: "200,100,2" where 200 is x coordinate, 100 is y coordinate and 2 is direction
	 */
	public String toString() {
		return x + "," + y + "," + dir;
	}
}