package XTank;

import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public abstract class GameObject implements Serializable {
	private int x;
	private int y;
	private int dir;
	
	public GameObject(int playerNumber) throws IllegalArgumentException {
		try {
			int x, y, dir;
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
				y = 550;
				dir = 1;
			}
			else if (playerNumber == 4) {
				x = 50;
				y = 300;
				dir = 2;
			}
			else {
				throw new IllegalArgumentException("Too many players");
			}
			setX(x);
			setY(y);
			setDir(dir);
		}
		finally {
			
		}
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
		String toRet = "";
		toRet += "dir :" + dir + "; x : " + x + "; y : " + y + "; ";
		return toRet;
	}
}
;