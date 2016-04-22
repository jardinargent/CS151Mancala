
public class Player {

	private boolean isNext; 
	private int undos; 
	
	public Player(){ 
		isNext = false;  
		undos = 3; 
	}
	
	public void isNext(){ 
		isNext = true; 
	}
	
	public boolean isPlaying(){ 
		return isNext; 
	}

	public void updateUndos(){ 
		if(undos > 0)
			undos--; 	
	}

	public int getUndos(){ 
		return undos; 
	}

	public boolean hasUnods(){ 
		return undos>0 ? true:false; 
	}
}
