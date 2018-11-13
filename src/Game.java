import java.util.ArrayList;
/**
 * 
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 *
 */
public class Game {
	public int numPlayers;
	private ArrayList<Card> commCards;
	private ArrayList<Player> players;
	
	/**
	 * Creates a new game with a specific number of players
	 * and initializes each player
	 * @param players number of players
	 */
	public Game(int numPlayers) {
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player());
		}
	}
	
	/** 
	 * plays the next hand
	 */
	public void play() {
		
	}
	
	/**
	 * figures out which player won that hand
	 * @return player that won
	 */
	public Player getWinner() {
		return null;
	}
	
	/**
	 * deals cards from the deck into the player's hands
	 */
	public void deal(Deck deck) {
		for (int i = 0; i < 2; i++) {
			for (Player currentPlayer : players) {
				currentPlayer.hand.add(deck.draw());
			}
		}
	}

	/**
	 * 
	 * @return the community cards
	 */
	public ArrayList<Card> getCommCards() {
		return commCards;
	}
	
}
