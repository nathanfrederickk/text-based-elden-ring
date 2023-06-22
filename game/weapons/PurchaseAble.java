package game.weapons;

import game.enums.ItemType;

/**
 * Interface to purchaseable item , any item that can be bought will implement this interface
 */
public interface PurchaseAble {
    /**
     *  method to get the buying price for this Item
     * return Runes value of item if player want to buy them
     */
    int getItemBuyPrice();

    /**
     * method to get the weapon type of this item
     * return weapon type of this weapon object
     */
    ItemType getWeaponType();
}
