package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.positions.SiteOfLostGrace;


/**
 * Class to allow the Player to rest and reset the game
 */
public class RestAction extends Action {

    private SiteOfLostGrace siteOfLostGrace;

    public RestAction(SiteOfLostGrace siteOfLostGrace){
        this.siteOfLostGrace = siteOfLostGrace;
    }

    /**
     * Perform the rest and reset action
     *
     * @param actor the Actor that is performing the RestAction
     * @param map   the GameMap the Actor is currently on
     * @return a string describing the RestAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetAction resetAction = new ResetAction();
        resetAction.execute(actor,map);
        return menuDescription(actor);
    }

    /**
     * Returns a string describing the Rest Action.
     *
     * @param actor the Actor that is performing the Rest Action
     * @return a string describing the Rest Action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Tarnished rests at the " + this.siteOfLostGrace + " Site Of Lost Grace";
    }
}
