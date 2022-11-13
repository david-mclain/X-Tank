/**
 * 
 * @author David McLain
 * 
 * Server play class is used to create new thread for each client to process information
 *
 */

package XTank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.Timer;

public class ServerPlay implements Runnable {
	private Timer timer;
	private Player player;
	private Game game;
	private Socket socket;
	/**
	 * Instantiates new ServerPlay
	 * @param socket - socket of client connected to
	 * @param player - player of client
	 * @param game - game to send information to from client
	 */
	public ServerPlay(Socket socket, Player player, Game game) {
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
	/**
	 * Run method of thread
	 */
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
	/**
	 * Processes all information recieved from client
	 */
	private void processCommands() {
		while (true) {
			String command = "";
			try {
				command = player.getInput().readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (command.startsWith("QUIT"))
				return;
			else {
				processInput(command);
			}
		}
	}
	/**
	 * Processes input from client
	 * @param command - what client sent
	 */
	private void processInput(String command) {
		int x = Character.getNumericValue(command.charAt(8));
		if (x == 5 || (x <= 4 && game.moveInRange(x, player.getTank().getHitBox())))
			player.processInput(x);
	}
	/**
	 * Refreshes game and player 60 times per second
	 */
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
		boolean win = game.checkWin(player);
		try {
			if (player.getHealth() <= 0) {
				out.writeUTF("died");
				game.killPlayer(player);
			}
			else if (win)
				out.writeUTF("win");
			else
				out.writeUTF(s);
		} catch (IOException e) {}
	}	
}