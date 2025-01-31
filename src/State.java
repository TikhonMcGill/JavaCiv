import java.util.ArrayList;

public class State {
	Region capital;
	ArrayList<Region> ownedRegions = new ArrayList<Region>();
	
	String name;
	
	enum Population {
		Sparse,
		Tiny,
		Small,
		Medium,
		Large,
		Huge
	}
	
	enum Economy {
		Desolate,
		Poor,
		Tiny,
		Small,
		Medium,
		Large
	}
	
	enum Army {
		None,
		Ceremonial,
		Tiny,
		Small,
		Medium,
		High
	}
	
	enum WarExhaustion {
		None,
		Negligible,
		Noticeable,
		Significant,
		Critical,
		Catastrophic
	}
	
	enum Stability {
		Anarchy,
		Minimal,
		Unstable,
		Shakey,
		Stable,
		Prosperous
	}
	
	Die die = new Die();
	
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
	
	// Declare war on another State, provided they're not already at war, or the State itself
	public void declareWar(State enemy) {
		if (enemy == this) {
			return;
		}
		
		if (!wars.contains(enemy)) {
			wars.add(enemy);
		}
		
		if (!enemy.wars.contains(this)) {
			enemy.wars.add(this);
		}
		
	}
	
	
}
