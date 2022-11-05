package XTank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bullet extends GameObject {	
	private static ImageIcon bulletIcon;
	private static BufferedImage image;
	int[] moveX = {0, 20, 0, -20};
	int[] moveY = {-20, 0, 20, 0};
	public Bullet(int x, int y, int dir) {
		super(x, y, dir);
		System.out.println(this);
		setImage();
	}

	public String toString() {
		return "{ bullet : " + "true; " + super.toString() + "}";
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
		System.out.println("travelk");
		this.setX(getX() + moveX[getDir() - 1]);
		this.setY(getY() + moveY[getDir() - 1]);
	}
}
