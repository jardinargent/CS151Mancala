/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Class:	Player
*  File: 	Player.java
*  Description:	Class contains information about player 
*  Date:	5/2/2016
*  @author  Team we.excelAt(ood)
*  @version	1
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Player {

	/*
	 * Class used to create a player object for the game
	 */
	private int undos;
	private String name;
	private boolean isPlaying;
	private int playNum;
	/**
	 * Initializes new player with given name, and sets und count to 3
	 *
	 * @param n 	  Name of player
	 * @param playNum num of player (either 1 or 2)
	 */
	public Player(String n, int playNum) {
		this.playNum = playNum;
		name = n;
		undos = 3;
	}

	/**
	 * Reset undos back to original count to three
	 */
	public void resetUndos() {
		undos = 3;
	}

	/**
	 * updates num of undos 
	 */
	public void updateUndos() {
		undos--;
	}
	
	/**
	 * Returns num of undos 
	 * @return num of undos
	 */
	public int getUndos() {
		return undos;
	}
	
	/**
	 * Returns player name in String variable
	 *
	 * @return player's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the player number
	 * @return int (either 1 or 2)
	 */
	public int getPlayNum(){
		return playNum; 
	}

	/**
	 * if this player is current player
	 * @return true if player is currently playing
	 */
	public boolean isCurrentPlayer() {
		return isPlaying;
	}
	
	/**
	 * Sets current player
	 * @param isPlaying if this player is playing or not 
	 */
	public void setCurrentPlayer(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	/**
	 * Overrides equal method to compare players by name and num of undos
	 * @param other other player to compare to
	 */
	@Override
	public boolean equals(Object other) {
		Player o = (Player)other;

		if (this.name.equals(o.getName()) && this.undos == o.getUndos())
			return true;
		else return false;
	}
}
