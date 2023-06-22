package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.trade.ExchangeAction;
import game.actions.trade.SellAction;
import game.enums.ItemType;
import game.enums.Status;
import game.item.RemembranceOfTheGrafted;
import game.weapons.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Trader that the Player can buy or sell weapon from. The buy or sell actions
 * are done by using Runes.
 */
public class FingerReaderEnia extends Actor {

    /**
     * The list of weapons sellAble from the Trader
     */
    private List<SellAble> weaponsSellable = new ArrayList<>();

    private String name;

    /**
     * Player can buy or sell weapons with Merchant Kale if  player has sufficient runes.
     * Merchant Kale cannot be attacked and is not hostile to player
     */
    public FingerReaderEnia(){
        super("Finger Reader Enia", 'E', 999);

        this.weaponsSellable.add(new Uchigatana());
        this.weaponsSellable.add(new GreatKnife());
        this.weaponsSellable.add(new Club());
        this.weaponsSellable.add(new Grossmesser());
        this.weaponsSellable.add(new Scimitar());
        this.weaponsSellable.add(new AstrologerStaff());
        this.weaponsSellable.add(new RemembranceOfTheGrafted());

        this.name = "Finger Reader Enia";
    }

    /**
     * The allowable actions of player to Trader
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the actions that can be done by player to Trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.PLAYER)){

            for(SellAble weapon: this.weaponsSellable){
                ItemType itemType = weapon.getWeaponType();
                // For Remembrance of the Grafted item, it can be exchanged for Axe of Godrick or Grafted Dragon
                if(itemType == ItemType.REMEMBRANCEOFTHEGRAFTED){
                    Item item = this.getItemInActor(otherActor, itemType);
                    if(otherActor.hasCapability(itemType)){
                            actions.add(new ExchangeAction(otherActor, new AxeOfGodrick(), item, this));
                            actions.add(new ExchangeAction(otherActor, new GraftedDragon(), item, this));
                        }
                }
                else if(otherActor.hasCapability(itemType)){
                    WeaponItem weaponItem = this.getWeaponInActor(otherActor, itemType);
                        actions.add(new SellAction(otherActor, weapon.getItemSellingPrice(), weaponItem, this.name));
                }

            }

        }
        return actions;
    }

    /**
     * Trader only has a do nothing behaviour
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action that merchant kale does, which is doing nothing.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * To get the weapon with the same weapon type in the actor's inventory.
     * @param actor The Actor who owns the inventory
     * @param itemType The weapon type that wants to be returned
     * @return the weapon
     */
    public WeaponItem getWeaponInActor(Actor actor, ItemType itemType){
        for(WeaponItem weaponItem : actor.getWeaponInventory()){
            if (weaponItem.hasCapability(itemType)){
                return weaponItem;
            }
        }
        return null;
    }

    /**
     * To get the item with the same item type in the actor's inventory.
     * @param actor The Actor who owns the inventory
     * @param itemType The item type that wants to be returned
     * @return the item
     */
    public Item getItemInActor(Actor actor, ItemType itemType){
        for(Item item : actor.getItemInventory()){
            if (item.hasCapability(itemType)){
                return item;
            }
        }
        return null;
    }
}

