package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	/**
	 * constructor of Floor ground, assign display char to its super class , only player can enter this ground
	 */
	public Floor() {
		super('_');
	}

	/**
	 * No enemy can enter the floor
	 * @param actor the Actor to check
	 * @return boolean value whetever actor can enter this ground
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.HOSTILE_TO_ENEMY) || actor.hasCapability(Status.PLAYER);
	}
}
