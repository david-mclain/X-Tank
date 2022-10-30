package XTank;

import java.awt.Color;
import java.awt.Dimension;
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g.clearRect(0, 0, 500, 800);
		g2.setColor(Color.WHITE);
	    Rectangle rect2 = new Rectangle(200, 200, 20, 20);

	    g2.rotate(Math.toRadians(45));
	    g2.draw(rect2);
	    g2.fill(rect2);
		g.setColor(Color.black);
		Rectangle r = new Rectangle(100, 100, 20, 20);
		Path2D.Double path = new Path2D.Double();
		path.append(r, false);

		AffineTransform t = new AffineTransform();
		t.rotate(45);
		path.transform(t);
		g2.draw(path);
		g.drawRect(rect2.x, rect2.y, rect2.height, rect2.height);
	}
}
