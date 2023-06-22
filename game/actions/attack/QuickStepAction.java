package game.actions.attack;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.RandomNumberGenerator;
import game.actions.DeathAction;
import game.enums.Status;

import java.util.List;
import java.util.Random;

/**
 * Quickstep skill implementation
 */
public class QuickStepAction extends Action {
    /**
     * The target Actor to attack.
     */
    private Actor target;

    /**
     * The direction of the attack.
     */
    private String direction;

    /**
     * The WeaponItem used in the attack.
     */
    private WeaponItem weaponItem;


    /**
     * Constructor for Quickstep skill
     *
     * @param target The target Actor to attack.
     * @param direction The direction of the attack.
     * @param weaponItem The WeaponItem used in the attack.
     */
    public QuickStepAction(Actor target , String direction, WeaponItem weaponItem){
        this.target = target;
        this.direction = direction;
        this.weaponItem = weaponItem;

    }


    /**
     * Executes the action by attacking the target Actor with the WeaponItem, inflicting damage, and moving the actor
     * away from the target if possible.
     *
     * @param actor The actor performing the action.
     * @param map The GameMap containing the actor and the target.
     * @return A String describing the results of the action.
     */
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = this.weaponItem;
        String results = "";
        int specialSkillProbability = RandomNumberGenerator.getRandomInt(100);

        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        if (!(specialSkillProbability <= weapon.chanceToHit())) {
            results = actor + " misses " + target + ".";
        }

        else {
            int damage = weapon.damage();
            results = actor + " do Quick Step to " + target + " for " + damage + " damage and move away.";
            target.hurt(damage);
            if (!target.isConscious()) {

                if(target.hasCapability(Status.REVIVABLE)){

                }
                else{
                    results += new DeathAction(actor).execute(target, map);
                }
            }
        }
        Location target_loc = map.locationOf(this.target);
        Location actor_loc = map.locationOf(actor);
        List<Exit> listOfExit = actor_loc.getExits();
        int curr_dis = 0;
        Location evade_loc = null;
        for (Exit exit: listOfExit){
            if (!(exit.getDestination().containsAnActor()) && exit.getDestination().getGround().canActorEnter(actor) && distance(exit.getDestination(),target_loc) > curr_dis){
                curr_dis = distance(exit.getDestination(),target_loc);
                evade_loc = exit.getDestination();
            }
        }

        if (evade_loc != null){
            results += new MoveActorAction( evade_loc," move away as result of Quick Step.").execute(actor,map);
        }

        return results;
    }



    /**
     * Used to calculate the distance for moving the actor
     *
     * @param a The first location.
     * @param b The second location.
     * @return The distance of the locations
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * Returns a String describing the action for display in the menu.
     *
     * @param actor The actor performing the action.
     * @return A String describing the action.
     */
    public String menuDescription(Actor actor) {
        return actor + " attack " + target + " at " + direction + " with Quick Step skill using weapon " + (actor.getWeaponInventory().get(0) != null ?actor.getWeaponInventory().get(0) : "intrinsic weapon");
    }
}
