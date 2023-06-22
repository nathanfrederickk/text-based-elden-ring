package game.item.rune;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actors.Player;
import game.enums.Status;
import game.item.Consumable;

/**
 * Golden Rune class
 */
public class GoldenRune extends Item implements Consumable {

    /**
     * Is it added to player's inventory
     */
    private boolean added;

    /**
     * To store the ConsumeAction so that it could be removed
     */
    private ConsumeAction consumeAction;

    /**
     * The player holding the golden rune
     */
    private Actor player;

    /**
     * The final amount of use of each Golden Rune
     */
    private final int USE_COUNT = 1;

    /**
     * Constructor
     */
    public GoldenRune(){
        super("Golden Rune", '*', true);
        this.added = false;
    }

    /**
     * Consume the Golden Rune to acquire runes between the range of 200 to 10000
     * @param actor : actor that will consume this item
     */
    @Override
    public void consume(Actor actor) {
        Rune newRune = new Rune(200,10000);
        RuneManager runeManager = RuneManager.getInstance();
        runeManager.getRuneAmount(actor).addRune(newRune.getRuneValue());
        actor.removeItemFromInventory(this);
    }

    /**
     * How many times it can be used
     * @return
     */
    @Override
    public int getUseCount() {
        return this.USE_COUNT;
    }

    /**
     * To add the consumeAction
     * @param actor actor to add the consumeAction
     * @param map current map
     */
    public void addAction(Actor actor, GameMap map){
        super.addAction(new ConsumeAction(this));
    }

    /**
     * To add the consumeAction if picked up by Player
     * @param location The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location location, Actor actor){
        for(Item item: actor.getItemInventory()){
            if(item == this){
                if(!added){
                    this.consumeAction = new ConsumeAction(this);
                    addAction(this.consumeAction);
                    this.player = actor;
                }
                added = true;
                return;
            }
        }
    }

    /**
     * To reset the attributes of the golden rune
     * when it is dropped by player so it can be
     * picked up again.
     */
    public void resetAttributes(){
        this.player = null;
        this.added = false;
    }

    /**
     * If golden rune is dropped from the players' inventory then the
     * consumeAction will be removed
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.player != null) {
            for (Item item : currentLocation.getItems()){
                if(item == this){
                    removeAction(this.consumeAction);
                }
            }
            this.resetAttributes();
        }
    }
}
