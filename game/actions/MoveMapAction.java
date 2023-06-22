package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.map.utils.MapDestination;

/**
 * Action to move actor across map
 */
public class MoveMapAction extends Action{

    /**
     * The target map
     */
    private final MapDestination mapDestination;

    /**
     * Constructor
     * @param mapDestination destination of which map the Actor will be moved
     */
    public MoveMapAction(MapDestination mapDestination){
        this.mapDestination = mapDestination;
    }

    /**
     * To move the actor to another map
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the display string after actor has travelled into another map
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        GameMap newMap = mapDestination.getDestinationMap();
        int x = mapDestination.getX();
        int y = mapDestination.getY();

        map.moveActor(actor, newMap.at(x, y));

        return actor + " has travelled to " + this.mapDestination.getMapName();
    }

    /**
     * The menu description to tell the user that
     * Player can travel to a different map
     * @param actor The actor performing the action.
     * @return string display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + this.mapDestination.getMapName();
    }
}
