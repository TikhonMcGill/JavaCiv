import java.util.ArrayList;
import java.util.HashMap;

public class State {
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
	
	// Tier of State's Size
	enum Size {
		Microstate,
		Tiny,
		Small,
		Medium,
		Large,
		Massive
	}
	
	// Tier of the State's Power
	enum Power {
		Super,
		Great,
		Secondary,
		Minor,
		Insignificant,
		Unrecognized
	}
	
	// Tier of a State's Influence over another Power
	enum Influence {
		Neglibible,
		Tiny,
		Small,
		Medium,
		Large,
		Master,
	}
	
	// Tier of a State's Technology
	enum Technology {
		Backwards,
		Outdated,
		Weak,
		Medium,
		Strong,
		HighTech,
	}
	
	// Tiers that can be Improved through Effort
	enum Improvable {
		Population,
		Economy,
		Army,
		WarExhaustion,
		Crime,
		Size,
		Technology
	}
	
	Die die = new Die(); // The Die with which the State does Random Rolls
	
	ArrayList<State> wars = new ArrayList<State>(); // The States this State is at war with, and can attack
	
	WarExhaustion warExhaustion = WarExhaustion.None; // The State's War Exhaustion Tier
	
	Population population = Population.Sparse; // The State's Population
	
	Improvable improvement = Improvable.Population; // The Current Tier that the State is improving (Population by default)
	
	HashMap<State, Influence> influences = new HashMap<State, Influence>(); // The State's Influence level over other States
	
	// Raise War Exhaustion Tier, if it's not at the Maximum (Catastrophic)
	public void raiseWarExhaustion() {
		if (warExhaustion.ordinal() == 5) {
			return;
		}
		
		warExhaustion = WarExhaustion.values()[warExhaustion.ordinal() + 1];
	}
	
	// Lower War Exhaustion Tier, if it's not at the Minimum (None)
	public void lowerWarExhaustion() {
		if (warExhaustion.ordinal() == 0) {
			return;
		}
		
		warExhaustion = WarExhaustion.values()[warExhaustion.ordinal() - 1];
		
	}
	
	// Raise Population Tier, if it's not at the Maximum (Huge)
	public void raisePopulation() {
		if (population.ordinal() == 5) {
			return;
		}
		
		population = Population.values()[population.ordinal() + 1];
	}
	
	// Lower Population Tier - if the Population is at the minimum (Sparse),
	// and is lowered, that's Game Over for the State
	public void lowerPopulation() {
		if (population.ordinal() == 0) {
			defunct = true;
			return;
		}
		
		population = Population.values()[population.ordinal() - 1];
		
	}
	
	// Constructor for the State
	public State(String stateName) {
		name = stateName;
	}
	
	// Determine if the State is "defunct", i.e. gone from the game
	public boolean isDefunct() {
		return defunct;
	}
	
	// Iterate the Improved Tier
	void iterateImprovement() {
		int result = die.roll();
		
		// If the Dice didn't roll 1 or 6, no change, so return
		if (result != 1 && result != 6) {
			return;
		}
		
		if (result == 1) {
			switch (improvement) {
			case Population:
				lowerPopulation();
				break;
			default:
				break;
			}
		} else {
			switch (improvement) {
			case Population:
				raisePopulation();
				break;
			default:
				break;
			
			}
		}
		
		
	}
	
	// Handle War Exhaustion Changes
	void iterateWarExhaustion() {
		// If there are no Wars, roll the die to possibly lower the War Exhaustion Tier
		if (wars.size() == 0) {
			int warExhaustionTier = warExhaustion.ordinal();
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
		if (isDefunct()) {
			return;
		}
		
		iterateWarExhaustion();
		iterateImprovement();
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
