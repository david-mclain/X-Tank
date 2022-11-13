package XTank;

import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.net.BindException;
import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Server {
	private static Game game;
	private static JFrame serverUI;
	private static JLabel serverIP;
	private static final int port = 58901;
	public static boolean gameStarted;
	public static void main(String[] args) {
		try {
			createServerUI();
		} catch (UnknownHostException e) {}
		gameStarted = true;
		startServer();
	}
	
	private static void createServerUI() throws UnknownHostException {
		String ip = InetAddress.getLocalHost().toString().split("/")[1];
		serverUI = new JFrame("XTank Server");
		serverIP = new JLabel();
		serverIP.setFont(new Font("Monospaced", Font.PLAIN, 16));
		serverIP.setBounds(40, 75, 300, 50);
		serverIP.setText("IP Address: " + ip + ":" + port);
		serverIP.setVisible(true);
		serverUI.add(serverIP);
		serverUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverUI.setLayout(null);
		serverUI.setSize(400, 400);
		serverUI.setVisible(true);
	}
	
	private static void startServer() {
		try (var listener = new ServerSocket(port)) {
			listener.setSoTimeout(30000);
			var pool = Executors.newFixedThreadPool(4);
			game = Game.getGame();
			while (true) {
				pool.execute(new ServerPlay(listener.accept(), new Player(game.getCurPlayer()), game));
			}
		} 
		catch (BindException e) {
			JOptionPane.showMessageDialog(serverUI, "Error! Address is already in use");
		}
		catch (SocketTimeoutException e) {
			JOptionPane.showMessageDialog(serverUI, "Warning! Server no longer accpepting new players.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}