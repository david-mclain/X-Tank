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
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	Canvas() {
		super();
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
		
		
		
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
	
}
