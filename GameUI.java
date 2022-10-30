package XTank;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI {
	JPanel canvas;
	JFrame shell;
	public GameUI() {
		shell = new JFrame("X-Tank");
		shell.setTitle("X-Tank");
		canvas = new Canvas();
		shell.add(canvas);
		shell.pack();
		shell.setVisible(true);
		shell.setSize(800, 700);
		canvas.repaint();
	}
}
