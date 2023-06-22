package game.actions.attack;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.RandomNumberGenerator;
import game.actions.DeathAction;
import game.enums.Status;

/**
 * Range attack
 */
public class RangedAttackAction extends Action {
    /**
     * The target Actor to attack.
     */
    private final Actor target;
    /**
     * The direction of the attack.
     */
    private String direction;

    /**
     * The WeaponItem used in the attack.
     */
    private WeaponItem weaponItem;

    /**
     * Constructor
     * @param target the target
     * @param direction direction of the attack
     * @param weaponItem the item to attack with
     */
    public RangedAttackAction(Actor target, String direction, WeaponItem weaponItem){
        this.target = target;
        this.direction = direction;
        this.weaponItem = weaponItem;

    }

    /**
     * To do the actual ranged attack
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return attack actor with distance up to 2 blocks away
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int specialSkillProbability = RandomNumberGenerator.getRandomInt(100);

        if (!(specialSkillProbability <= weaponItem.chanceToHit())) {
            return actor + " misses " + target + ".";
        }


        int damage = this.weaponItem.damage() ;

        String result = actor + " unsheathe " + target + " for " + damage + " damage.";

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
     * Option to do the range attack
     * @param actor The actor performing the action.
     * @return range attack
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoot " + target +" at "+ direction +  " with " + (actor.getWeaponInventory().get(0) != null ?weaponItem: "intrinsic weapon");
    }
}
