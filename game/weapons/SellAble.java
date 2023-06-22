package game.weapons;

import game.enums.ItemType;

/**
 * Interface to sellable item , any item that can be sold will implement this interface
 */
public interface SellAble{


    /**
     * method to get the selling price for this item
     * return runes value that player will obtained if player sell them
     */
    int getItemSellingPrice();

    /**
     * method to get the weapon type of this item
     * return weapon type of this weapon object
     */
    ItemType getWeaponType();
}
