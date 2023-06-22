package game.map.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Trader;
import game.positions.GoldenFogDoor;
import game.positions.SiteOfLostGrace;

/**
 * Helper method to add items, actors, or grounds
 * to a map
 */
public class MapSettings {

    /**
     * Static method to add a Golden Fog Door to a map
     * @param x x coordinate to insert the golden fog door
     * @param y y coordinate to insert the golden fog door
     * @param mapDestination where the door leads to
     * @param currentMap current map where the door wants to be located in
     */
    public static void addGoldenFogDoor(int x, int y, MapDestination mapDestination, GameMap currentMap) {
        GoldenFogDoor door = new GoldenFogDoor(mapDestination);
        currentMap.at(x, y).setGround(door);
    }

    /**
     * Static method to add a Trader to a map
     * @param x x coordinate to insert the Trader
     * @param y y coordinate to insert the Trader
     * @param name name of the Trader
     * @param gameMap map where Trader is intended to be added
     */
    public static void addTrader(int x, int y, String name, GameMap gameMap) {
        Trader trader = new Trader(name);
        gameMap.at(x, y).addActor(trader);
    }

    /**
     * Static method to add a SiteOfLostGrace to a map
     * @param x x coordinate to insert the SiteOfLostGrace
     * @param y y coordinate to insert the SiteOfLostGrace
     * @param name name of the SiteOfLostGrace
     * @param gameMap map where SiteOfLostGrace is intended to be added
     */
    public static void addSiteOfLostGrace(int x, int y, String name, GameMap gameMap) {
        SiteOfLostGrace siteOfLostGrace = new SiteOfLostGrace(name);
        gameMap.at(x, y).setGround(siteOfLostGrace);
    }

    /**
     * Static method to add an Actor to a map
     * @param x x coordinate to insert the Actor
     * @param y y coordinate to insert the Actor
     * @param actor actor to be added
     * @param gameMap map where the Actor is intended to be added
     */
    public static void addActor(int x, int y, Actor actor, GameMap gameMap){
        gameMap.at(x,y).addActor(actor);
    }

    /**
     /**
     * Static method to add an Item to a map
     * @param x x coordinate to insert the Item
     * @param y y coordinate to insert the Item
     * @param item item to be added
     * @param gameMap map where the Item is intended to be added
     */
    public static void addItem(int x, int y, Item item, GameMap gameMap){
        gameMap.at(x,y).addItem(item);
    }
}

