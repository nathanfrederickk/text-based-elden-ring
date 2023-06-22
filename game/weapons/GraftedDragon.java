package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AreaAttackAction;
import game.enums.ItemType;

/**
 * Grafted Dragon item
 */
public class GraftedDragon extends WeaponItem implements SellAble {

    /**
     * Constructor
     */
    public GraftedDragon(){
        super("Grafted Dragon", 'N', 89, "attacks", 90);
    }

    /**
     * Selling price of item
     * @return selling price
     */
    @Override
    public int getItemSellingPrice() {
        return 200;
    }

    /**
     * Weapon type of weapon
     * @return weapon type of weapon
     */
    @Override
    public ItemType getWeaponType() {
        return ItemType.GRAFTEDDRAGON;
    }

    /**
     * Skill of weapon
     * @param holder weapon holder
     * @return skill of weapon
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AreaAttackAction(holder, this);
    }
}
