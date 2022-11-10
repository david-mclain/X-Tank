package XTank;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameUI extends JFrame {
	private JPanel canvas;
	private Player player;
	private Timer timer;
	private Game game;
	public GameUI(String s, Player p) {
		super(s);
		this.player = p;
		canvas = new Canvas(player);
		game = Game.getGame();
		timer = new Timer(16, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.update();
				game.refresh();
				canvas.repaint();
				
			}
		});
		timer.start();
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
	}
	
	public void move(int x) {
		player.processMove(x);
		game.refresh();
		canvas.repaint();
	}
}
