package game.item;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for all cosumable item, all item that can be consumed will implement this interface
 */
public interface Consumable {
    /**
     * method for consuming this item, will modify user attribute depend on item effect
     * @param actor : actor that will consume this item
     */
    void consume(Actor actor);

    /**
     * method to get number of remaining times spesific item can be used
     * @return number of remaining times spesific item can be used
     */
    int getUseCount();
}
