package XTank;

public class GameObject {
	private int x;
	private int y;
	private int angle;
	
	public GameObject(int playerNumber) {
		int x, y, angle;
		if (playerNumber == 1) {
			x = 250;
			y = 50;
			angle = 180;
		}
		else if (playerNumber == 2) {
			x = 250;
			y = 650;
			angle = 0;
		}
		else if (playerNumber == 3) {
			x = 450;
			y = 350;
			angle = 90;
		}
		else {
			x = 50;
			y = 350;
			angle = 270;
		}
		
		setX(x);
		setY(y);
		setAngle(angle);
	}
	
	public GameObject(int x, int y, int angle) {
		setX(x);
		setY(y);
		setAngle(angle);
	}
	
	public int getX() {  return x;  }

	public void setX(int x) {  this.x = x;  }

	public int getY() {  return y;   }

	public void setY(int y) {  this.y = y;  }

	public int getAngle() {  return angle;  }

	public void setAngle(int angle) {  this.angle = angle;  }
	
	public String toString() {
		String toRet = "";
		toRet += "angle :" + angle + "; x : " + x + "; y : " + y + "; ";
		return toRet;
	}
}
