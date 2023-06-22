package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.enums.Status;

/**
 * Cliff class. Any actor instantly dies if it walks into a cliff.
 */
public class Cliff extends Ground {
    /**
     * Constructor
     */
    public Cliff() {
        super('+');
    }

    /**
     * Check if there's an actor in the location or not. If yes, then kill the actor
     * because it has fallen into a cliff.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Display display = new Display();
            Actor actor = location.getActor();
            display.println(new DeathAction(actor).execute(actor, location.map()));
        }
    }

    /**
     * Only a player can step off a cliff
     * @param actor the Actor to check
     * @return boolean if actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.PLAYER);
    }
}
