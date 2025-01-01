import java.util.ArrayList;
import java.util.Random;

public class Game {
	static Namings namings = new Namings();
	
	public static void main(String [] args) {}
	
	static // Generate a Random World
	World generateRandomWorld() {
		Random r = new Random();
		
		int regionsToMake = r.nextInt(10, 101);
		
		ArrayList<Region> allRegions = new ArrayList<Region>();
		World newWorld = new World();
		
		// Generate Randomly-named Regions
		for (int i = 0; i < regionsToMake; i++) {
			Region newRegion = new Region(namings.generateWord());
			allRegions.add(newRegion);
		}
		
		// Produce Random Adjacencies
		for (Region region : allRegions) {
			newWorld.addAdjacency(region, allRegions.get(r.nextInt(regionsToMake)));
		}
		
		// Prepare the Final World
		newWorld.setRegions(allRegions);
		return newWorld;
		
	}
	
}
