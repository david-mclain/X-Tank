package XTank;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI extends JFrame {
	JPanel canvas;
	public GameUI(String s) {
		super(s);
		canvas = new Canvas();
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
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
		this.setBounds(10, 10, 800, 630);
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.add(canvas);
		this.setVisible(true);
	}
	
	public void move(int x) {
		switch(x) {
		case 1:
			System.out.println("up");
			break;
		case 2:
			System.out.println("right");
			break;
		case 3:
			System.out.println("down");
			break;
		case 4:
			System.out.println("left");
			break;
		}
	}
}
