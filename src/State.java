import java.util.ArrayList;

public class State {
	Region capital;
	ArrayList<Region> ownedRegions = new ArrayList<Region>();
	
	String name;
	
	long population = 100;
	long money = 0;
	long soldiers = 0;
	
	double warExhaustion = 0.0;
	double stability = 100.0;
	
	ArrayList<State> wars = new ArrayList<State>();
	
	public State(String stateName) {
		name = stateName;
	}
	
	public void setCapital(Region newCapital) {
		capital = newCapital;
		if (!ownedRegions.contains(newCapital)) {
			ownedRegions.add(newCapital);
		}
	}
	
	// Determine if the State is "defunct", i.e. gone from the game
	public boolean isDefunct() {
		// If a State has no people, it is defunct
		if (population <= 0) {
			return true;
		}
		
		return false;
	}
	
	public void iterate() {
		population *= 1.01;
		
		if (population > 1) {
			population += 1;
		}
		
		// Remove all Defunct States that are at war with this State, since they no longer exist
		for (State war : wars) {
			if (war.isDefunct() == true) {
				wars.remove(war);
			}
		}
		
	}
	
	public void declareWar(State enemy) {
		if (!wars.contains(enemy)) {
			wars.add(enemy);
		}
		
		if (!enemy.wars.contains(this)) {
			enemy.wars.add(this);
		}
		
	}
	
	
}
