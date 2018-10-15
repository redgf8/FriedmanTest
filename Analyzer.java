/**Analyzer.java
 * Object for storage and analysis of Vigenere input using the Friedman test
 * to guess the likely length of encryption key.
 */

package friedman_test;

import java.util.HashMap;
import java.util.Map;

public class Analyzer {

	private Map<String, Double> letterCount = new HashMap<String, Double>();
	private double index = 0.0;
	private int keyLength = 0;
	static final private String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	//check that the given input string is valid input
	public String checkInput(String input) {
		
		//remove spaces and convert to lower case
		input = input.replaceAll("\\s+","");
		input = input.toLowerCase();
		
		//check for invalid input, return null if invalid
		if (input.isEmpty() || input.equals(" ")) {
			
			System.out.println("Error: Invalid input\n");
			return null;
			
		}
		
		for (int i = 0; i < input.length(); i++) {
			
			if (!alphabet.contains(input.substring(i, i+1))) {
				
				System.out.println("Error: Invalid input\n");
				return null;
				
			}
			
		}
		
		return input;
		
	}
	
	//count and store occurrences of letters in cipher text
	public void parseInput(String input) {
		
		//count occurrences, sort, and store in map
		for (int i = 0; i < input.length(); i++) {
			
			//loop through each char of the input string and update count of letter
			if (this.letterCount.containsKey(input.substring(i,i+1))) {
				
				this.letterCount.put(input.substring(i,i+1), (this.letterCount.get(input.substring(i,i+1)) + 1.0));
				
			} else {
				
				this.letterCount.put(input.substring(i,i+1), 1.0);
				
			}
			
		}
		
	}
	
	//calculate index of coincidence
	public double getIndexOfCoincidence(int length) {
		
		this.index = (1 / ((double) length * ((double) length - 1)));
		double currentCount = 0.0;
		double sum = 0.0;
		
		for (int i = 0; i < 26; i++) {
			
			if (letterCount.containsKey(alphabet.substring(i,i+1))) {
				
				currentCount = letterCount.get(alphabet.substring(i,i+1));
				sum += (currentCount * (currentCount - 1));
				
			}
				
		}
		
		this.index *= sum;
		return this.index;
		
	}
	
	//calculate probable length
	public int guessKeyLength(int length) {
		
		this.keyLength = (int) Math.round((0.0265 * length)/((0.065 - this.index) + (length * (this.index - 0.0385))));
		
		//minimum value of key length is one, correct any lower guesses
		if (this.keyLength <= 0) {
			
			this.keyLength = 1;
			
		}
		
		return this.keyLength;
		
	}
	
	//clear all values
	public void clear() {
		
		this.index = 0.0;
		this.keyLength = 0;
		this.letterCount.clear();
		
	}
	
}
