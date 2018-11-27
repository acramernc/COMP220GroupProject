import java.util.ArrayList;

public class ComputerOpponent extends Player {
	
	private static String[] names = {"Bob", "John", "Philip", "Adam", "Alan", "Fred",
									"Sam", "Alex", "Chuck", "Brad", "Computron", "Liam", "Alfred",
									"Chuck", "Logan", "Craig"};

	boolean hasPair;
	
	public ComputerOpponent() {
		
		super(names[(int)Math.floor(Math.random() * names.length)]);
		isComp = true;
		hasPair = false;
	}
	
	
	
	@Override
	public int getBid(int stakes, int pot, ArrayList<Card> commCards) {
//		if (currentBid == stakes) {
//			check();
//			return stakes;
//		}
//		else {
//			call(stakes);
//			return stakes;
//		}
		
		int prob = 50; //Probability
		if (pot > money*2)
			prob+=5;
		if (money < (stakes - currentBid))
			prob -= 30;
		if (commCards.size() == 0) {
			if (hand.get(0).getValue() == hand.get(1).getValue()) {
				prob += 30;
				hasPair = true;
			} else {
				prob -= 7;
			}
			if (hand.get(0).getSuit() == hand.get(1).getSuit()) {
				prob += 6;
			} else {
				prob -= 4;
			}
			for (Card card : hand) {
				if (card.getValue() < 5)
					prob -= 10;
				if (card.getValue() > 10)
					prob += 6;
			}
			int rand = (int)(Math.random() *100);
			System.out.println("probability: " + prob + " rand: " + rand);
			if (rand < prob) {
				if (rand < (prob/2)) {
					int amount = currentBid+(money/4);
					raise(amount);
					return amount;
				}
				call(stakes);
				return stakes;
			} else {
				fold();
				return stakes;
			}
		} else {
			for (Card currentComm : commCards) {
				
			}
			if (stakes == currentBid) {
				check();
				return stakes;
			} else {
				call(stakes);
				return stakes;
			}
		}
	}
}
