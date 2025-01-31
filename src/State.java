import java.util.ArrayList;

public class State {
	Region capital; // The Capital Region of the State - losing it immediately drops Stability down by 1 Tier (if possible)
	ArrayList<Region> ownedRegions = new ArrayList<Region>(); // The Regions owned by the State
	
	String name; // The name of the State
	
	boolean defunct = false; // If true, the State is "defunct" and out of the game
	
	// Tier representing the State's Population
	enum Population {
		Sparse,
		Tiny,
		Small,
		Medium,
		Large,
		Huge
	}
	
	// Tier representing the size of the State's Economy
	enum Economy {
		Desolate,
		Poor,
		Tiny,
		Small,
		Medium,
		Large
	}
	
	// Tier of how large/powerful the State's Army is
	enum Army {
		None,
		Ceremonial,
		Tiny,
		Small,
		Medium,
		High
	}
	
	// Tier of how exhausted the State is from War
	enum WarExhaustion {
		None,
		Negligible,
		Noticeable,
		Significant,
		Critical,
		Catastrophic
	}
	
	// Tier of how Stable the State is
	enum Stability {
		Anarchy,
		Minimal,
		Unstable,
		Shakey,
		Stable,
		Prosperous
	}
	
	// Tier of how much Crime there is in the State
	enum Crime {
		Negligible,
		Minimal,
		Small,
		Considerable,
		High,
		MafiaState
	}
	
	// Tier of how "Corrupt" the State is
	enum Corruption {
		Negligible,
		Minimal,
		Low,
		Noticeable,
		Significant,
		Crippling
	}
	
	Die die = new Die(); // The Die with which the State does Random Rolls
	
	ArrayList<State> wars = new ArrayList<State>(); // The States this State is at war with, and can attack
	
	WarExhaustion exhaustion = WarExhaustion.None; // The State's War Exhaustion Tier
	
	// Raise War Exhaustion Tier, if it's not at the Maximum (Catastrophic)
	public void raiseWarExhaustion() {
		if (exhaustion.ordinal() == 5) {
			return;
		}
		
		exhaustion = WarExhaustion.values()[exhaustion.ordinal() + 1];
	}
	
	// Lower War Exhaustion Tier, if it's not at the Minimum (None)
	public void lowerWarExhaustion() {
		if (exhaustion.ordinal() == 0) {
			return;
		}
		
		exhaustion = WarExhaustion.values()[exhaustion.ordinal() - 1];
		
	}
	
	// Constructor for the State, with only name
	public State(String stateName) {
		name = stateName;
	}
	
	// Constructor for the State, with name and Capital Region
	public State(String stateName, Region stateCapital) {
		name = stateName;
		capital = stateCapital;
	}
	
	// Set the State's Capital Region
	public void setCapital(Region newCapital) {
		capital = newCapital;
		if (!ownedRegions.contains(newCapital)) {
			ownedRegions.add(newCapital);
		}
	}
	
	// Determine if the State is "defunct", i.e. gone from the game
	public boolean isDefunct() {
		return defunct;
	}
	
	// Handle War Exhaustion Changes
	void iterateWarExhaustion() {
		// If there are no Wars, roll the die to possibly lower the War Exhaustion Tier
		if (wars.size() == 0) {
			int warExhaustionTier = exhaustion.ordinal();
			int roll = die.roll();
			
			// If the Number rolled is less than the War Exhaustion Tier, or the Number Rolled is 1, lower War Exhaustion Tier
			// For example, if War Exhaustion is Catastrophic, that's equivalent to 6, so 5/6 chance that will get lowered
			if (roll < (warExhaustionTier + 1) || roll == 1) {
				lowerWarExhaustion();
			}
			
		}
	}
	
	// Handle Passive, per-Turn updates
	public void iterate() {
		iterateWarExhaustion();
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
