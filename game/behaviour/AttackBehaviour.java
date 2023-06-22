package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Behaviour;
import game.RandomNumberGenerator;
import game.enums.ItemType;

import java.util.List;

/**
 * To make the current Actor attack its surroundings if there's another Actor
 * beside it
 */
public class AttackBehaviour implements Behaviour {

    /**
     * the Actor who's going to attack
     */
    Actor currentActor;

    /**
     * Actor beside the currentActor
     */
    Actor otherActor;

    /**
     * The attack action that's going to be used by currentActor
     */
    Action attackAction;

    /**
     * If there's any hostile actors around the current actor, it will perform an attack. The attack
     * can be a normal single attack (attackAction) or an area attack.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return the action performed by the current actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        this.currentActor = actor;

        Location locationOfActor = map.locationOf(this.currentActor);
        List<Exit> exits = locationOfActor.getExits();

        if(currentActor.getWeaponInventory().size()> 0){
            if(currentActor.getWeaponInventory().get(0).hasCapability(ItemType.Ranged)){
                List<Action> range_attack = currentActor.getWeaponInventory().get(0).getAllowableActions();
                if(range_attack.size()> 0) {
                    int random_target = RandomNumberGenerator.getRandomInt(0, range_attack.size() - 1);
                    attackAction = range_attack.get(random_target);
                    return attackAction;
                }
            }
        }

        for(Exit e : exits){
            if (e.getDestination().containsAnActor()){

                this.otherActor =  e.getDestination().getActor();
                ActionList tempActionList = otherActor.allowableActions(this.currentActor, e.getName(), map);
                if (tempActionList.size() > 0){
                    attackAction = otherActor.allowableActions(this.currentActor, e.getName(), map).get(0);
                    return attackAction;
                }
            }

        }

        return null;
    }

}
