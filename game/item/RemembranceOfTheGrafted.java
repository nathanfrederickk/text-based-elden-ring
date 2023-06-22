package game.item;

import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemType;
import game.weapons.SellAble;

/**
 * Remembrance of the Grafted item, can be sold or exchanged to Finger Reader Enia
 */
public class RemembranceOfTheGrafted extends Item implements SellAble {
    /**
     * Constructor for Remembrance of the Grafted
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        this.addCapability(ItemType.REMEMBRANCEOFTHEGRAFTED);
    }

    /**
     * Remembrance of the Grafted can be sold for 20000 runes
     * @return int the selling price of Remembrance of the Grafted
     */
    @Override
    public int getItemSellingPrice() {
        return 20000;
    }

    /**
     * Method to get the item type of Remembrance of the Grafted
     * @return ItemType the Item Type of this
     */
    @Override
    public ItemType getWeaponType() {
        return ItemType.REMEMBRANCEOFTHEGRAFTED;
    }
}
