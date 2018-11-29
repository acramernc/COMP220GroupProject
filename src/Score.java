import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

public class Score {
	private LinkedList<Player> playerList;
	//private ArrayList<Card> commCards;
	
	public Score(LinkedList<Player> players) {
		playerList = players; //Intentionally shallow copy
	}
	/**
	 * Runs through the list of players and returns the player who's hand won
	 * @return player that won
	 */
	public Player getWinner() {
		TreeMap<Integer, HashSet<Player>> playerRank = new TreeMap<Integer, HashSet<Player>>();
		int tempRank;
		for(Player p: playerList) {
			tempRank = rankHand(p);
			if(playerRank.containsKey(tempRank)) {
				HashSet<Player> temp = new HashSet<Player>(playerRank.get(tempRank));
				temp.add(p);
				playerRank.put(tempRank, temp);
			}
			else {
				HashSet<Player> temp = new HashSet<Player>();
				temp.add(p);
				playerRank.put(tempRank, temp);
			}
			
		}
		Iterator<Player> iter = playerRank.get(playerRank.firstKey()).iterator();
		if(playerRank.get(playerRank.firstKey()).size()==1) {
			return iter.next();
		}
		else {
			Player winner = iter.next();
			Player tempPlayer;
			
			while (iter.hasNext()) {
				tempPlayer = iter.next();
				if(handHigh(winner)<handHigh(tempPlayer)) {
					winner = tempPlayer;
				}
			}
			return winner;
		}
	}
	/**
	 * ranks hand based on rankings specified in handRanks.txt (royal flush = 1 ect)
	 * @return integer ranking of hand
	 */
	public int rankHand(Player p) {
		
	}
	
	/**
	 * 
	 * @param p
	 * @return value of the highest card within the hand (not including cards not being played)
	 */
	public int handHigh(Player p) {
		
	}
	//TODO: change method returns to the value of the highest card in the hand or 0 if it would return false
	/**
	 * 
	 * @return True if is a Royal Flush
	 */
	public boolean isRFlush(ArrayList<Card> hand, ArrayList<Card> commCards) {
		//Checks that the hole cards are the same suit
		if(hand.get(0).getSuit() != hand.get(1).getSuit())
			return false;
		//Ensures the cards in the hand are A,K,Q,J, or 10
		if(!(hand.get(0).getValue() >= 10 || hand.get(0).getValue() == 1))
			return false;
		if(!(hand.get(1).getValue() >= 10 || hand.get(1).getValue() == 1))
			return false;
		
		//All the requirements for a royal flush
		char suit = hand.get(0).getSuit();
		boolean hasAce = false;
		boolean hasKing = false;
		boolean hasQueen = false;
		boolean hasJack = false;
		boolean has10 = false;
		
		//Checks the cards in the hand for royal flush components
		for(Card c: hand) {
			if(c.getValue() == 1)//Checking for an ace
				hasAce = true;
			else if(c.getValue() == 10)//Checking for a 10
				has10 = true;
			else if(c.getValue() == 11)//Checking for a Jack
				hasJack = true;
			else if(c.getValue() == 12)//Checking for a Queen
				hasQueen = true;
			else if(c.getValue() == 13)//Checking for a King
				hasKing = true;
		}
		
		//Checks the cards in the Community Cards for royal flush components
		for(Card c: commCards) {
			if(c.getValue() == 1 && c.getSuit() == suit)//Checking for an ace
				hasAce = true;
			else if(c.getValue() == 10 && c.getSuit() == suit)//Checking for a 10
				has10 = true;
			else if(c.getValue() == 11 && c.getSuit() == suit)//Checking for a Jack
				hasJack = true;
			else if(c.getValue() == 12 && c.getSuit() == suit)//Checking for a Queen
				hasQueen = true;
			else if(c.getValue() == 13 && c.getSuit() == suit)//Checking for a King
				hasKing = true;
		}
		
		//Checks that all conditions are met
		if(hasAce && has10 && hasJack && hasQueen && hasKing)
			return true;
		else
			return false;	
	}
	
	/**
	 *   
	 * @return True if is a Straight Flush
	 */
	public boolean isSFlush(ArrayList<Card> hand, ArrayList<Card> commCards) {
		boolean isValid = false;
		//int max= 14;
		//Checks that the hole cards are the same suit
		if(hand.get(0).getSuit() != hand.get(1).getSuit())
			return false;
		
		char suit = hand.get(0).getSuit();//suit for the straight flush
		
		int highCard = 0; //stores the value of the high card
		//sets the value of the high card
		for(Card c: hand) {
			if(c.getValue() > highCard)//For normal straight will have to check for ace
				highCard = c.getValue();
		}
		for(Card c: commCards) {
			if(c.getValue() > highCard && c.getSuit() == suit)//For normal straight will have to check for ace
				highCard = c.getValue();
		}
		
		while(!isValid && highCard > 5) {
			isValid = isNumHigh(hand, commCards, suit, highCard);
		}
		if(isValid)
			return true;
		else 
			return false;
		
		/*TODO remove this
		if(highCard < 6)//straight must have a high card of at least 6 eg. 6,5,4,3,2
			return false;
		
		//Requirements for Straight flush
		boolean has1Low = false; //has card that is 1 lower than highCard
		boolean has2Low = false;
		boolean has3Low = false;
		boolean has4Low = false;
		
		//Check for 1low
		while(!has1Low) {
			for(Card c: hand) {
				if(c.getValue() == highCard-1)
					has1Low = true;
			}
			for(Card c: commCards) {
				if(c.getValue() == highCard-1 && c.getSuit() == suit)
					has1Low = true;
			}
			if(!has1Low) {
				max = highCard;
				highCard = findHighest(hand, commCards, suit, max);
			}
		}*/
		
		
		
	}
	
