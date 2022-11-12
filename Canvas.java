package XTank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private ImageIcon[][] tankImages;
	private Graphics g;
	private int[] tankInfo;
	private ImageIcon bullet;
	private int[][] bullets;
	Canvas() {
		super();
		//this.you = you;
		setTankImages();
		setBulletImage();
	}
	
	public void paint(Graphics g) {
		setGraphics(g);
		g.clearRect(0, 0, 800, 630);
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
				
//		List<GameObject> gameObjects = game.getGameObjects();
//		for (GameObject obj : gameObjects) {
//			if (obj != null) {
//				obj.getImage().paintIcon(this, g, obj.getX(), obj.getY());
//				Rectangle hitbox = obj.getHitBox();
//				g.drawRect((int)hitbox.getX(), (int)hitbox.getY(), (int)hitbox.getWidth(), (int)hitbox.getHeight());
//				g.drawRect(300, 100, 100, 25);
//				//g.drawRect(obj.getX() + 5, obj.getY() + 5, 40, 40);
//			}
//		}
		
		if (tankInfo[0] != 0)
			tankImages[tankInfo[4] - 1][tankInfo[3] - 1].paintIcon(this, g, tankInfo[1], tankInfo[2]);
		for (int[] arr : bullets) {
			if (arr[0] != 0)
				bullet.paintIcon(this, g, arr[1], arr[2]);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Monospaced",Font.BOLD, 15));
		g.drawString("Scores", 700,30);

//		g.drawString("Player 1:  0", 670,60);
//		g.drawString("Player 2:  0", 670,90);
		
		g.drawString("Health", 700,150);
//		g.drawString("Player 1:  0", 670,180);
//		g.drawString("Player 2:  0", 670,210);
		
		
//		// draw solid bricks
//		br.drawSolids(this, g);
//		
//		// draw Breakable bricks	
//		br.draw(this, g);
	}
	
	private void setGraphics(Graphics g) {
		this.g = g;
	}
	
	private void setTankImages() {
		tankImages = new ImageIcon[4][4];
		BufferedImage temp;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				try {
					temp = ImageIO.read(Tank.class.getResourceAsStream("tank_" + i + "_direction_" + j + ".png"));
					tankImages[i - 1][j - 1] = new ImageIcon(temp);
				} catch (IOException e) {  System.out.println("Error loading tank images.");  }
			}
		}
	}
	
	private void setBulletImage() {
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(Tank.class.getResourceAsStream("bullet.png"));
		}
		catch (IOException e) {  System.out.println("Error loading tank images.");  }
		bullet = new ImageIcon(temp);
	}
	
	public void drawStuff(String response, int num) {
		tankInfo = new int[5];
		bullets = new int[3][3];
		String[] playerInfo = response.split(";");
		String[] tank = playerInfo[0].split(",");
		tankInfo[0] = Integer.parseInt(tank[0]); // tank info health
		tankInfo[1] = Integer.parseInt(tank[1]); // tank info x
		tankInfo[2] = Integer.parseInt(tank[2]); // tank info y
		tankInfo[3] = Integer.parseInt(tank[3]); // tank info dir
		tankInfo[4] = num; // tank info num
		for (int i = 1; i < playerInfo.length; i++) {
			String[] temp = playerInfo[i].split(",");
			bullets[i - 1][0] = 1;
			bullets[i - 1][1] = Integer.parseInt(temp[0]);
			bullets[i - 1][2] = Integer.parseInt(temp[1]);
		}
		repaint();
	}
	
	public void drawTank(int x, int y, int dir, int num) {
		tankInfo = new int[5];
		tankInfo[0] = x;
		tankInfo[1] = y;
		tankInfo[2] = dir;
		tankInfo[3] = num;
		tankInfo[4] = 1;
		repaint();
	}

	public void drawBullet(int x, int y) {
		bullet.paintIcon(this, g, x, y);
	}
	
}
