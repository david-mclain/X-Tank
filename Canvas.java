package XTank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
//	private BufferedImage image;
//	private ImageIcon icon;
//	private Player you;
	Canvas(Player you) {
		super();
		//this.you = you;
		repaint();
	}
	
	public void paint(Graphics g) {
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
	
}
