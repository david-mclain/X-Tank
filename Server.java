package XTank;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;

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
    public static void main(String[] args) throws Exception 
    {
        try (var listener = new ServerSocket(58901)) 
        {
            System.out.println("X-Tank server is running...");
            var pool = Executors.newFixedThreadPool(200);
            while (true) 
            {
                game = Game.getGame();
//                pool.execute(new Play(listener.accept(), new Player('X'), game));
//                pool.execute(new Play(listener.accept(), new Player('O'), game));
            }
        }
    }
}
/*

class Player
{
	private char mark;
	Scanner input;
	PrintWriter output;
	
	
	public Player(char mark) {this.mark = mark;}
	public char getMark() {return mark;}
	public void setInput(Scanner input) { this.input = input;}
	public void setOutput(PrintWriter output) {this.output = output;}
	public Scanner getInput() {return input;}
	public PrintWriter getOutput() {return output;}
}

    /**
     * A Player is identified by a character mark which is either 'X' or 'O'. For
     * communication with the client the player has a socket and associated Scanner
     * and PrintWriter.
     */
class Play implements Runnable {
	Player you;
	Player opponent=null;
	Game game;
	Socket socket;

	public Play(Socket socket, Player player, Game game) 
	{
		this.socket = socket;
		this.you = player;
		this.game = game;
//		game.setCurrentPlayer(you);
//		if (game.getPlayer1() == null)
//			game.setPlayer1(you);
//		else
//			game.setPlayer2(you);
	}

	@Override
	public void run() 
	{
		try 
		{
//			you.setInput(new Scanner(socket.getInputStream()));
//			you.setOutput(new PrintWriter(socket.getOutputStream(), true));
//			you.getOutput().println("WELCOME " + you.getMark());
//			processCommands();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	private void processCommands() 
	{
//		while (you.getInput().hasNextLine()) 
//		{
//			if (you == game.getPlayer1()) opponent = game.getPlayer2();
//			else opponent = game.getPlayer1();
//			
//			var command = you.getInput().nextLine();
//			if (command.startsWith("QUIT"))
//				return;
//			else if (command.startsWith("MOVE"))
//				processMoveCommand(Integer.parseInt(command.substring(5)));
//		}
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
//	public synchronized void move(int location, Player player) 
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