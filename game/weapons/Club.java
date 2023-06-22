package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.ItemType;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem implements SellAble, PurchaseAble {

    /**
     * Constructor of club item class , set attribute (name,displayChar,damage,damage,verb,and hit rate)
     * add capability WeaponType Club for class club
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.addCapability(ItemType.CLUB);
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
        return ItemType.CLUB;
    }
}
