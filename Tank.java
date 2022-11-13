package XTank;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Tank extends GameObject {
		
	private ImageIcon[] images;
	private ImageIcon curImage;
		
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	public Tank(int x) {
		super(x);
	}
	
	public void move(int i) {
		this.setX(this.getX() + moveX[i - 1]);
		this.setY(this.getY() + moveY[i - 1]);
	}

	public ImageIcon getImage(int x) {
		return images[x - 1];
	}
	
	public void processInput(int i) {
		setDir(i);
		if (i <= 4) {
			move(i);
		}
	}
	
	public String toString() {
		return super.toString();
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(getX() + 5, getY() + 5, 40, 40);
	}

	@Override
	public ImageIcon getImage() {
		return curImage;
	}
}
