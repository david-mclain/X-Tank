package XTank;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Player[] players;
	private int curPlayers;
	private List<GameObject> gameObjects;
	private static Game game;
	
	private Game() {
		curPlayers = 0;
		gameObjects = new ArrayList<>();
	}
	
	public static Game getGame() {
		if (game == null) {
			game = new Game();
		}
		return game;
	}
	
	public int getCurPlayer() {  return curPlayers + 1;  }
	
	public void addPlayer(Player player) {
		players[curPlayers++] = player;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	/*
	 * class Game 
{
	// Board cells numbered 0-8, top to bottom, left to right; null if empty
	private Player[] board = new Player[9];
	private Player player1 = null;
	private Player player2 = null;
	private Player currentPlayer = null;
	
	public Player[] getBoard() {return board;}
	public Player getCurrentPlayer() {return currentPlayer;}
	public void setCurrentPlayer(Player player) {currentPlayer = player;}
	public Player getPlayer1(){return player1;}
	public void setPlayer1(Player player) {player1 = player;}
	public Player getPlayer2(){return player2;}
	public void setPlayer2(Player player) {player2= player;}
}
	 * 
	 */
}
