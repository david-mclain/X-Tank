package XTank;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI extends JFrame {
	private JPanel canvas;
	private Player cur;
	private Tank tank;
	public GameUI(String s, Player p) {
		super(s);
		this.cur = p;
		tank = cur.getTank();
		canvas = new Canvas(cur);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					move(1);
				}
				else if (e.getKeyCode() == KeyEvent.VK_D) {
					move(2);
				}
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					move(3);
				}
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					move(4);
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					move(10);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
		this.setBounds(10, 10, 800, 630);
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.add(canvas);
		this.setVisible(true);
		cur = p;
	}
	
	public void move(int x) {
		tank.processMove(x);
		canvas.repaint();
	}
}
