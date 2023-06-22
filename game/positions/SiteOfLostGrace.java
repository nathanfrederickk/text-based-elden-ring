package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.enums.Status;

/**
 * Represents the Site of Lost Grace, with the first Site of Lost Grace called The
 * First Step. When a player enters the ground it will reset all the actor in the map,
 * heal itself and reset the amount of Flask of Crimson tears in the inventory.
 */
public class SiteOfLostGrace extends Ground {

    private String name;

    /**
     * constructor of Dirt ground, assign display char to its super class, Player can rest here
     */
    public SiteOfLostGrace(String name){
        super('U');
        this.name = name;
    }

    @Override
    /**
     * Returns action list that contain rest action.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if(actor.hasCapability(Status.PLAYER)){
            return new ActionList(new RestAction(this));
        }

        return new ActionList();
    }

    /**
     * Site Of Lost Grace's name
     * @return the name of the Site Of Lost Grace
     */
    @Override
    public String toString() {
        return name;
    }
}
