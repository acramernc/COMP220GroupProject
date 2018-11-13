import java.util.Scanner;

/**
 * 
 * @author Adam Cramer, Wesley Curtis, Jake Murphy
 *
 */
public class Poker{
	public static void main(String args[]) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("How many human players do you have?");
		int numHuman = scan.nextInt();
		//TODO: add exceptions
		System.out.println("And how many computer opponents would you like?");
		int numComp = scan.nextInt();
		Game g = new Game(numHuman,numComp);
		g.play();
	}
}
