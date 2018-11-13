import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 *
 */
public class Player {
	public ArrayList<Card> hand;
	//money is the total amount of money the player has
	public int money;
	//currentBid is the amount of money that the player has put in to the hand
	public int currentBid;
	public String name;
	public boolean isDealer;
	//hasFolded is true when the player folds their cards
	public boolean hasFolded;
	private Scanner scan;
	
	/**
	 * Creates a blank player with no cards or money
	 */
	public Player(String name) {
		money = 500;
		this.name = name;
		hand = new ArrayList<>();
		hasFolded = false;
		isDealer = false;
		scan = new Scanner(System.in);
	}
	
	
	public int getBid(int stakes, int pot) {
		System.out.println(name + ",s turn\nYou have " + this.toString());
		System.out.println("The stakes are at " + stakes + " and the pot is " + pot + 
				"Your current bid is " + currentBid);
		System.out.println("Would you like to fold(f), check(c), raise(r) or call(l)?");
		char answer = scan.next().toLowerCase().charAt(0);
		if (answer == 'f') {
			fold();
			return stakes;
		}
		else if (answer == 'c') {
			check();
			return stakes;
		}
		else if (answer == 'l') {
			call();
			return stakes;
		}
		else if (answer == 'r') {
			System.out.println("What would you like to raise it to?");
			int amount = scan.nextInt();
			raise(amount-stakes);
			return amount;
		}
		else {
			System.out.println("You did not pick one of the options so you will fold.");
			fold();
			return stakes;
		}
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
		hasFolded = true;
	}
	
	/**
	 * pass
	 */
	public void check() {
		System.out.println(name + " has checked.");
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
	
	public String toString() {
		String output = "";
		for (int i = 0; i < hand.size(); i++) {
			if (i != hand.size() - 1)
				output += hand.get(i).toString() + ",";
			else
				output += hand.get(i).toString();
		}
		return output;
	}
}
