package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AreaAttackAction;
import game.enums.ItemType;

public class Grossmesser extends WeaponItem implements SellAble  {
    /**
     * Constructor of Grossmesser item class , set attribute (name,displayChar,damage,damage,verb,and hit rate)
     * add capability WeaponType.Grossmesser as this weapon identifier
     */
    public Grossmesser(){
        super("Grossmesser", '?', 115, "slashes", 85);
        this.addCapability(ItemType.GROSSMESSER);
    }

    @Override
    /**
     * Get an active skill action from the weapon. This should be used for weapon skills that do not involve a target actor
     * For instance, healing the holder of the weapon, switching current weapon's attack, e.g. from normal attack to fire attack
     * @param holder weapon holder
     * @return a special Action that can be performed by this weapon (heal the player, etc.)
     */
    public Action getSkill(Actor holder) {
        return new AreaAttackAction(holder, this);
    }

    @Override
    /**
     * method to get the selling price for this item
     * return runes value that player will obtained if player sell them
     */
    public int getItemSellingPrice() {
        return 100;
    }

    @Override
    /**
     * method to get the weapon type of this item
     * return weapon type of this weapon object
     */
    public ItemType getWeaponType() {
        return ItemType.GROSSMESSER;
    }
}
