package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.enums.Status;

/**
 * Represents the Summoning Sign ground in the map
 */
public class SummoningGround extends Ground {
    /**
     * Constructor for the summoning sign with display char '='
     */
    public SummoningGround(){
        super('=');
    }

    /**
     * Allows the player to summon Ally or Invader when nearby a summoning sign
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList containing the Actions that can be done if a player is nearby
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        if(actor.hasCapability(Status.PLAYER)){
            return new ActionList(new SummonAction(location));
        }
        return new ActionList();
    }
}
