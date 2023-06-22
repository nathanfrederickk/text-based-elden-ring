package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.actions.ConsumeAction;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Golden Seeds class
 */
public class GoldenSeeds extends Item implements Consumable, Resettable {

    /**
     * Track number of use of item
     */
    private int useCount;

    /**
     * To store the consume action
     */
    private List <Action> L1;

    /**
     * Constructor
     */
    public GoldenSeeds(){
        super("Golden Seed", 'S',true);
        this.useCount = 1;
        this.L1 = new ArrayList<>();
    }

    /**
     * Enables consume action in actor if picked up by actor
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation,Actor actor) {
        if(this.getUseCount() > 0 & FlaskOfCrimsonTears.getInstance().getUseCount() < 2)
            L1.add(new ConsumeAction(this));
        else if (L1.size() > 0){
            L1.remove(0);
        }
        if(this.useCount == 0){
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Reset the number of item in inventory
     * @param map : map that currently used
     */
    @Override
    public void reset(GameMap map) {
        this.useCount = 1;
    }

    /**
     * Consume the golden seeds
     * @param actor : actor that will consume this item
     */
    @Override
    public void consume(Actor actor) {
        if(actor.hasCapability(Status.PLAYER) & this.useCount >0 & FlaskOfCrimsonTears.getInstance().getUseCount() < 2){
            FlaskOfCrimsonTears.getInstance().increaseUse();
            this.useCount -=1;
        }
    }

    /**
     * Get the number of uses
     * @return number of uses
     */
    public int getUseCount(){
        return this.useCount;
    }

    /**
     * Allowable actions of golden seeds to actor
     * @return consume action
     */
    @Override
    public List<Action> getAllowableActions() {
        return this.L1;
    }
}
