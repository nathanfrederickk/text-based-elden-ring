package game.map.utils;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * To contain all the information needed
 * for an actor to travel from one map to another
 */
public class MapDestination {

    /**
     * the destination of the map that the
     * player intends to go to
     */
    private final GameMap mapDesign;

    /**
     * the x coordinate of the location
     * actor will go to in the map destination
     */
    private final int x_coordinate;

    /**
     * the y coordinate of the location
     * actor will go to in the map destination
     */
    private final int y_coordinate;

    /**
     * To store the map name in String
     */
    private String mapName;

    /**
     * Constructor class for the MapDestination class
     * @param destination destination map
     * @param x x coordinate in the new map
     * @param y y coordinate in the new map
     */
    public MapDestination(GameMap destination, int x, int y, String mapName) {
        this.mapDesign = destination;
        this.x_coordinate = x;
        this.y_coordinate = y;
        this.mapName = mapName;
    }

    /**
     * To get the x coordinate of the destination
     * @return x coordinate of the destination
     */
    public int getX() {
        return x_coordinate;
    }

    /**
     * To get the y coordinate of the destination
     * @return y coordinate of the destination
     */
    public int getY() {
        return y_coordinate;
    }

    /**
     * To get the destination map
     * @return destination map
     */
    public GameMap getDestinationMap() {
        return mapDesign;
    }

    /**
     * To get the map name in string
     * @return map name
     */
    public String getMapName(){
        return this.mapName;
    }


}
