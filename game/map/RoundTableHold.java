package game.map;


import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.item.GoldenSeeds;
import game.item.rune.GoldenRune;
import game.map.utils.MapDestination;
import game.map.utils.MapSettings;
import game.positions.*;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a RoundTableHold map
 */
public class RoundTableHold {

    /**
     * To store the instance of the map
     */
    private static GameMap instance;

    /**
     * Map display layout
     */
    private final static List<String> mapLayout = Arrays.asList(
            "##################",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "#________________#",
            "########___#######");

    /**
     * The ground components of the RoundTableHold map
     */
    private final static FancyGroundFactory groundFactory = new FancyGroundFactory(new Wall(), new Floor());

    /**
     * Constructor for the RoundTableHold class
     * @param groundFactory the ground components of the map
     * @param lines map layout
     */
    public RoundTableHold(GroundFactory groundFactory, List<String> lines) {
        this.instance = new GameMap(groundFactory, lines);
    }

    /**
     * To get the instance of the RoundTableHold class and ensure
     * only one RoundTableHold class is created.
     * @return the instance of RoundTableHold
     */
    public static GameMap getInstance() {
        if(instance == null){
            new RoundTableHold(groundFactory, mapLayout);
        }
        return instance;
    }

    /**
     * To add the RoundTableHold to the world and add other components
     * to the map
     * @param world the world RoundTableHold class will be added into
     */
    public static void addToListOfWorlds(World world) {
        world.addGameMap(RoundTableHold.getInstance());

        // adds the SiteOfLostGrace
        MapSettings.addSiteOfLostGrace(9, 5, "Table of Lost Grace", getInstance());

        // adds a GoldenFogDoor back to limgrave map
        MapSettings.addGoldenFogDoor(9, 10, new MapDestination(LimGrave.getInstance(), 3, 23, LimGrave.getName()), getInstance());

        //add golden seeds
        MapSettings.addItem(9,7,new GoldenSeeds(),getInstance());
    }

    /**
     * Map name
     * @return map name
     */
    public static String getName() {
        return "Roundtable Hold";
    }
}
