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
import game.positions.spawn.Barrack;
import game.positions.spawn.Cage;
import game.positions.spawn.GustOfWind;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a StormVeilCastle map
 */
public class StormVeilCastle{

    /**
     * To store the instance of the map
     */
    private static GameMap instance;

    /**
     * The display map layout
     */
    private final static List<String> mapLayout = Arrays.asList(
            "...........................................................................",
            "..................<...............<........................................",
            "...........................................................................",
            "##############################################...##########################",
            "............................#................#.......B..............B......",
            ".....B...............B......#................#.............................",
            "...............................<.........<.................................",
            ".....B...............B......#................#.......B..............B......",
            "............................#................#.............................",
            "#####################..#############...############.####..#########...#####",
            "...............#++++++++++++#................#++++++++++++#................",
            "...............#++++++++++++...<.........<...#++++++++++++#................",
            "...............#++++++++++++..................++++++++++++#................",
            "...............#++++++++++++#................#++++++++++++#................",
            "#####...##########.....#############...#############..#############...#####",
            ".._______........................B......B........................B.....B...",
            "_____..._..____....&&......................................................",
            ".........____......&&......................................................",
            "...._______..................<..............<....................<.....<...",
            "#####....##...###..#####...##########___###############......##.....####...",
            "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
            "+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
            "+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
            "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

    /**
     * The ground components of the map
     */
    private final static FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Cliff(), new GustOfWind(), new Barrack(), new Cage());

    /**
     * Constructor for the StormVeilCastle class
     * @param groundFactory the ground components of the map
     * @param lines map layout
     */
    public StormVeilCastle(GroundFactory groundFactory, List<String> lines) {
        this.instance = new GameMap(groundFactory, lines);
    }

    /**
     * To get the instance of the StormVeilCastle class and ensure
     * only one StormVeilCastle class is created.
     * @return the instance of StormVeilCastle
     */
    public static GameMap getInstance() {
        if(instance == null){
            new StormVeilCastle(groundFactory, mapLayout);
        }
        return instance;
    }

    /**
     * To add the StormVeilCastle to the world and add other components
     * to the map
     * @param world the world StormVeilCastle class will be added into
     */
    public static void addToListOfWorlds(World world) {
        world.addGameMap(StormVeilCastle.getInstance());

        // adds the SiteOfLostGrace to the map
        MapSettings.addSiteOfLostGrace(38, 20, "Stormveil Main Gate", getInstance());

        // adds Golden Runes
        MapSettings.addItem(36, 23, new GoldenRune(), StormVeilCastle.getInstance());
        MapSettings.addItem(37, 18, new GoldenRune(), StormVeilCastle.getInstance());
        MapSettings.addItem(34, 21, new GoldenRune(), StormVeilCastle.getInstance());

        // adds GoldenFogDoors to both limgrave and bossRoom map
        MapSettings.addGoldenFogDoor(38, 23, new MapDestination(LimGrave.getInstance(), 30, 0, LimGrave.getName()), getInstance());
        MapSettings.addGoldenFogDoor(5, 0, new MapDestination(BossRoom.getInstance(), 0, 4, BossRoom.getName()), getInstance());


        //adds Golden seeds to stormveil castle
        MapSettings.addItem(35, 18, new GoldenSeeds(), StormVeilCastle.getInstance());
        MapSettings.addItem(12, 21, new GoldenSeeds(), StormVeilCastle.getInstance());
        MapSettings.addItem(3, 2, new GoldenSeeds(), StormVeilCastle.getInstance());
    }

    /**
     * To get the name of the map
     * @return name of the map
     */
    public static String getName() {
        return "StormVeil Castle";
    }
}
