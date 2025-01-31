import java.util.Random;

// Die - used for Randomness

public class Die {
	private Random r = new Random();
	
	public Die() {}
	
	// Roll a Random Number from 1 to 6
	public int roll() {
		return r.nextInt(1, 7);
	}
	
}
