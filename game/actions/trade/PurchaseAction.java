package game.actions.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Trader;
import game.item.rune.RuneManager;

/**
 * Class used to purchase a WeaponItem from a Merchant Kale using Runes.
 */
public class PurchaseAction extends Action {
    private Actor playerPurchasing;
    private int amountOfRunes;
    private WeaponItem itemBought;

    private Trader trader;

    /**
     * Constructs a PurchaseAction with the specified parameters.
     *
     * @param playerPurchasing the Actor purchasing the WeaponItem.
     * @param amountOfRunes the amount of Runes needed to make the purchase.
     * @param itemBought the WeaponItem being purchased.
     */
    public PurchaseAction(Actor playerPurchasing, int amountOfRunes, WeaponItem itemBought, Trader trader){
        this.playerPurchasing = playerPurchasing;
        this.amountOfRunes = amountOfRunes;
        this.itemBought = itemBought;
        this.trader = trader;
    }

    /**
     * Executes the PurchaseAction by adding the WeaponItem to the Actor's inventory and decreasing their Rune amount.
     *
     * @param actor the Actor performing the action.
     * @param map the GameMap on which the action is being performed.
     * @return a message describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(RuneManager.getInstance().getRuneAmount(actor).getRuneValue() < amountOfRunes){
            return "Purchase action failed! The player does not have enough runes!";
        }

        this.playerPurchasing.addWeaponToInventory(itemBought);
        RuneManager.getInstance().getRuneAmount(actor).decreaseRune(this.amountOfRunes);

        return actor + " bought the " + this.itemBought + " weapon for a price of " + this.amountOfRunes + " runes from Merchant Kale.";
    }

    /**
     * Returns a menu description of the PurchaseAction.
     *
     * @param actor the Actor performing the action.
     * @return a menu description of the PurchaseAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + this.itemBought + " from " + trader.getName() + " for a price of " + this.amountOfRunes + " runes.";
    }
}
