package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MoveMapAction;
import game.enums.Status;
import game.map.utils.MapDestination;

/**
 * A door that can allow Players to travel between maps
 */
public class GoldenFogDoor extends Ground {

    /**
     * The map destination where the door will lead to
     */
    private final MapDestination mapDestination;

    /**
     * Constructor.
     */
    public GoldenFogDoor(MapDestination mapDestination) {
        super('D');
        this.mapDestination = mapDestination;
    }

    /**
     * The player can enter the door to move map
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the action player can do, which is to move map
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if(actor.hasCapability(Status.PLAYER)){
            actions.add(new MoveMapAction(this.mapDestination));
            return actions;
        }

        return actions;
    }
}
