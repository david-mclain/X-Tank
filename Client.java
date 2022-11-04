package XTank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client {
	private JFrame frame;
	private Game game;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	private static Player you;
	
	public Client(String serverAddress) throws Exception {
		game = Game.getGame();
		you = new Player(game.getCurPlayer());
		game.addPlayer(you);
		frame = new GameUI("X-Tank", you);
//		socket = new Socket(serverAddress, 58901);
//		in = new Scanner(socket.getInputStream());
//		out = new PrintWriter(socket.getOutputStream(), true);

//		messageLabel.setBackground(Color.lightGray);
//		frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);

//		var boardPanel = new JPanel();
//		boardPanel.setBackground(Color.black);
//		boardPanel.setLayout(new GridLayout(3, 3, 2, 2));
//		for (var i = 0; i < board.length; i++) {
//			final int j = i;
//			board[i] = new Square();
//			board[i].addMouseListener(new MouseAdapter() {
//				public void mousePressed(MouseEvent e) {
//					currentSquare = board[j];
//					out.println("MOVE " + j);
//				}
//			});
//			boardPanel.add(board[i]);
//		}
		//frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
	}
	
	public void play() throws Exception {
		try {
			var response = in.nextLine();
			var mark = response.charAt(8);
			var opponentMark = mark == 'X' ? 'O' : 'X';
			frame.setTitle("Tic Tac Toe: Player " + mark);
			while (in.hasNextLine()) {
				response = in.nextLine();
//				if (response.startsWith("VALID_MOVE")) {
//					messageLabel.setText("Valid move, please wait");
//					currentSquare.setText(mark);
//					currentSquare.repaint();
//				} 
//				else if (response.startsWith("OPPONENT_MOVED")) {
//					var loc = Integer.parseInt(response.substring(15));
//					board[loc].setText(opponentMark);
//					board[loc].repaint();
//					messageLabel.setText("Opponent moved, your turn");
//				} 
//				else if (response.startsWith("MESSAGE")) {
//					messageLabel.setText(response.substring(8));
//				} 
				if (response.startsWith("VICTORY")) {
					JOptionPane.showMessageDialog(frame, "Winner Winner");
					break;
				} 
				else if (response.startsWith("DEFEAT")) {
					JOptionPane.showMessageDialog(frame, "Sorry you lost");
					break;
				} 
				else if (response.startsWith("TIE")) {
					JOptionPane.showMessageDialog(frame, "Tie");
					break;
				} 
				else if (response.startsWith("OTHER_PLAYER_LEFT")) {
					JOptionPane.showMessageDialog(frame, "Other player left");
					break;
				}
			}
			out.println("QUIT");
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			socket.close();
			frame.dispose();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Client client = new Client("127.0.0.1");
		
	}
}

