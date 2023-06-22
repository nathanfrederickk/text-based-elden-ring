package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AttackAction;
import game.enums.Status;
import game.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * Crossbow weapon
 */
public class HeavyCrossbows extends WeaponItem implements  SellAble , PurchaseAble{

    /**
     * Range action
     */
    private List<Action> rangeAction = new ArrayList<>();

    /**
     * Constructor
     */
    public HeavyCrossbows(){
        super("Heavy Crossbows", '}',64," shot",57);
        this.addCapability(ItemType.Ranged);
    }

    /**
     * Item buying price
     * @return buying price
     */
    @Override
    public int getItemBuyPrice() {
        return 1500;
    }

    /**
     * Item selling price
     * @return selling price
     */
    @Override
    public int getItemSellingPrice() {
        return 100;
    }

    /**
     * Weapon type
     * @return weapon type
     */
    @Override
    public ItemType getWeaponType() {
        return ItemType.Ranged;
    }

    /**
     * Scans the surrounding for enemies
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        //now lets reset range attack action list
        this.rangeAction = new ArrayList<>();

        //list of exit 1
        List<Exit> exitUnique = new ArrayList<>(currentLocation.getExits());

        List<Location> locUnique = new ArrayList<>();
        for (Exit exit : exitUnique)
            locUnique.add(exit.getDestination());

        //exit of distance 2
        List <Location> locTarget = new ArrayList<>();

        for (Exit exit1 : currentLocation.getExits() ){
            for(Exit exit2 : exit1.getDestination().getExits()){
                if(!locUnique.contains(exit2.getDestination()) & !(exit2.getDestination() == currentLocation)){
                    locUnique.add(exit2.getDestination());
                    locTarget.add(exit2.getDestination());
                }
            }
        }
        for( Location loc:locTarget ){
            if(loc.containsAnActor()) {
                if (actor.hasCapability(Status.PLAYER)) {
                    if (loc.getActor().hasCapability(Status.HOSTILE_TO_ENEMY) && loc.getActor() != actor && loc != currentLocation) {
                        rangeAction.add(new AttackAction(loc.getActor(), " Ranged Attack ", this));
                    }
                }
                else{
                    boolean sameType = false;
                    for (Enum capability: loc.getActor().capabilitiesList()){
                        for (Enum capability2 : actor.capabilitiesList()){
                            if(capability == capability2 && capability != Status.REVIVABLE && capability != Status.HOSTILE_TO_ENEMY && capability != Status.HOSTILE_TO_PLAYER){
                                sameType = true;
                            }
                        }
                    }
                    if (!sameType){
                        rangeAction.add(new AttackAction(loc.getActor(), " Ranged Attack ", this));
                    }
                }
            }
        }


    }

    /**
     * Allowable actions
     * @return allowable actions
     */
    @Override
    public List<Action> getAllowableActions() {
        return this.rangeAction;
    }
}
