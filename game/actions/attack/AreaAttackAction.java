package game.actions.attack;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;


import java.util.*;

/**
 * Class for actor to perform an area attack with a weapon to all surrounding actors.
 */
public class AreaAttackAction extends Action {
    /**
     * The weapon used for the area attack.
     */
    private Weapon weapon;

    /**
     * A map of surrounding actors and their corresponding exit.
     */
    Map<Actor, Exit> actorAndDirection;

    /**
     * The location of the actor performing the area attack.
     */
    Location locationOfActor;

    /**
     * Constructor for AreaAttackAction object with the given actor and weapon.
     *
     * @param actor the actor performing the area attack
     * @param weapon the weapon used for the area attack
     */
    public AreaAttackAction(Actor actor, Weapon weapon) {
        this.actorAndDirection = new HashMap<>();
        this.weapon = weapon;
    }

    /**
     * Executes the AreaAttackAction object, which performs an attack action on all surrounding actors using the given weapon
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String with the result of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.getSurroundingActors(actor, map);

        String result = "";
        for (Actor tempActor : this.actorAndDirection.keySet()){
            Exit tempExit = this.actorAndDirection.get(tempActor);
            result += new AttackAction(tempActor, tempExit.getName(), this.weapon).execute(actor, map) + "\n";
        }
        return menuDescription(actor) + result;
    }

    /**
     * Get all actors that are sorrounding the attacking actor
     *
     * @param actor the actor performing the area attack
     * @param map the game map
     */
    public void getSurroundingActors(Actor actor, GameMap map){
        this.locationOfActor = map.locationOf(actor);
        List<Exit> listOfExit = locationOfActor.getExits();

        for(Exit exit: listOfExit){
            if (exit.getDestination().containsAnActor()) {
                Actor tempActor =  exit.getDestination().getActor();
                actorAndDirection.put(tempActor, exit);
            }
        }
    }

    /**
     * String representing the menu description of the AreaAttackAction object.
     *
     * @param actor the actor performing the area attack
     * @return a string representing the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        String result = actor + " does an area attack with " + this.weapon + "!\n";
        return result;
    }
}