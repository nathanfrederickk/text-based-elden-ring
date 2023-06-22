package game;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private CopyOnWriteArrayList<Resettable> resettables;
    private CopyOnWriteArrayList<Resettable> resetOnDeath;
    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new CopyOnWriteArrayList<>();
        this.resetOnDeath = new CopyOnWriteArrayList<>();
    }

    /**
     * Instance of reset manager
     * @return instance of reset manager
     */
    public static ResetManager getInstance(){
        if(instance==null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset all the resettables
     * @param map current map
     */
    public void reset(GameMap map) {
        for(Resettable resettable: resettables){
            resettable.reset(map);
        }
        this.resettables.clear();
    }

    /**
     * Reset when player dies
     * @param map current map
     */
    public void resetPlayerDeath(GameMap map){
        for(Resettable resettable: resetOnDeath){
            resettable.reset(map);
        }
        for(Resettable resettable: resettables){
            resettable.reset(map);
        }
        this.resetOnDeath.clear();
        this.resettables.clear();
    }

    /**
     * Register the reset
     * @param resettable class to be reset
     */
    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    /**
     * Reset when player dies
     * @param resettable class to be reset
     */
    public void registerDeathReset(Resettable resettable){this.resetOnDeath.add(resettable);}

    /**
     * Remove resettable
     * @param resettable class to be removed from resettables
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }

    /**
     * Remove resettable
     * @param resettable class to be removed from resettables
     */
    public void removeDeathReset(Resettable resettable){this.resetOnDeath.remove(resettable);}
}