	/**
	 * Takes in @param highCard and checks if it is the high card in a straight
	 * @param hand 
	 * @param commCards
	 * @param suit suit of the hand
	 * @param highCard
	 * @return true if it is a valid highcard
	 */
	public boolean isNumHigh(ArrayList<Card> hand, ArrayList<Card> commCards, char suit, int highCard) {
		if(highCard < 6)
			return false;
		
		boolean has1Low = false; //has card that is 1 lower than highCard
		boolean has2Low = false;
		boolean has3Low = false;
		boolean has4Low = false;
		
		
		
		//Check for 1 low
		for(Card c: hand) {
			if(c.getValue() == highCard-1)
				has1Low = true;
		}
		for(Card c: commCards) {
			if(c.getValue() == highCard-1 && c.getSuit() == suit)
				has1Low = true;
		}
		if(!has1Low)
			return false;
		
		//Check for 2 low
		for(Card c: hand) {
			if(c.getValue() == highCard-2)
				has2Low = true;
		}
		for(Card c: commCards) {
			if(c.getValue() == highCard-2 && c.getSuit() == suit)
				has2Low = true;
		}
		if(!has2Low)
			return false;
		
		//Checks for 3 low
		for(Card c: hand) {
			if(c.getValue() == highCard-3)
				has3Low = true;
		}
		for(Card c: commCards) {
			if(c.getValue() == highCard-3 && c.getSuit() == suit)
				has3Low = true;
		}
		if(!has3Low)
			return false;
		
		//Checks for 4 low
		for(Card c: hand) {
			if(c.getValue() == highCard-4)
				has3Low = true;
		}
		for(Card c: commCards) {
			if(c.getValue() == highCard-4 && c.getSuit() == suit)
				has3Low = true;
		}
		if(!has4Low)
			return false;
		else
			return true;
		
		
		
	}
	
	/**
	 * Finds the highest card of specified suit in either hand or commcards
	 * @param hand
	 * @param commCards
	 * @param suit
	 * @return
	 */
	public int findHighest(ArrayList<Card> hand, ArrayList<Card> commCards, char suit) {//TODO figure out if this is used
		int highCard = 0;
		for(Card c: hand) {
			if(c.getValue() > highCard)//For normal straight will have to check for ace
				highCard = c.getValue();
		}
		for(Card c: commCards) {
			if(c.getValue() > highCard && c.getSuit() == suit)//For normal straight will have to check for ace
				highCard = c.getValue();
		}
		return highCard;
	}
	
	/**
	 * 
	 * @return True if is Four of a Kind
	 */
	public boolean isFour(ArrayList<Card> hand, ArrayList<Card> commCards) {
		//TODO finish
		//for each card find if there are three others of the same 
		//make sure that at least one of the four is from the hand
		boolean hasFour = false;//will be true if there are 4 cards of the same value in all
		int value =0;//holds the value of the four of a kind
		//Create an arraylist of all the possible cards for easier checking
		ArrayList<Card> all = new ArrayList<Card>();
		for(Card c: hand) {
			all.add(c);
		}
		for(Card c: commCards) {
			all.add(c);
		}
		
		for(Card c: all) {
			for(Card d: all) {
				if(c.getValue() == d.getValue() && c.getSuit() != d.getSuit()) {
					for(Card e: all) {
						if(e.getValue() == d.getValue() && e.getSuit() != d.getSuit() && c.getSuit() != e.getSuit()) {
							for(Card f: all) {
								if(f.getValue() == d.getValue() && f.getSuit() != d.getSuit() && c.getSuit() != f.getSuit() && e.getSuit()!=f.getSuit()) {
									hasFour = true;
									value = f.getValue();
								}}}}}}}
		if(!hasFour)
			return false;
		
		for(Card c: hand) {
			if (c.getValue()== value)
				return true;//TODO check that this follows rules
		}
		return false;
		
	}
	
	public boolean isSameSuit(Card one, Card two) {
		return one.getSuit() == two.getSuit();
	}
	
	public boolean isSameRank(Card one, Card two) {
		return one.getValue() == two.getValue();
	}
	
	
}
