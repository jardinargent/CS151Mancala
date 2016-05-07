public class Player {

	/*
	 * Class used to create a player object for the game
	 */
	private int undos;
	private String name;
	private boolean isPlaying;
	private int playNum;
	/*
	 * Initializes new player with given name, and sets und count to 3
	 *
	 * @param n Name of player
	 */
	public Player(String n, int playNum) {
		this.playNum = playNum;
		name = n;
		undos = 3;
	}

	/*
	 * Reset undos back to original count to three
	 */
	public void resetUndos() {
		undos = 3;
	}

	public void updateUndos() {
		undos--;
	}
	public int getUndos() {
		return undos;
	}
	/*
	 * Returns player name in String variable
	 *
	 * @return player's name
	 */
	public String getName() {
		return name;
	}
	
	public int getPlayNum(){
		return playNum; 
	}

	public boolean isCurrentPlayer() {
		return isPlaying;
	}

	public void setCurrentPlayer(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	@Override
	public boolean equals(Object other) {
		Player o = (Player)other;

		if (this.name.equals(o.getName()) && this.undos == o.getUndos())
			return true;
		else return false;
	}
}
