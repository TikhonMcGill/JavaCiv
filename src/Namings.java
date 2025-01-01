import java.util.Random;

public class Namings {
	private String[] vowels = {"a", "e", "i", "o", "u"};
	private String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
	
	private String[] governmentNames = {"Empire", "Republic", "Kingdom", "Duchy", "Federation", "Union", "Confederation", "State", "States"};
	private String[] governmentAdjectives = {"Serene", "United", "People's", "National", "Federative", "Imperial", "Democratic", "Islamic"};
	
	private Random r = new Random();
	
	// Capitalize a String
	String capitalize(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1); 
	}
	
	// Get all Vowels
	String[] getVowels() { return vowels; }
	
	// Get all Consonants
	String[] getConsonants() { return consonants; }
	
	// Generate a Word
	public String generateWord() {
		boolean isVowel = r.nextBoolean();
		int wordLength = r.nextInt(3, 8);
		
		String resultWord = "";
		
		for (int i = 0; i < wordLength; i++) {
			if (isVowel) {
				resultWord = resultWord + vowels[r.nextInt(vowels.length)];
			} else {
				resultWord = resultWord + vowels[r.nextInt(consonants.length)];
			}
			isVowel = !isVowel;
		}
		
		return capitalize(resultWord);
	}
	
	// Generate a Government Name
	String generateGovernmentName(String countryName) {
		boolean doAdjective = r.nextBoolean() && r.nextBoolean();
		
		if (doAdjective) {
			return "The " + governmentAdjectives[r.nextInt(governmentAdjectives.length)] + " " + governmentNames[r.nextInt(governmentNames.length)] + " of " + countryName;
		}
		
		return "The " + governmentNames[r.nextInt(governmentNames.length)] + " of " + countryName;
	}
}
