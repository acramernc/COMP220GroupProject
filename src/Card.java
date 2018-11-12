/**
 * Holds values such as the value and suit of the card,
 * also contains a reference to the card's image file
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 */
public class Card {
	private int value;
	//private enum suit
	//{Spade, Diamond, Heart, Club;}
	private char suit;
	 
	/**
	 * Creates a Card of value val and suit suit
	 * @param val Value of the card
	 * @param suit Suit of the card
	 */
	public Card(int val, char suit) {

	}

	
	public int getValue() {
		return value;
	}

	
	public char getSuit() {
		return suit;
	}
	
	/**
	 * Returns the file path of the face image
	 * @return filepath of card image
	 */
	public String getImage() {
		return "";
	}
	
}
