import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Language {
	private Random r = new Random();
	private Namings n = new Namings();
	
	// TODO Translating Words between languages, based on commonality of letters or absence thereof
	
	// Words of the Language
	public String languageName;
	public ArrayList<String> givenNames;
	public ArrayList<String> familyNames;
	
	// In a Language, letters are sorted by frequency, e.g. "e, i, a, o, u" means e is most common, u is least common
	private ArrayList<String> vowels;
	private ArrayList<String> consonants;
	
	// Generate an Alphabet for this Language
	private void generateAlphabet() {
		ArrayList<String> newVowels = new ArrayList<String>();
		ArrayList<String> newConsonants = new ArrayList<String>();
		
		// Generate Vowels - shuffle them, possibly delete them
		String[] pVowels = n.getVowels();
		
		for (int i = 0; i < pVowels.length; i++) {
			if (r.nextInt(20) > 0) {
				newVowels.add(pVowels[i]);
			}
		}
		Collections.shuffle(newVowels);
		
		// Generate Consonants - shuffle them, possibly delete them
		String[] pConsonants = n.getConsonants();
		
		for (int i = 0; i < pConsonants.length; i++) {
			if (r.nextInt(20) > 0) {
				newConsonants.add(pConsonants[i]);
			}
		}
		Collections.shuffle(newConsonants);
		
		vowels = newVowels;
		consonants = newConsonants;
	}
	
	// Generate Given and Family Names
	private void generateNames() {
		ArrayList<String> givens = new ArrayList<String>();
		ArrayList<String> families = new ArrayList<String>();
		
		// Generate 100 Given and Family Names, giving a Combination of 10,000 possible names
		for (int i = 0; i < 100; i++) {
			// Generate a Given Name
			String newGiven = generateWord();
			while (givens.contains(newGiven)) {
				newGiven = generateWord();
			}
			givens.add(newGiven);
			
			// Generate a Family Name
			String newFamily = generateWord();
			while (families.contains(newFamily)) {
				newFamily = generateWord();
			}
			families.add(newFamily);
			
		}
		
		// Add the Language Name as a First and Last Name, with a chance
		if (r.nextBoolean() == true) {
			givens.add(languageName);
		}
		
		if (r.nextBoolean() == true) {
			families.add(languageName);
		}
		
		givenNames = givens;
		familyNames = families;
	}
	
	public Language() {
		generateAlphabet();
		languageName = generateWord();
		generateNames();
	}
	
	public Language(String name) {
		languageName = name;
		generateAlphabet();
		generateNames();
	}
	
	public Language(String name, ArrayList<String> vs, ArrayList<String> cs) {
		languageName = name;
		vowels = vs;
		consonants = cs;
		generateNames();
	}
	
	public Language(String name, ArrayList<String> vs, ArrayList<String> cs, ArrayList<String> givens, ArrayList<String> families) {
		languageName = name;
		givenNames = givens;
		familyNames = families;
		
		vowels = vs;
		consonants = cs;
	}
	
	// Get a Vowel from the Letter's Language
	private String getVowel() {
		int index = r.nextInt(vowels.size() - r.nextInt(vowels.size()));
		return vowels.get(index);
	}
	
	// Get a Consonant from the Letter's Language
	private String getConsonant() {
		int index = r.nextInt(consonants.size() - r.nextInt(consonants.size()));
		return consonants.get(index);
	}
	
	// Generate a Word based on the Language's Alphabet
	public String generateWord() {
		boolean isVowel = r.nextBoolean();
		int wordLength = r.nextInt(3, 8);
		
		String resultWord = "";
		
		for (int i = 0; i < wordLength; i++) {
			if (isVowel) {
				resultWord = resultWord + getVowel();
			} else {
				resultWord = resultWord + getConsonant();
			}
			isVowel = !isVowel;
		}
		
		return n.capitalize(resultWord);
	}
	
	// Get a Given Name
	public String getGivenName() {
		return givenNames.get(r.nextInt(givenNames.size()));
	}
	
	// Get a Family Name
	public String getFamilyName() {
		return familyNames.get(r.nextInt(familyNames.size()));
	}
	
}
