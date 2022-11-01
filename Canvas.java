package XTank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private Game game;
	private BufferedImage image;
	private ImageIcon icon;
	Canvas() {
		super();
		game = Game.getGame();
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
		
		List<GameObject> gameObjects = game.getGameObjects();
//		for (GameObject obj : gameObjects) {
//			obj.getImage().paintIcon(this, g, obj.getX(), obj.getY());
//		}
		getImage(1);
		icon.paintIcon(this, g, 50, 50);
		getImage(2);
		icon.paintIcon(this, g, 100, 50);
		getImage(3);
		icon.paintIcon(this, g, 150, 50);
		getImage(4);
		icon.paintIcon(this, g, 200, 50);
		
		g.setColor(Color.white);
		g.setFont(new Font("Monospaced",Font.BOLD, 15));
		g.drawString("Scores", 700,30);
		g.drawString("Player 1:  0", 670,60);
		g.drawString("Player 2:  0", 670,90);
		
		g.drawString("Lives", 700,150);
		g.drawString("Player 1:  0", 670,180);
		g.drawString("Player 2:  0", 670,210);
		
		
//		// draw solid bricks
//		br.drawSolids(this, g);
//		
//		// draw Breakable bricks	
//		br.draw(this, g);
	}
	
	public void getImage(int x) {
		try {
			image = ImageIO.read(Canvas.class.getResourceAsStream("tank_1_direction_" + x + ".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		icon = new ImageIcon(image);
	}
	
}
