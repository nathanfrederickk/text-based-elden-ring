package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AttackAction;
import game.enums.ItemType;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Astrologer Staff to be used by Actors with the Astrologer combat archetype
 */
public class AstrologerStaff extends WeaponItem implements SellAble, PurchaseAble{
    /**
     * Astrologer staff can have a ranged attack
     */
    private List<Action> rangeAction = new ArrayList<>();

    /**
     * Astrologer staff constructor
     */
    public AstrologerStaff() {
        super("Astrologer's staff", 'f', 274, "magic attacks", 50);
        this.addCapability(ItemType.ASTROLOGERSTAFF);
        this.addCapability(ItemType.Ranged);
    }

    /**
     * Get Astrologer staff selling price
     * @return int the selling price of this item
     */
    @Override
    public int getItemSellingPrice() {
        return 100;
    }

    /**
     * Get Astrologer staff buy price
     * @return int the buying cost of this item
     */
    @Override
    public int getItemBuyPrice() {
        return 800;
    }

    /**
     * Get the weapon type of this item
     * @return ItemType the item type of this item
     */
    @Override
    public ItemType getWeaponType() {
        return ItemType.ASTROLOGERSTAFF;
    }

    /**
     * tick method to allow the staff to have a ranged attack
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
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
                for(Exit exit3: exit2.getDestination().getExits()){
                    if(!locUnique.contains(exit3.getDestination()) & !(exit3.getDestination() == currentLocation)){
                        locUnique.add(exit3.getDestination());
                        locTarget.add(exit3.getDestination());
                    }
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

    @Override
    public List<Action> getAllowableActions() {
        return this.rangeAction;
    }

}
