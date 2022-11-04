package XTank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Tank extends GameObject {
	
	private int number;
	
	private ImageIcon[] images;
	
	private ImageIcon curImage;
		
	int[] moveX = {0, 10, 0, -10};
	int[] moveY = {-10, 0, 10, 0};
	public Tank(int x) {
		super(x);
		this.number = x;
		setImages();
		curImage = images[getDir() - 1];
	}
	
	public void setImageAndMove(int i) {
		curImage = images[i - 1];
		this.setX(this.getX() + moveX[i - 1]);
		this.setY(this.getY() + moveY[i - 1]);
	}

	public ImageIcon getImage(int x) {
		return images[x - 1];
	}
	
	public void processMove(int i) {
		if (i <= 4) {
			setImageAndMove(i);
		}
	}
	
	private void setImages() {
		BufferedImage temp = null;
		images = new ImageIcon[4];
		System.out.println(this.number);
		for (int i = 1; i <= 4; i++) {
			try {
				temp = ImageIO.read(Tank.class.getResourceAsStream("tank_" + number + "_direction_" + i + ".png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			images[i - 1] = new ImageIcon(temp);
		}
	}

	@Override
	public ImageIcon getImage() {
		return curImage;
	}
}
