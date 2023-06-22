package game.positions;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Dirt extends Ground {
	/**
	 * constructor of Dirt ground, assign display char to its super class, any actor can enter this ground
	 */
	public Dirt() {
		super('.');
	}
}
