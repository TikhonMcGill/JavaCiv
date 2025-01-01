import java.util.ArrayList;

public class World {
	ArrayList<Region> regions;
	
	public World() {};
	
	public void setRegions(ArrayList<Region> allRegions) {
		regions = allRegions;
	}
	
	public World(ArrayList<Region> allRegions) {
		regions = allRegions;
	}
	
	public void addAdjacency(Region regionA, Region regionB, boolean mutual) {
		if (regionA == regionB) {
			return;
		}
		
		if (!regionA.adjacentRegions.contains(regionB)) {
			regionA.adjacentRegions.add(regionB);
		}
		
		if (mutual && !regionB.adjacentRegions.contains(regionA)) {
			regionB.adjacentRegions.add(regionA);
		}
	}
	
	public void addAdjacency(Region regionA, Region regionB) {
		addAdjacency(regionA, regionB, true);
	}
	
}
