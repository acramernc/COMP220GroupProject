
public class ComputerOpponent extends Player {
	
	private static String[] names = {"Bob", "John", "Philip", "Adam", "Alan", "Fred",
									"Sam", "Alex", "Chuck", "Brad", "Computron", "Liam", "Alfred"};
	
	
	public ComputerOpponent() {
		
		super(names[(int)Math.floor(Math.random() * names.length)]);
		isComp = true;
	}
	
	
	
	@Override
	public int getBid(int stakes, int pot) {
		if (currentBid == stakes) {
			check();
			return stakes;
		}
		else {
			call(stakes);
			return stakes;
		}
	}
}
