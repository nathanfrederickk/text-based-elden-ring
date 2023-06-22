package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class for an actor to de-spawn and be removed from the game map.
 */
public class DeSpawnAction extends Action {

    /**
     * Executes the de-spawn action by removing the actor from the game map.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The menu description of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return this.menuDescription(actor);
    }

    /**
     * Returns the menu description of the de-spawn action.
     *
     * @param actor The actor performing the action.
     * @return The menu description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " has de-spawned!";
    }
}
