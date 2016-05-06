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
	private Player player1, player2, currentPlayer;
	private boolean gameOver;
	private int[] board, cloneForRestart, cloneForUndo;
	ArrayList<ChangeListener> listeners;
	private int currentPit, stonesInHand;
	private List<MancalaGame> games;
	private BoardStyle boardStyle = null;

	/*
	 * Creates generic layout for Mancala game, having 6 pits on each side, one
	 * scoring pit for each player and initializing all pits to value of 0. Pit
	 * numbering in array is as follows:
	 *  13   12 11 10 9 8 7         {Player 2}
	 *        0  1  2 3 4 5    6    {Player 1}
	 *  Pits #6 and #13 are scoring pits.
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
	 * @param p2 name of player 2
	 *
	 * @param stonesInEachPit number of stones in each pit prior to game
	 * starting
	 */
	public void newGame(String p1, String p2, int stonesInEachPit) {
		int originalCount = stonesInEachPit;
		for (int i = 0; i < board.length; i++) {
			if (i == 6 || i == 13) {
				continue;
			} else {
				board[i] = originalCount;
			}
		}
		player1 = new Player(p1);
		player2 = new Player(p2);
		currentPlayer = player1;
		cloneForRestart = board.clone(); // save board layout for restart button
		printBoard();
		System.out.println("It is now " + currentPlayer.getName() + "'s turn");
	}

	/*
	 * Checks if current player is player1 return true if player1, false
	 * otherwise
	 */
	public boolean isPlayer1() {
		return currentPlayer.equals(player1);
	}

	/*
	* Move method, used for initial move where a pit is selected
	* @param pit pit which was selected by user
	*/
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
		checkEndGame();
		if (gameOver) {
			determineWinner();
		}
	}

	/*
	 * Move to method, used for traversing mancala board after picking up stones from initial pit
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
		if (isValid() && board[currentPit] == 0) {              				// landed on empty of
			// pit of own side,
			// so bonus!
			bonusStones();
		}
		//check if extra turn is earned
		if (extraTurn()) {
			board[6]++;
			stonesInHand = 0;
			System.out.println("Extra turn!");
		}
		// no bonus or extra turn, so drop last stone in pit and turn is over
		else {
			board[currentPit]++;
			stonesInHand = 0;
			endTurn();
		}
	}

	/*
	 * Checks if player has earned an extra turn by landing in own mancala on final stone
	 *
	 * return true if landed in own mancala pit, false otherwise
	 */
	public boolean extraTurn() {
		if (isPlayer1() && currentPit == 6 || !isPlayer1() && currentPit == 13) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Player landed in empty pit of own side. Gets the final stone he had in
	 * hand plus pit directly across, places total in own mancala
	 */
	public void bonusStones() {
		int bonus = 1; // final pit player had when landing on empty pit
		bonus += board[12 - currentPit]; // (12 - currentPit) will get us pit directly across board
		board[12 - currentPit] = 0;
		if (isPlayer1()) { // if player one, place bonus stones in pit 6
			board[6] += bonus;
		} else { // if player 2, place stones in pit 13
			board[13] += bonus;
		}
	}

	/*
	 * Used to check that current pit is valid, meaning on current player's side
	 * of board
	 *
	 * return true if current pit is on current player's side of board excluding scoring pits, false otherwise
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
		if (currentPlayer.getUndos() > 0) {
			board = cloneForUndo;
			printBoard();
		}
	}

	/*
	 * Helper method used to check if game is over
	 *
	 * return true if either player side pits are completely empty, false otherwise
	 */
	public void checkEndGame() {
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
		}
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
		String winner = "";
		if (board[6] > board[13])
			winner = player1.getName();
		else if (board[6] < board[13])
			winner = player2.getName();
		else {
			System.out.println("The game is a tie");
			return;
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
		currentPlayer.resetUndos();
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
		printBoard();
		System.out.println("It is now " + currentPlayer.getName() + "'s turn");

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
				if (gameOver) {
					//notify no need to save completed game
				} else {
					games.add(this);
					output.writeObject(games);
				}
			} finally {
				output.close();
			}
		} catch (IOException e) {
			System.out.println("Save not successful");
		}
	}

	/*
	 * Load games on file
	 */
	public void load() throws IOException, ClassNotFoundException {
		try {
			// use buffering
			File gamesInFile = new File("games.txt");
			if (!gamesInFile.exists())
				System.out.println("First run");
			else {
				InputStream file = new FileInputStream(gamesInFile);
				InputStream buffered = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffered);
				try {
					// deserialize the List
					ArrayList<MancalaGame> gamesFromFile = (ArrayList<MancalaGame>) input.readObject();
					games = gamesFromFile;
				} finally {
					input.close();
				}
			}
		} finally {
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

	public void setBoardStyle(BoardStyle style) {
		boardStyle = style;
	}

	public BoardStyle getChosenStyle() {
		return boardStyle;
	}

}// end of mancala class

