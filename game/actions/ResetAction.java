package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;

/**
 * Class used to reset the game using ResetManager
 */
public class ResetAction extends Action {

    /**
     * Get instance of resetManager
     */
    private ResetManager resetManager = ResetManager.getInstance();

    /**
     * Resets the game by calling the ResetManager's run() method.
     *
     * @param actor the actor performing the action
     * @param map   the map where the action is performed
     * @return a string describing the action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        resetManager.reset(map);
        return menuDescription(actor);
    }

    /**
     * Returns a string describing the reset action performed.
     *
     * @param actor the actor performing the action
     * @return a string describing the reset action performed
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Game has been reset by " + actor;
    }
}
