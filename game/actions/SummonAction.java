package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.RandomNumberGenerator;
import game.actors.Ally;
import game.actors.Invader;

/**
 * Summon Action used to summon the Ally or Invader on a Summoning Sign
 */
public class SummonAction extends Action {
    /**
     * Chance of spawning an ally or invader
     */
    private final int chance;

    /**
     * Location to spawn the entity
     */
    private final Location location;

    /**
     * Constructor for summoning action, calculates the chance of summoning and stores the spowning location
     * @param location where the summoning should be done
     */
    public SummonAction(Location location){
        this.chance = RandomNumberGenerator.getRandomInt(100);
        this.location = this.spawnLocation(location);
    }

    /**
     * Execute the summoning and spawn an Ally/Invader in the desired location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String what actor was spawned
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(chance <= 50){
            Ally newAlly = new Ally();
            map.at(this.location.x(), this.location.y()).addActor(newAlly);
            return "Summoned Ally";
        }
        else{
            Invader newInvader = new Invader();
            map.at(this.location.x(), this.location.y()).addActor(newInvader);
            return "Summoned Invader";
        }
    }

    /**
     * Checks the possible spawning location
     * @param location the location of spawning
     * @return Location where spawning can be done
     */
    public Location spawnLocation(Location location){
        for (Exit exit : location.getExits()){
            if (!exit.getDestination().containsAnActor()){
                return exit.getDestination();
            }
        }
        return null;
    }

    /**
     * Menu description when a summoning action can be executed
     * @param actor The actor performing the action.
     * @return String describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Summon Ally/ Invader";
    }
}
