import java.util.ArrayList;

public class Region {
	String regionName;
	
	ArrayList<Region> adjacentRegions;
	ArrayList<State> presentStates;
	
	public Region(String name) {
		regionName = name;
		adjacentRegions = new ArrayList<Region>();
	}
	
	public Region(String name, ArrayList<Region> adjacencies) {
		regionName = name;
		adjacentRegions = adjacencies;
	}
	
	public void addState(State newState) {
		if (!presentStates.contains(newState)) {
			presentStates.add(newState);
		}
	}
	
	public void removeState(State state) {
		if (presentStates.contains(state)) {
			presentStates.remove(state);
		}
	}
	
}
