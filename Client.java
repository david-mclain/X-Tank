package XTank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
			int mark = Character.getNumericValue(response.charAt(7));
			playerNumber = mark;
			createUI();
			while (true) {
				response = in.readUTF();
				if (response.startsWith("Game not")) {
					JOptionPane.showMessageDialog(frame, "Game not yet started!");
				}
				else if (response.startsWith("win")){
					JOptionPane.showMessageDialog(frame, "You win!");
					break;
				}
				else if (response.startsWith("died")) {
					JOptionPane.showMessageDialog(frame, "You died!");
					break;
				}
				else {
					drawStuff(response);
				} 
			}
			out.writeUTF("QUIT");
		} 
		catch (SocketException e) {
			JOptionPane.showMessageDialog(frame, "Error. Connection to server closed");
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
		((GameUI) frame).drawStuff(response);
	}

	public static void main(String[] args) {
		try {
			Client client = new Client("127.0.0.1");
			client.play();
		}
		catch (ConnectException e) {
			JOptionPane.showMessageDialog(null, "Error connecting to server.");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error. Too many players in server.");
		}
	}
}

