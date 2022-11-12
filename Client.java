package XTank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Client {
	private JFrame frame;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private int playerNumber;
	private final int port = 58901;
		
	public Client(String serverAddress) throws IllegalArgumentException {
		connect(serverAddress);
	}
	
	private void connect(String serverAddress) {
		try {
			socket = new Socket(serverAddress, port);
			in = new DataInputStream(socket.getInputStream());
//			in = new Scanner(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createUI() {
		frame = new GameUI();
		frame.setTitle("XTank: Player " + playerNumber);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					process(1);
				}
				else if (e.getKeyCode() == KeyEvent.VK_D) {
					process(2);
				}
				else if (e.getKeyCode() == KeyEvent.VK_S) {
					process(3);
				}
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					process(4);
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					process(5);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}
	
	private void process(int i) {
		try {
			out.writeUTF("command:" + i + ",player:" + playerNumber);
		} catch (IOException e) {}
	}

	public void play() throws Exception {
		try {
			String response = in.readUTF();
			System.out.println(response);
			int mark = Character.getNumericValue(response.charAt(7));
			playerNumber = mark;
			createUI();
			System.out.println("created ui");
			while (true) {
				response = in.readUTF();
//				System.out.println(response);
				if (response.startsWith("Game not")) {
					JOptionPane.showMessageDialog(frame, "Game not yet started!");
				}
				else {
					drawStuff(response);
				}
//				else if (response.startsWith("bullet")) {
//					drawBullet(response);
//				}
//				else if (response.startsWith("tank")) {
//					drawTank(response);
//				}
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
			out.writeUTF("QUIT");
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			socket.close();
			frame.dispose();
		}
	}
	
	private void drawStuff(String response) {
		((GameUI) frame).drawStuff(response, playerNumber);
		
	}

	public static void main(String[] args) {
		try {
			Client client = new Client("127.0.0.1");
			client.play();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error. Too many players in server.");
		}
	}
}

