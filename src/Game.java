import java.util.ArrayList;
import java.util.LinkedList;
/**
 * 
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 *
 */
public class Game {
	public int numPlayers;
	public ArrayList<Card> commCards;
	public LinkedList<Player> players;
	private int stakes;
	private int pot;
	
	/**
	 * Creates a new game with a specific number of players
	 * and initializes each player
	 * @param players number of players
	 */
	public Game(int numHuman, int numComp) {
		players = new LinkedList<>();
		numPlayers = numHuman + numComp;
		
		//Adds human players
		for (int i = 0; i < numHuman; i++) {
			players.add(new Player("Player" + (i + 1)));
		}
		
		//Adds computer players
		for (int i = 0; i < numComp; i++) {
			players.add(new ComputerOpponent());
		}
		players.get(0).isDealer = true;
		stakes = 0;
		pot = 0;
		commCards = new ArrayList<>();
	}
	
	/** 
	 * plays the next hand
	 */
	public void play() {
		System.out.println("Welcome to Poker! Let's deal out the cards.");
		Deck deck = new Deck();
		deck.shuffle();
		//This sorts the LinkedList of players so that the dealer is first in 
		//queue in order to assign the small and big blinds
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isDealer) {
				players.add(players.remove(i));
				i--;
			} else {
				break;
			}
		}
		

		deal(deck);
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).name + " has the cards " + players.get(i).toString());
		}
		System.out.print("Let's begin the betting round.");
		firstBet();
	}
	
	/**
	 * This may not be needed with the getWinner method in Score class.
	 * figures out which player won that hand
	 * @return player that won
	 */
//	public Player getWinner() {
//		for (int i = 0; i < players.size(); i++) {
//			if players.get(i).is
//		}
//	}
	
	
	private void firstBet() {
		
	}
	
	private void bet() {
		
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
