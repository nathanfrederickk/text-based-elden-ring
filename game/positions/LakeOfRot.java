package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Part of creative Requirement, Lake of Rot which decreases the Actor's health
 * if it steps into it.
 */
public class LakeOfRot extends Ground {

    /**
     * The amount of damage per turn
     */
    private final int MINUS_HIT_POINTS = 10;

    /**
     * Constructor.
     */
    public LakeOfRot() {
        super('l');
    }

    /**
     * Check if there's an Actor in it
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor actor = location.getActor();
            actor.hurt(this.MINUS_HIT_POINTS);
        }
    }
}
