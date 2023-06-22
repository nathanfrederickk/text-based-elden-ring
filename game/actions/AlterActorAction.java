package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * This class represents an action where an actor alters into another actor. Currently mostly used for respawning actors.
 */
public class AlterActorAction extends Action {
    /**
     * The actor to alter into.
     */
    private Actor alterInto;

    /**
     * Constructs an AlterActorAction object with the given actor to alter into.
     *
     * @param actor the actor to alter into
     */
    public AlterActorAction(Actor actor){
        this.alterInto = actor;
    }

    /**
     * Executes the AlterActorAction object, which removes the current actor from the game map, increases the
     * max hitpoints of the actor to alter into by 0, and adds the actor to alter into to the game map.
     *
     * @param actor the current actor
     * @param map the game map
     * @return a string representing the menu description
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location tempLocation = map.locationOf(actor);
        map.removeActor(actor);
        this.alterInto.increaseMaxHp(0);
        tempLocation.addActor(this.alterInto);
        return menuDescription(actor);
    }

    /**
     * Returns a string representing the menu description of the AlterActorAction object.
     *
     * @param actor the current actor
     * @return a string representing the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has turned into " + this.alterInto;
    }
}
