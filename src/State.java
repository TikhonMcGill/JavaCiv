import java.util.ArrayList;

public class State {
	Region capital;
	ArrayList<Region> ownedRegions = new ArrayList<Region>();
	
	String name;
	
	int population = 100;
	
	public State(String stateName) {
		name = stateName;
	}
	
	public void setCapital(Region newCapital) {
		capital = newCapital;
		if (!ownedRegions.contains(newCapital)) {
			ownedRegions.add(newCapital);
		}
	}
	
	public void iterate() {
		population *= 1.01;
		
		if (population > 1) {
			population += 1;
		}
	}
	
}
