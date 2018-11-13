
/**
 * Holds values such as the value and suit of the card,
 * also contains a reference to the card's image file
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 */
public class Card {
	private int value;
	private String suit;
	private char suitChar;
	 
	/**
	 * Creates a Card of value val and suit suit
	 * @param val Value of the card
	 * @param suit Suit of the card
	 */
	public Card(int val, String suit) {
		this.value = val;
		this.suit = suit;
		this.suitChar = suit.charAt(0);
	}

	
	public int getValue() {
		return this.value;
	}

	
	public char getSuit() {
		return this.suitChar;
	}
	
	/**
	 * Returns the file path of the face image
	 * @return filepath of card image
	 */
	public String getImage() {
		return "../CardImages/" + this.value + this.suitChar + ".png";
	}
	
	public String toString() {
		String name = "";
		if (this.value == 1) {
			name = "Ace";
		}
		else if (this.value == 11){
			name = "Jack";
		}
		else if (this.value == 12){
			name = "Queen";
		}
		else if (this.value == 13){
			name = "King";
		}
		else {
			name = String.valueOf(this.value);
		}
		return name + " of " + this.suit;
	}
	
}
