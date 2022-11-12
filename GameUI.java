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
	private Game game;
	public GameUI() {
		canvas = new Canvas();
		game = Game.getGame();
		this.setBounds(10, 10, 800, 630);
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.add(canvas);
		this.setVisible(true);
	}
	
	public void move(int x) {
		game.refresh();
		canvas.repaint();
	}
	
	public void drawTank(int x, int y, int dir, int num) {
		((Canvas) canvas).drawTank(x, y, dir, num);
	}
	
	public void drawBullet(int x, int y) {
		((Canvas) canvas).drawBullet(x, y);
	}

	public void drawStuff(String response, int playerNumber) {
		((Canvas) canvas).drawStuff(response, playerNumber);
	}
}
