import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

/*
 * Known Issues:
 * anything lower than 4 of a kind is not checked
 * royal flush doesnt follow the rules correctly
 * if there is a tie the method only returns null instead of showing which players tied
 * no support for more than 2 people with the same hand
 * tie only checks highest tiebreaker card and will return a tie without checking anymore cards
 * player needs .equals method for this to function properly
 */
/**
 * 
 * @author CRAMERAM17
 *
 */
public class Score {
	private LinkedList<Player> playerList;//list of players
	private ArrayList<Card> coCards;//community cards
	
	/**
	 * 
	 * @param players list of all the players
	 * @param cCards list of community cards
	 */
	public Score(LinkedList<Player> players, ArrayList<Card> cCards) {
		playerList = players; //Intentionally shallow copy
		coCards = cCards; //also Intentionally shallow
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
		HashSet<Player> tempHash = playerRank.get(playerRank.firstKey());
		Iterator<Player> iter = tempHash.iterator();
		if(tempHash.size()==1) {
			return iter.next();
		}
		else {
			Player winner = iter.next();
			Player tempPlayer;
			boolean isTie = false;
			
			while (iter.hasNext()) {
				tempPlayer = iter.next();
				if(handHigh(winner) == handHigh(tempPlayer)) {
					isTie = true;
				}
				if(handHigh(winner)<handHigh(tempPlayer)) {
					winner = tempPlayer;
				}
			}
			if(isTie) {
				iter = tempHash.iterator();//resets iterator to find tie
				tempPlayer = iter.next();
				while(iter.hasNext()) {
					if(handHigh(winner) == handHigh(tempPlayer) && !winner.equals(tempPlayer)) {
						return null;//this is bad but wesley wants it 
					}
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
		if (isRFlush(p.hand, coCards)) {
			return 1;
		}
		if(isSFlush(p.hand, coCards) != 0) {
			return 2;
		}
		if(isFour(p.hand, coCards) != 0) {
			return 3;
		}
		if(isFHouse(p.hand, coCards) != 0) {
			return 4;
		}
		if(isFlush(p.hand, coCards) != 0) {
			return 5;
		}
		if(isStraight(p.hand, coCards) != 0) {
			return 6;
		}
		if(isThree(p.hand, coCards) != 0) {
			return 7;
		}
		if(is2Pair(p.hand, coCards) != 0) {
			return 8;
		}
		if(isPair(p.hand, coCards) != 0) {
			return 9;
		}
		return 10;
	}
	
	/**
	 * 
	 * @param p
	 * @return most valuable card within the hand (not including cards not being played)
	 */
	public int handHigh(Player p) {
		if (isRFlush(p.hand, coCards)) {
			return 14;//ace
		}
		if(isSFlush(p.hand, coCards) != 0) {
			return isSFlush(p.hand, coCards);
		}
		if(isFour(p.hand, coCards) != 0) {
			return isFour(p.hand, coCards);
		}
		if(isFHouse(p.hand, coCards) != 0) {
			return isFHouse(p.hand, coCards);
		}
		if(isFlush(p.hand, coCards) != 0) {
			return isFlush(p.hand, coCards);
		}
		if(isStraight(p.hand, coCards) != 0) {
			return isStraight(p.hand, coCards);
		}
		if(isThree(p.hand, coCards) != 0) {
			return isThree(p.hand, coCards);
		}
		if(is2Pair(p.hand, coCards) != 0) {
			return is2Pair(p.hand, coCards);
		}
		if(isPair(p.hand, coCards) != 0) {
			return isPair(p.hand, coCards);
		}
		//High card
		int highCard = 0;
		for(Card c: p.hand) {
			if(c.getValue() > highCard)
				highCard = c.getValue();
		}
		for(Card c: coCards) {
			if(c.getValue() > highCard)
				highCard = c.getValue();
		}
		return highCard;
		
		
	}
	
	//currently unnessecary may be nessecary if we eventually iterate through cards properly to resolve ties
	public Player tieBreaker(Player p1, Player p2) {
		if(handHigh(p1) > handHigh(p2)) {
			return p1;
		}
		if(handHigh(p1) < handHigh(p2)) {
			return p2;
		}
		return null;
	}
	
	/**
	 * 
	 * @return True if is a Royal Flush
	 */
	public boolean isRFlush(ArrayList<Card> hand, ArrayList<Card> commCards) {
		//TODO this needs to be changed such that the hole cards dont need to be included
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
	 * @return value of the highest card if is a Straight Flush or 0 if it is not
	 */
	public int isSFlush(ArrayList<Card> hand, ArrayList<Card> commCards) {
		boolean isValid = false;//TODO this needs to be changed such that the hole cards dont need to be included
		//int max= 14;
		//Checks that the hole cards are the same suit
		if(hand.get(0).getSuit() != hand.get(1).getSuit())
			return 0;
		
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
			return highCard;
		else 
			return 0;
	}
	
	/**
	 * Takes in @param highCard and checks if it is the high card in a straight
	 * @param hand 
	 * @param commCards
	 * @param suit suit of the hand
	 * @param highCard
	 * @return true if it is a valid highcard
	 *///used in SFlush
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
	 *///Used in sFlush maybe??
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
	 * @return value of the card if is Four of a Kind or 0 if its not
	 */
	public int isFour(ArrayList<Card> hand, ArrayList<Card> commCards) {
		//TODO finish
		//for each card find if there are three others of the same 
		//make sure that at least one of the four is from the hand
		//Create an arraylist of all the possible cards for easier checking
		ArrayList<Card> all = new ArrayList<Card>();
		for(Card c: hand) {
			all.add(c);
		}
		for(Card c: commCards) {
			all.add(c);
		}
		
		//Brute force iterates through the list finding all value matches
		for(Card c: all) {
			for(Card d: all) {
				if(c.getValue() == d.getValue() && c.getSuit() != d.getSuit()) {
					for(Card e: all) {
						if(e.getValue() == d.getValue() && e.getSuit() != d.getSuit() && c.getSuit() != e.getSuit()) {
							for(Card f: all) {
								if(f.getValue() == d.getValue() && f.getSuit() != d.getSuit() && c.getSuit() != f.getSuit() && e.getSuit()!=f.getSuit()) {
									return f.getValue();
								}}}}}}}
		return 0;
		
		
	}
	
	/**
	 * 
	 * 
	 * @return 0 if not a full house: otherwise return value of the three of a kind
	 */
	public int isFHouse(ArrayList<Card> hand, ArrayList<Card> commCards) {
		return 0;//TODO finish full house
	}
	
	public int isFlush(ArrayList<Card> hand, ArrayList<Card> commCards) {
		return 0;//TODO finish flush
	}
	
	public int isStraight(ArrayList<Card> hand, ArrayList<Card> commCards) {
		return 0;//TODO finish straight
	}
	
	public int isThree(ArrayList<Card> hand, ArrayList<Card> commCards) {
		return 0;//TODO finish three of a kind
	}
	
	public int is2Pair(ArrayList<Card> hand, ArrayList<Card> commCards) {
		return 0;//TODO finish two pair
	}
	
	public int isPair(ArrayList<Card> hand, ArrayList<Card> commCards) {
		return 0;//TODO finish pair
	}
	
	public boolean isSameSuit(Card one, Card two) {
		return one.getSuit() == two.getSuit();
	}
	
	public boolean isSameRank(Card one, Card two) {
		return one.getValue() == two.getValue();
	}
	
	
}
