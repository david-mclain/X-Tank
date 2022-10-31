package XTank;

import javax.swing.ImageIcon;

public class Bullet extends GameObject{	
	
	public Bullet(int x, int y, int angle) {
		super(x, y, angle);
	}

	public String toString() {
		return "{ bullet : " + "true; " + super.toString() + "}";
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return null;
	}
}
