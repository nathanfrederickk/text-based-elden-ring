package game;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public interface Resettable {
    /**
     * method to reset object
     * @param map : map that currently used
     */
    void reset(GameMap map);
}
