package XTank;

import javax.swing.ImageIcon;

public abstract class GameObject {
	private int x;
	private int y;
	private int dir;
	
	public GameObject(int playerNumber) {
		int x, y, dir;
		if (playerNumber == 0) {
			x = 250;
			y = 50;
			dir = 3;
		}
		else if (playerNumber == 1) {
			x = 250;
			y = 650;
			dir = 1;
		}
		else if (playerNumber == 2) {
			x = 450;
			y = 350;
			dir = 2;
		}
		else {
			x = 50;
			y = 350;
			dir = 4;
		}
		setX(x);
		setY(y);
		setDir(dir);
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
