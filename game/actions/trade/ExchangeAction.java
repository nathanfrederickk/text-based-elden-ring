package game.actions.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class ExchangeAction extends Action {
    private Actor playerPurchasing;
    private WeaponItem itemBought;
    private Item exchangeItem;
    private Actor trader;

    public ExchangeAction(Actor playerPurchasing, WeaponItem itemBought, Item exchangeItem, Actor trader){
        this.playerPurchasing = playerPurchasing;
        this.itemBought = itemBought;
        this.exchangeItem = exchangeItem;
        this.trader = trader;
    }

    public String execute(Actor actor, GameMap map){
        this.playerPurchasing.removeItemFromInventory(exchangeItem);
        this.playerPurchasing.addWeaponToInventory(this.itemBought);

        return actor + " exchanged the " + this.exchangeItem +  "with " + this.itemBought + " from Finger Reader Enia.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchange " + this.exchangeItem + " for " + this.itemBought + " from " + this.trader;
    }
}
