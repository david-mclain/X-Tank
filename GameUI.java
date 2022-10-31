package XTank;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI extends JFrame {
	JPanel canvas;
	public GameUI(String s) {
		super(s);
		canvas = new Canvas();
		this.setBounds(10, 10, 800, 630);
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.add(canvas);
		this.setVisible(true);
	}
}
