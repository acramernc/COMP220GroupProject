import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 *
 */
public class Game {
	public int numPlayers;
	private ArrayList<Card> commCards;
	//We use a LinkedList because we need to keep the order of players 
	//for bidding and assigning small and big blinds
	private LinkedList<Player> players;
	//Stakes is what the bid is currently at per player
	private int stakes;
	//private Iterator<Player> playerIter;
	
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
		
		//First player is by default the first dealer
		players.get(0).isDealer = true;
		
		//playerIter = players.iterator();
		
		
		commCards = new ArrayList<>();
	}
	
	/** 
	 * plays the next hand
	 */
	public void playHand() {
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
		
		//Resets everyone to not being folded and says what everyone has
		for (int i = 0; i < players.size(); i++) {
			players.get(i).hasFolded = false;
			System.out.println(players.get(i).name + " has the cards " + players.get(i).toString());
		}
		
		//Assigns the small and big blinds, which are forced to start the bidding
		nextPlayer();
		Player smallBlind = nextPlayer();
		Player bigBlind = nextPlayer();
		
		smallBlind.currentBid += 25;
		bigBlind.currentBid += 50;
		stakes = 50;
				
		System.out.println("Let's begin the first betting round. The small and big blinds have been assigned.");
		bet();
		
		for (int i = 0; i < 3; i++) {
			commCards.add(deck.draw());
		}
		bet();
		commCards.add(deck.draw());
		bet();
		commCards.add(deck.draw());
		bet();
	}

	
	/**
	 * this starts a betting round,getting bets from each player, and goes until isBettingFunction returns false
	 */
	private void bet() {
		System.out.println("The pot is currently at " + getPot() + " dollars");
		Player current;
		//Will do this until everyone stops raising the stakes
		do {

			current = nextPlayer();
			
			//Only does this for human players
			if (!current.isComp) {
				for (Player currentPlayer : players) {
					System.out.println(currentPlayer.name + " has bid " + currentPlayer.currentBid);
				}
				
				//Will only show community cards to human players if there are community cards.
				if (commCards.size() != 0) {
					String s = "";					
					for (int i = 0; i < commCards.size(); i++) {
						if (i == commCards.size() - 1) {
							s += commCards.get(i).toString();
						} else 
							s += commCards.get(i).toString() + ", ";
					}
					System.out.println("The community cards are " + s);
				}
			}
			
			stakes = current.getBid(stakes, getPot());
		} while (isBetting());
	}
	
	
	/**
	 * 
	 * @return true if at least one person is still betting and has not bid the same amount as the stakes
	 */
	private boolean isBetting() {
		
		//Goes through each player, skipping ones who have folded
		//and returns false if their bid does not match the stakes
		//to determine if the bidding round is over
		for (Player currentPlayer : players) {
			if (currentPlayer.currentBid != stakes && !currentPlayer.hasFolded) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * deals cards from the deck into the player's hands
	 */
	private void deal(Deck deck) {
		
		//gives each player two cards in their hand
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
	
	
	/**
	 * 
	 * @return the total amount of money in the pot
	 */
	private int getPot() {
		int output = 0;
		for (Player currentPlayer : players) {
			output += currentPlayer.currentBid;
		}
		return output;
	}
	
	/**
	 * This method returns the next player in the "circle" 
	 *and puts the previous player at the end of the list
	 * @return
	 */
	private Player nextPlayer() {
		Player out = players.peekFirst();
		players.offer(players.removeFirst());
		return out;
	}
	
}
