package XTank;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * A server for a multi-player tic tac toe game. Loosely based on an example in
 * Deitel and Deitel’s “Java How to Program” book. For this project I created a
 * new application-level protocol called TTTP (for Tic Tac Toe Protocol), which
 * is entirely plain text. The messages of TTTP are:
 *
 * Client -> Server MOVE <n> QUIT
 *
 * Server -> Client WELCOME <char> VALID_MOVE OTHER_PLAYER_MOVED <n>
 * OTHER_PLAYER_LEFT VICTORY DEFEAT TIE MESSAGE <text>
 */
public class Server {
	private static Game game;
	private static JFrame serverUI;
	private static JButton start;
	private static JButton startGame;
	private static JLabel serverIP;
	private static final int port = 58901;
	public static boolean gameStarted;
	public static void main(String[] args) throws Exception {
		createServerUI();
		gameStarted = true;
		startServer();
	}
	
	private static void createServerUI() throws UnknownHostException {
		String ip = InetAddress.getLocalHost().toString().split("/")[1];
		serverUI = new JFrame("XTank Server");
//		start = new JButton();
//		start.setFocusable(false);
//		start.setText("Start Server");
//		start.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		start.addActionListener(e -> startServer());
//		start.setBounds(25, 25, 150, 30);
//		startGame = new JButton();
//		startGame.setFocusable(false);
//		startGame.setText("Start Game");
//		startGame.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		startGame.addActionListener(e -> startGame());
//		startGame.setBounds(200, 25, 150, 30);
		serverIP = new JLabel();
		serverIP.setFont(new Font("Monospaced", Font.PLAIN, 16));
		serverIP.setBounds(40, 75, 300, 50);
		serverIP.setText("IP Address: " + ip + ":" + port);
		serverIP.setVisible(true);
//		serverUI.add(startGame);
//		serverUI.add(start);
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
				pool.execute(new Play(listener.accept(), new Player(game.getCurPlayer()), game));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void startGame() {
		gameStarted = true;
	}
}

class Play implements Runnable {
	private Timer timer;
	private Player player;
	private Game game;
	private Socket socket;

	public Play(Socket socket, Player player, Game game) {
		this.socket = socket;
		this.player = player;
		this.game = game;
		timer = new Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshAndSend();
			}
			
			
		});
		timer.start();
		game.addPlayer(player);
	}

	@Override
	public void run() {
		try {
			player.setInput(new DataInputStream(socket.getInputStream()));
			player.setOutput(new DataOutputStream(socket.getOutputStream()));
			player.getOutput().writeUTF("player " + player.getPlayerNumber());
			processCommands();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//public synchronized void process()

	private void processCommands() {
		while (true) {
			String command = "";
			try {
				command = player.getInput().readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (command.startsWith("QUIT"))
				return;
			else {
				processInput(command);
			}
		}
	}

	private void processInput(String command) {
		int x = Character.getNumericValue(command.charAt(8));
		if (x == 5 || (x <= 4 && game.moveInRange(x, player.getTank().getHitBox())))
			player.processInput(x);
	}

	protected void refreshAndSend() {
		player.update();
		game.refresh();
		DataOutputStream out = player.getOutput();
		Player[] players = game.getPlayers();
		String s = "";
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			if (p != null) {
				s += p.toString() + "+";
			}
		}
		try {
			out.writeUTF(s);
		} catch (IOException e) {}
	}
	
//	private void processMoveCommand(int location) 
//	{
//		try 
//		{
//			move(location, you);
//			you.getOutput().println("VALID_MOVE");
//			opponent.getOutput().println("OPPONENT_MOVED " + location);
//			if (true) 
//			{
//				you.getOutput().println("VICTORY");
//				opponent.getOutput().println("DEFEAT");
//			} 
//			else if (true) 
//			{
//				you.getOutput().println("TIE");
//				opponent.getOutput().println("TIE");
//			}
//		} 
//		catch (IllegalStateException e) 
//		{
//			you.getOutput().println("MESSAGE " + e.getMessage());
//		}
//	}
//
//	public synchronized void move(Player player) 
//	{
//		if (player != game.getCurrentPlayer()) {
//			throw new IllegalStateException("Not your turn");
//		} else if (game.getBoard()[location] != null) {
//			throw new IllegalStateException("Cell already occupied");
//		}
//		game.getBoard()[location] = game.getCurrentPlayer();
//		game.setCurrentPlayer(opponent);
//	}		
}