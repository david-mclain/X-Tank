package XTank;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Tank extends GameObject {
		
	private ImageIcon[] images;
	private ImageIcon curImage;
		
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	public Tank(int x) {
		super(x);
		System.out.println("tank created");
//		setImages();
//		curImage = images[getDir() - 1];
	}
	
	public void setImageAndMove(int i) {
//		curImage = images[i - 1];
		this.setX(this.getX() + moveX[i - 1]);
		this.setY(this.getY() + moveY[i - 1]);
	}

	public ImageIcon getImage(int x) {
		return images[x - 1];
	}
	
	public void processInput(int i) {
		setDir(i);
		if (i <= 4) {
			setImageAndMove(i);
		}
	}
	
	public String toString() {
		return "tank:true," + super.toString();
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(getX() + 5, getY() + 5, 40, 40);
	}

	@Override
	public ImageIcon getImage() {
		return curImage;
	}
}
