import java.util.ArrayList;
import java.util.Random;

public class Game {
	static Namings namings = new Namings();
	static ArrayList<State> allStates = new ArrayList<State>();
	
	public static void main(String [] args) {
		// TODO Random Events
		// TODO Create a "RandomPlayer" Class, extending Player
		// TODO Let Players Choose what to roll dice on each Turn to increase
		// TODO Implement Chosen Increase (default = Population) to grow at beginning of each turn (Roll dice, 1 = shrink, 6 = grow)
		// TODO Implement ability to increase/decrease on tier at the cost of another (e.g. Conscription = Lower Population, Raise Army Size)
		// TODO Consider Sphere of Influence System, also tier-based, State-to-State (only Great Powers can have Influence, maybe?)
		// TODO Create Helper Methods for Raising, Lowering all State Tiers (provided not at extremes)
		// TODO Consider spending Influence to take positive or give negative Tiers (e.g. lower influence, take land from influenced State)
		// TODO In Wars, peace deals could be taking positive Tiers (e.g. taking population, land, etc.), or giving negative Tiers (e.g. dumping Criminals, etc.)
		// TODO Optional Randomized Starts, for fun
		// TODO Idea - if have excess Tiers, can transfer to another (e.g. if excessive land, can build penal colonies, lowering crime)
		// TODO Crisis System, Tier-based (e.g. Calm, Flashpoint, etc.)
		// TODO Add all Tiers to the State, instead of just Enum Definitions
		// TODO Idea - spending a different tier to influence a State
		// TODO Idea - Diplomatic Reputation as another tier
		
		/*
		 * Main Tenets:
		 * 1. Dice System - If there's any Randomness, it's a Dice Roll, from 1 to 6
		 * 2. Tiers, not Numbers - Instead of specific Numbers, low-medium-high, bad-medium-good, etc. etc. etc.
		 */
		
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
				state.iterate();
			}
			
			// Delete all Defunct States
			for (State state: statesToDelete) {
				allStates.remove(state);
			}
			
			turn++;
		}
		
		if (allStates.size() == 1) {
			System.out.println(allStates.get(0).name + " is the last State standing!");
		} else {
			System.out.println("No State survived!");
		}
		
		
	}
}
