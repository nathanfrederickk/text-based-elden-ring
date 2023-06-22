package game.actions.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Trader;
import game.item.rune.RuneManager;

/**
 * Class to allow Player to sell items to Merchant for Runes
 */
public class SellAction extends Action {
    /**
     * The player selling the weapon item
     */
    private Actor playerSelling;

    /**
     * The amount of runes obtained from the sale
     */
    private int amountOfRunes;

    /**
     * The weapon item being sold
     */
    private WeaponItem itemSold;

    private String traderName;

    /**
     * Constructor for SellAction
     *
     * @param playerSelling the player selling the weapon item
     * @param amountOfRunes the amount of runes obtained from the sale
     * @param itemBought the weapon item being sold
     */
    public SellAction(Actor playerSelling, int amountOfRunes, WeaponItem itemBought, String traderName){
        this.playerSelling = playerSelling;
        this.amountOfRunes = amountOfRunes;
        this.itemSold = itemBought;
        this.traderName = traderName;
    }

    /**
     * Executes the sell action by removing the weapon item from the player's inventory and adding the amount of runes obtained to their rune amount.
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a string representing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.playerSelling.removeWeaponFromInventory(this.itemSold);
        RuneManager.getInstance().getRuneAmount(this.playerSelling).addRune(this.amountOfRunes);

        return actor + " sold the " + this.itemSold + " weapon for a price of " + this.amountOfRunes + " runes to Merchant Kale.";

    }

    /**
     * Returns a string representing the sell action that can be displayed in a menu.
     *
     * @param actor the actor performing the action
     * @return a string representing the sell action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sell " + this.itemSold + " to " + traderName + " for " + this.amountOfRunes + " runes.";
    }
}
