package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.rune.Rune;
import game.item.rune.RuneManager;


/**
 * Class to allow actor to pick a Rune in a GameMap.
 */
public class PickUpRuneAction extends PickUpAction {
    /**
     * Rune object to be picked up
     */
    Rune rune;

    /**
     * Constructor for PickUpRuneAction with the specified Rune object.
     *
     * @param rune the Rune to be picked up by the Actor.
     */
    public PickUpRuneAction(Rune rune){
        super(rune);
        this.rune = rune;
    }

    /**
     * Executes the PickUpRuneAction by adding the Rune's value to the Actor's Rune amount using RuneManager.
     *
     * @param actor the Actor performing the action.
     * @param map the GameMap on which the action is being performed.
     * @return the result of executing the superclass's execute method.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().getRuneAmount(actor).addRune(this.rune.getRuneValue());
        return super.execute(actor, map);
    }
}
