package XTank;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class GameObject {
	private int x;
	private int y;
	private int dir;
	
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
	
	public int getX() {  return x;  }

	public void setX(int x) {  this.x = x;  }

	public int getY() {  return y;   }

	public void setY(int y) {  this.y = y;  }

	public int getDir() {  return dir;  }

	public void setDir(int angle) {  this.dir = angle;  }
	
	public abstract ImageIcon getImage();
	
	public abstract Rectangle getHitBox();
	
	public String toString() {
		return x + "," + y + "," + dir;
	}
}
;