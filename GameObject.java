package XTank;

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
				x = 600;
				y = 300;
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
	
	public GameObject(int x, int y, int angle) {
		setX(x);
		setY(y);
		setDir(angle);
	}
	
	public int getX() {  return x;  }

	public void setX(int x) {  this.x = x;  }

	public int getY() {  return y;   }

	public void setY(int y) {  this.y = y;  }

	public int getDir() {  return dir;  }

	public void setDir(int angle) {  this.dir = angle;  }
	
	public abstract ImageIcon getImage();
	
	public String toString() {
		String toRet = "";
		toRet += "dir :" + dir + "; x : " + x + "; y : " + y + "; ";
		return toRet;
	}
}
;