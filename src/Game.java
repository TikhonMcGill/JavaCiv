import java.util.ArrayList;
import java.util.Random;

public class Game {
	static Namings namings = new Namings();
	static ArrayList<State> allStates = new ArrayList<State>();
	
	public static void main(String [] args) {
		// Generate all States
		int statesToMake = 100; //No. States to Generate
		for (int i = 0; i < statesToMake; i++) {
			State newState = new State(namings.generateGovernmentName());
			allStates.add(newState);
		}
		
		int turn = 1;
		
		// While there is more than one State, iterate through them
		while (allStates.size() > 1) {
			System.out.println("Turn " + Integer.toString(turn));
			
			ArrayList<State> statesToDelete = new ArrayList<State>();
			
			for (State state: allStates) {
				// If the State is Defunct, delete it from all States
				if (state.isDefunct()) {
					statesToDelete.add(state);
					System.out.println("\t " + state.name + " got destroyed!");
					continue;
				}
				
				System.out.println("\t" + state.name + ": ");
				System.out.println("\t\tPopulation: " + Long.toString(state.population));
				state.iterate();
			}
			
			// Delete all Defunct States
			for (State state: statesToDelete) {
				allStates.remove(state);
			}
			
			turn++;
		}
		
		if (allStates.size() == 1) {
			System.out.println(allStates.get(0) + " is the last State standing!");
		} else {
			System.out.println("No State survived!");
		}
		
		
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
		for (Region region: allRegions) {
			newWorld.addAdjacency(region, allRegions.get(r.nextInt(regionsToMake)));
		}
		
		// Prepare the Final World
		newWorld.setRegions(allRegions);
		return newWorld;
		
	}
	
}
