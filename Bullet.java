package XTank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bullet extends GameObject {	
	private static ImageIcon bulletIcon;
	private static BufferedImage image;
	public Bullet(int x, int y, int angle) {
		super(x, y, angle);
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
}
