package XTank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private ImageIcon[][] tankImages;
	private Graphics g;
	private BufferedImage image;
	private int[] tankInfo;
	private ImageIcon bullet;
	Canvas() {
		super();
		tankInfo = new int[4];
		repaint();
		//this.you = you;
		setTankImages();
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
		
		tankImages[tankInfo[3] - 1][tankInfo[2] - 1].paintIcon(this, g, tankInfo[0], tankInfo[1]);
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Monospaced",Font.BOLD, 15));
		g.drawString("Scores", 700,30);
//		Player[] players = game.getPlayers();
//		for (int i = 0; i < players.length; i++) {
//			Player player = players[i];
//			if (player != null) {
//				g.drawString("Player " + player.getPlayerNumber() + ":  0", 670, 60 + i * 30);
//			}
//		}
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
	
	public void drawTank(int x, int y, int dir, int num) {
		tankInfo[0] = x;
		tankInfo[1] = y;
		tankInfo[2] = dir;
		tankInfo[3] = num;
		repaint();
	}

	public void drawBullet(int x, int y) {
		bullet.paintIcon(this, g, x, y);
	}
	
}
