package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AreaAttackAction;
import game.enums.ItemType;

/**
 * Axe of Godrick weapon
 */
public class AxeOfGodrick extends WeaponItem implements SellAble{

    /**
     * Constructor
     */
    public AxeOfGodrick(){
        super("Axe of Godrick", 'T', 142, "attacks", 84);
    }

    /**
     * The item selling price
     * @return
     */
    @Override
    public int getItemSellingPrice() {
        return 100;
    }

    /**
     * The weapon type
     * @return
     */
    @Override
    public ItemType getWeaponType() {
        return ItemType.AXEOFGODRICK;
    }

    /**
     * Skill
     * @param holder weapon holder
     * @return skill of weapon
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AreaAttackAction(holder, this);
    }
}
