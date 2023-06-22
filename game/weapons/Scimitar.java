package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AreaAttackAction;
import game.enums.ItemType;

public class Scimitar extends WeaponItem implements SellAble, PurchaseAble{
    /**
     * Constructor of Schimitar item class , set attribute (name,displayChar,damage,damage,verb,and hit rate)
     * add capability WeaponType.Schimitar as this weapon identifier
     */
    public Scimitar(){
        super("Scimitar",'S',118,"slash",88);
        this.addCapability(ItemType.SCIMITAR);
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
     *  method to get the buying price for this Item
     * return Runes value of item if player want to buy them
     */
    public int getItemBuyPrice() {
        return 600;
    }

    @Override
    /**
     * method to get the weapon type of this item
     * return weapon type of this weapon object
     */
    public ItemType getWeaponType() {
        return ItemType.SCIMITAR;
    }
}
