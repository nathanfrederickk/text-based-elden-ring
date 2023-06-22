package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.QuickStepAction;
import game.enums.ItemType;

public class GreatKnife extends WeaponItem implements SellAble, PurchaseAble {
    /**
     * Constructor of GreatKnife item class , set attribute (name,displayChar,damage,damage,verb,and hit rate)
     * add capability WeaponType.GreatKnife as this weapon identifier
     */
    public GreatKnife(){
        super("Great Knife", '/', 75, "stabs", 70);
        this.addCapability(ItemType.GREATKNIFE);
    }

    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill
     * against one targeted Actor (i.e, special attack, heal, stun, etc.).
     * @param target target actor
     * @return a special Action that can be performed by this weapon (perform special attack on the enemy, etc.)
     */
    public Action getSkill(Actor target, String direction) {
        return new QuickStepAction(target,direction, this);
    }

    @Override
    /**
     *  method to get the buying price for this Item
     * return Runes value of item if player want to buy them
     */
    public int getItemBuyPrice() {
        return 3500;
    }

    @Override
    /**
     * method to get the selling price for this item
     * return runes value that player will obtained if player sell them
     */
    public int getItemSellingPrice() {
        return 350;
    }

    @Override
    /**
     * method to get the weapon type of this item
     * return weapon type of this weapon object
     */
    public ItemType getWeaponType() {
        return ItemType.GREATKNIFE;
    }
}
