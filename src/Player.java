import java.util.ArrayList;
/**
 * 
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 *
 */
public class Player {
	public ArrayList<Card> hand;
	public int money;
	
	/**
	 * Creates a blank player with no cards or money
	 */
	public Player() {
		money = 500;
	}
	
	/**
	 * Raises the current bid by ammount
	 * @param ammount ammount to raise the bid by
	 */
	public void raise(int ammount) {
		
	}
	
	/**
	 * Match the current bid
	 */
	public void call() {
		
	}
	
	/**
	 * fold the hand
	 */
	public void fold() {
		
	}
	
	/**
	 * pass
	 */
	public void check() {
		
	}
	/* replaced by score class
	/**
	 * compares this player and other player to determine who wins
	 * @param other the player to compare
	 * @return true if this player is better than other player
	 *\/
	public boolean isBetter(Player other) {
		return true;
	}*/
}
