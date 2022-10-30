package XTank;

public class GameObject {
	private int x;
	private int y;
	private int angle;
	
	public GameObject(int x, int y, int angle) {
		
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
