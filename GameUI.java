package XTank;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

	public void drawStuff(String response, int playerNumber) {
		((Canvas) canvas).drawStuff(response);
	}

	public void drawStuff(String response) {
		((Canvas) canvas).drawStuff(response);
	}
}
