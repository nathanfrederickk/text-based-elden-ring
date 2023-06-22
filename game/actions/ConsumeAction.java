package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.item.Consumable;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class for actor to consume a consumable item
 */
public class ConsumeAction extends Action {
    /**
     * The consumable item being consumed.
     */
    private Consumable item;

    /**
     * Constructor for ConsumeAction with the specified actor, game map, and consumable item.
     * @param item The consumable item being consumed.
     */
    public ConsumeAction(Consumable item){
        this.item = item;
    }

    /**
     * Executes the action by consuming the consumable item and modifying the actor's state.
     * @param actor The actor performing the action.
     * @param map The game map the actor is on.
     * @return Message describing the outcome of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.consume(actor);
        return menuDescription(actor);
    }

    /**
     * Returns a string describing the ConsumeAction.
     * @param actor The actor performing the action.
     * @return A message describing the ConsumeAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses " + item + " (" + item.getUseCount() + ")";
    }
}
