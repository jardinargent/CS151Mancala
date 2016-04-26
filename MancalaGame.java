import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaGame implements Serializable {
	private Player player1, player2, currentPlayer, winner;
	private boolean gameOver;
	private int[] board, cloneForRestart, cloneForUndo;
	ArrayList<ChangeListener> listeners;
	private int currentPit, stonesInHand;
	private List<MancalaGame> games;

	/*
	 * Creates generic layout for Mancala game, having 6 pits on each side, one
	 * scoring put for each player and initializing all pits to value of 0 Pit
	 * numbering in out array is as follows: 13 12 11 10 9 8 7 {Player 2} 0 1 2
	 * 3 4 5 6 {Player 1} Pits #6 and #13 are scoring pits.
	 */
	public MancalaGame() {
		// promptUser board style
		// user inputs player names and stones per pit
		board = new int[14];
		for (int i = 0; i < board.length; i++) {
			board[i] = 0;
		}
		gameOver = false;
		games = new ArrayList<MancalaGame>();
	}

	/*
	 * Start a new game with given players and stones in each pit
	 * 
	 * @param p1 name of player 1
	 * 
	 * @param p2 name of player 2
	 * 
	 * @param stonesInEachPit number of stones in each pit prior to game
	 * starting
	 */
	public void newGame(String p1, String p2, int stonesInEachPit) {
		int originalCount = stonesInEachPit;
		for (int i = 0; i < board.length; i++) {
			if (i == 6 || i == 13) {
				board[i] = 0;
			}
			else {
				board[i] = originalCount;
			}
		}
		player1 = new Player(p1);
		player2 = new Player(p2);
		currentPlayer = player1;
		cloneForRestart = board.clone(); // save board layout for restart button
		printBoard();
		System.out.println("It is now " + currentPlayer.name + "'s turn");
	}

	/*
	 * Checks if current player is player1 return true if player1, false
	 * otherwise
	 */
	public boolean isPlayer1() {
		return currentPlayer == player1;
	}

	public void move(int pit) {
		cloneForUndo = board.clone(); // clone in case player chooses to
		// undo
		stonesInHand = 0;
		currentPit = pit;
		// check if player chose valid side
		while (!isValid()) { // check if valid move
			invalidMovePrompt();
		}
		stonesInHand = board[currentPit];
		resetPit();
		moveTo(stonesInHand, ++currentPit);
		// return;
	}

	/*
	 * Move method
	 * 
	 * @param stonesInHand stones currently in hand
	 * 
	 * @param pit number of pit
	 */
	public void moveTo(int stonesInHand, int pit) {
		printBoard();
		currentPit = pit;
		if (currentPit == 14)
			currentPit = 0; // loops pitNum to stay between 0 and 13
		// complete player's turn
		if (isPlayer1() && currentPit == 13 || !isPlayer1() && currentPit == 6) { // player
																					// will
																					// skip
																					// other
																					// player's
																					// scoring
																					// pit
			moveTo(stonesInHand, ++currentPit);
			// return;
		}
		while (stonesInHand > 1) {
			board[currentPit]++;
			stonesInHand--;
			moveTo(stonesInHand, ++currentPit);
			return;
		}

		// dropping last stone, so check if landing in own scoring pit or
		// side for bonus turn or stones
		if (isValid() && board[currentPit] == 0) { // landed on empty of
													// pit of own side,
													// so bonus!
			bonusStones();
		}
		if (extraTurn()) { // we landed in own mancala pit, so drop last
							// stone and go again!
			board[6]++;
			stonesInHand = 0;
			System.out.println("Extra turn!");
		}
		else { // no bonus or extra turn, so drop last stone in pit and turn
				// is over
			board[currentPit]++;
			stonesInHand = 0;
			endTurn();
		}
	}

	/*
	 * Checks if player has earned an extra turn return true if landed in own
	 * mancala pit, false otherwise
	 */
	public boolean extraTurn() {
		if (isPlayer1() && currentPit == 6)
			return true;
		return false;
	}

	/*
	 * Player landed in empty pit of own side. Gets the final stone he had in
	 * hand plus pit directly across
	 */
	public void bonusStones() {
		int bonus = 1; // final pit player had when landing on empty pit
		bonus += board[12 - currentPit]; // pit 12 is final available pit to
											// take from, 13 not allowed
		board[12 - currentPit] = 0;
		if (isPlayer1()) { // if player one, place bonus stones in pit 6
			board[6] += bonus;
		}
		else { // if player 2, place stones in pit 13
			board[13] += bonus;
		}
	}

	/*
	 * Used to check that current pit is valid, meaning on current player's side
	 * of board return true if current pit is on current player's side of board
	 * excluding scoring pits, false otherwise
	 */
	public boolean isValid() {
		if (isPlayer1() && currentPit < 7 && currentPit != 6)
			return true; // check appropriate side of board, excluding scoring
							// pit
		else if (!isPlayer1() && currentPit >= 7 && currentPit != 13)
			return true;
		else {
			return false;
		}
	}

	/*
	 * Undo move to previous state
	 */
	public void undo() {
		if (currentPlayer.undos > 0) {
			board = cloneForUndo;
			printBoard();
		}
	}

	/*
	 * Helper method used to check if game is over return true either player
	 * side pits are completely empty
	 */
	public boolean checkEndGame() {
		int total = 0;
		int total2 = 0;
		for (int i = 0; i < 6; i++) {
			total += board[i];
		}
		for (int i = 7; i < 13; i++) {
			total2 += board[i];
		}

		if (total == 0 || total2 == 0) {
			gameOver = true;
			return true;
		}
		return false;
	}

	/*
	 * Resets pit
	 */
	public void resetPit() {
		board[currentPit] = 0;
	}

	/*
	 * Method used to calculate winner and print to screen
	 */
	public void determineWinner() {
		// use array or have counter??
		if (board[6] > board[13])
			winner = player1;
		else if (board[6] < board[13])
			winner = player2;
		else {
			System.out.println("The game is a tie");
		}
		System.out.println(winner + " is the winner");
	}

	/*
	 * Begin current game from start state, with same settings and players
	 */
	public void restart() {
		board = cloneForRestart;
	}

	/*
	 * Switch players turn, resetting undos as we switch
	 */
	public void endTurn() {
		checkEndGame();
		if (gameOver) {
			determineWinner();
			return;
		}
		else {
			currentPlayer.resetUndos();
			if (currentPlayer == player1) {
				currentPlayer = player2;
			}
			else {
				currentPlayer = player1;
			}
			printBoard();
			System.out.println("It is now " + currentPlayer.name + "'s turn");
		}
	}

	/*
	 * Returns current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/*
	 * Attach a listener to the model
	 * 
	 * @param c change listener to be attached
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}

	/*
	 * Choose pit for move param pit pit number that was chosen for move
	 */
	public void update(int pit) {
		move(pit);
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}

	/*
	 * Save current state of game
	 */
	public void save() {
		try {
			// use buffering
			OutputStream file = new FileOutputStream("games.txt");
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try {
				output.writeObject(games);
			}
			finally {
				output.close();
			}
		}
		catch (IOException ex) {
			System.out.println("Not successful");
		}
	}

	public void load() throws IOException, ClassNotFoundException {
		try {
			// use buffering
			File gamesInFile = new File("games.txt");
			if (!gamesInFile.exists())
				System.out.println("This is the first run!");
			else {
				InputStream file = new FileInputStream(gamesInFile);
				InputStream buffered = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffered);
				try {
					// deserialize the List
					ArrayList<MancalaGame> gamesFromFile = (ArrayList<MancalaGame>) input.readObject();
					games = gamesFromFile;
				}
				finally {
					input.close();
				}
			}
		}
		finally {
			System.out.println("Load successful");
		}
	}

	/*
	 * Prints simple laout to console for testing
	 */
	public void printBoard() {
		System.out.println();
		int i;
		for (i = 0; i < 7; i++) {
			System.out.print(board[13 - i] + " ");
		}
		System.out.println();
		System.out.printf("  ");
		while (i < 14) {
			System.out.print(board[i - 7] + " ");
			i++;
		}
		System.out.println();
	}

	public void invalidMovePrompt() {
		System.out.println("Invalid move. Please try again");
	}

	/*
	 * Subclass used to create a player object for the game
	 */
	class Player {
		private int undos;
		private String name;

		/*
		 * Initializes new player with given name, and sets und count to 3
		 * 
		 * @param n Name of player
		 */
		public Player(String n) {
			name = n;
			undos = 3;
		}

		/*
		 * Reset undos back to original count to three
		 */
		public void resetUndos() {
			undos = 3;
		}

		/*
		 * Returns player name in String variable
		 * 
		 * @return player's name
		 */
		public String getName() {
			return name;
		}
	} // end of player class
}// end of mancala class
