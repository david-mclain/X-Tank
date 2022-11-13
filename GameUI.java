/**
 * 
 * @author David McLain
 * 
 * GameUI class is used for any UI related things for client
 *
 */

package XTank;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI extends JFrame {
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 2785089710457159677L;
	private JPanel canvas;
	/**
	 * Instantiates new GameUI
	 */
	public GameUI() {
		canvas = new Canvas();
		this.setBounds(10, 10, 800, 630);
		this.setBackground(Color.gray);
		this.setResizable(false);
		this.add(canvas);
		this.setVisible(true);
	}
	/**
	 * Makes canvas draw information based on response from server
	 * @param response - response from server
	 */
	public void drawStuff(String response) {
		((Canvas) canvas).drawStuff(response);
	}
}
