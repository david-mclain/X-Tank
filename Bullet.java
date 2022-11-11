package XTank;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bullet extends GameObject {	
	private static ImageIcon bulletIcon;
	private static BufferedImage image;
	int[] moveX = {0, 1, 0, -1};
	int[] moveY = {-1, 0, 1, 0};
	public Bullet(int x, int y, int dir) {
		super(x, y, dir);
		setImage();
	}

	public String toString() {
		return "bullet:true," + super.toString() + "";
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(getX(), getY(), 10, 10);
	}

	@Override
	public ImageIcon getImage() {
		return bulletIcon;
	}
	
	public void setImage() {
		try {
			image = ImageIO.read(Canvas.class.getResourceAsStream("bullet.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bulletIcon = new ImageIcon(image);
	}
	
	public void travel() {
		this.setX(getX() + moveX[getDir() - 1]);
		this.setY(getY() + moveY[getDir() - 1]);
	}
}
