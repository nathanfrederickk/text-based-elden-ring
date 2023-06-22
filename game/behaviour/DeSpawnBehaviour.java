package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Behaviour;
import game.RandomNumberGenerator;
import game.actions.DeSpawnAction;

/**
 * Each turn, each enemy has a despawn chance of 10 percent if it is not
 * following a player.
 */
public class DeSpawnBehaviour implements Behaviour {

    /**
     * Constructor of DeSpawnBehaviour class
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the Despawn action if the chance is lower than 10
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        int deSpawnChance = RandomNumberGenerator.getRandomInt(100);

        if (deSpawnChance <= 10){
            return new DeSpawnAction();
        } else {
            return null;
        }

    }
}
