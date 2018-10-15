/**Daniel Haluszka
 * 09/25/18
 * Computer Cryptography
 * FriedmanTest.java
 * Main driver file of Friedman test key length guesser program.
 */

package friedman_test;
import java.util.Scanner;

public class FriedmanTest {
	
	public static void main(String args[]) {
		
		String input = "";
		Analyzer analyzer = new Analyzer();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		//main loop
		while (running == true) {
			
			analyzer.clear();
			System.out.println("--------------------------------------------------\n"
					+ "Please enter a string of alphabetic characters:\n(Enter 0 to exit.)");
			input = scanner.nextLine();
			
			//look for exit input, else run program on input
			if (input.equals("0")) {
				
				System.out.println("Exiting...");
				scanner.close();
				running = false;
				break;
				
			} else {
				
				input = analyzer.checkInput(input);
				
				if (input != null) {
					
					analyzer.parseInput(input);
					System.out.println("\nIndex of coincidence: "
					+ String.format("%.3g%n", analyzer.getIndexOfCoincidence(input.length())));
					System.out.println("Guess for key length: "
					+ analyzer.guessKeyLength(input.length()) + "\n");
					
				}
				
			}
			
		}
		
	}

}
