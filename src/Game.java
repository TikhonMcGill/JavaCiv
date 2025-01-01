import java.util.ArrayList;
import java.util.Random;

public class Game {
	static Namings namings = new Namings();
	
	public static void main(String [] args) 
    {
		Language newLang = new Language();
		
		System.out.println(newLang.languageName);
		for (int i = 0; i < 100; i++) {
			String name = newLang.getGivenName() + " " + newLang.getFamilyName();
			System.out.println("\t" + name);
		}
		
//		World newWorld = generateRandomWorld();
//		
//		for (Region region : newWorld.regions) {
//			System.out.println("Adjacencies of " + region.regionName + ": ");
//			for (Region adjacency : region.adjacentRegions) {
//				System.out.println("\t" + adjacency.regionName);
//			}
//		}
		
    }
	
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
