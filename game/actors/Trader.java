package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.trade.PurchaseAction;
import game.actions.trade.SellAction;
import game.enums.ItemType;
import game.enums.Status;
import game.weapons.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Trader that the Player can buy or sell weapon from. The buy or sell actions
 * are done by using Runes.
 */
public class Trader extends Actor {

    /**
     * The list of weapons purchaseAble from the Trader
     */
    private List<PurchaseAble> weaponsPurchaseAble = new ArrayList<>();

    /**
     * The list of weapons sellAble from the Trader
     */
    private List<SellAble> weaponsSellable = new ArrayList<>();

    /**
     * Inventory of the Trader so that it could get the weapon needs
     * to be sold
     */
    private List<WeaponItem> weaponsForSale = new ArrayList<>();

    /**
     * Trader's name
     */
    private String name;

    /**
     * Player can buy or sell weapons with Merchant Kale if  player has sufficient runes.
     * Merchant Kale cannot be attacked and is not hostile to player
     */
    public Trader(String name){
        super(name, 'K', 999);
        this.weaponsPurchaseAble.add(new Uchigatana());
        this.weaponsPurchaseAble.add(new GreatKnife());
        this.weaponsPurchaseAble.add(new Club());
        this.weaponsPurchaseAble.add(new Scimitar());
        this.weaponsForSale.add(new Uchigatana());
        this.weaponsForSale.add(new GreatKnife());
        this.weaponsForSale.add(new Club());
        this.weaponsForSale.add(new Scimitar());
        this.weaponsForSale.add(new HeavyCrossbows());

        this.weaponsSellable.add(new Uchigatana());
        this.weaponsSellable.add(new GreatKnife());
        this.weaponsSellable.add(new Club());
        this.weaponsSellable.add(new Grossmesser());
        this.weaponsSellable.add(new Scimitar());
        this.weaponsSellable.add(new HeavyCrossbows());

        this.name = name;
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

                if(otherActor.hasCapability(itemType)){
                   WeaponItem weaponItem = this.getWeaponInActor(otherActor, itemType);

                   actions.add(new SellAction(otherActor, weapon.getItemSellingPrice(), weaponItem, this.name));
                }
            }

            for(PurchaseAble weapon: this.weaponsPurchaseAble){
                ItemType itemType = weapon.getWeaponType();

                WeaponItem weaponItem = this.getWeaponForSale(itemType);
                actions.add(new PurchaseAction(otherActor, weapon.getItemBuyPrice(), weaponItem, this));
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
     * Get the weapon in Trader's inventory
     * @param itemType the weaponTye to search in Trader's inventory
     * @return the weaponItem with the same weaponType as param
     */
    public WeaponItem getWeaponForSale(ItemType itemType){
        for(WeaponItem weaponItem : this.weaponsForSale){
            if (weaponItem.hasCapability(itemType)){
                return weaponItem;
            }
        }
        return null;
    }

    public String getName(){
        return this.name;
    }
}

