package game.actions.attack;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.RandomNumberGenerator;
import game.actions.DeathAction;
import game.enums.Status;

import java.util.Random;

/**
 * Quickstep skill implementation
 */
public class UnsheatheAction extends Action {
    /**
     * The target Actor to attack.
     */
    private final Actor target;
    /**
     * The direction of the attack.
     */
    private final String direction;

    /**
     * The WeaponItem used in the attack.
     */
    private final WeaponItem weaponItem;

    /**
     * Constructor for Unsheathe skill
     *
     * @param target The target Actor to attack.
     * @param direction The direction of the attack.
     * @param weaponItem The WeaponItem used in the attack.
     */
    public UnsheatheAction(Actor target, String direction, WeaponItem weaponItem){
        this.target = target;
        this.direction = direction;
        this.weaponItem = weaponItem;
    }

    /**
     * Executes the action by attacking the target Actor with the WeaponItem, inflicting damage
     * @param actor The actor performing the action.
     * @param map The GameMap containing the actor and the target.
     * @return A String describing the results of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int specialSkillProbability = RandomNumberGenerator.getRandomInt(100);

        if (!(specialSkillProbability <= 60)) {
            return actor + " misses " + target + ".";
        }


        int damage = this.weaponItem.damage() * 2;

        String result = actor + " Unsheathe " + target + " for " + damage + " damage.";

        target.hurt(damage);

        if (!target.isConscious()) {

            if(target.hasCapability(Status.REVIVABLE)){

            }
            else{
                result += new DeathAction(actor).execute(target, map);
            }
        }

        return result;
    }

    /**
     * Returns a String describing the action for display in the menu.
     *
     * @param actor The actor performing the action.
     * @return A String describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attack " + target + " at " + direction + " with Unsheathe skill using weapon " + (actor.getWeaponInventory().get(0) != null ? weaponItem : "intrinsic weapon");
    }
}
