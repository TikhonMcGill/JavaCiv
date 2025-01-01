import java.util.ArrayList;

public class Region {
	String regionName;
	ArrayList<Region> adjacentRegions;
	
	public Region(String name) {
		regionName = name;
		adjacentRegions = new ArrayList<Region>();
	}
	
	public Region(String name, ArrayList<Region> adjacencies) {
		regionName = name;
		adjacentRegions = adjacencies;
	}
	
}
