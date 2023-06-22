package game.map;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.ResetManager;
import game.actors.FingerReaderEnia;
import game.actors.Player;
import game.item.GoldenSeeds;
import game.item.rune.GoldenRune;
import game.map.utils.MapDestination;
import game.map.utils.MapSettings;
import game.positions.*;
import game.positions.spawn.*;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Limgrave map
 */
public class LimGrave{

    /**
     * Instance of the Limgrave map
     */
    private static GameMap instance;

    /**
     * Map display layout
     */
    private final static List<String> mapLayout = Arrays.asList(
            "......................#.............#..........................+++.........",
            "......................#.............#.......................+++++..........",
            "......................#..___....____#.........................+++++........",
            "......................#...........__#............................++........",
            "......................#_____........#.............................+++......",
            "......................#............_#..............................+++.....",
            "......................######...######......................................",
            "...........................................................................",
            "...........................=...............................................",
            "........++++......................###___###................................",
            "........+++++++....ll.............________#................................",
            "..........+++....llll.............#________................................",
            "............+++..ll...............#_______#................................",
            ".............+....................###___###................................",
            "............++......................#___#..................................",
            "..............+...................=........................................",
            "..............++.................................................=.........",
            "..............................................++...........................",
            "..................++++......................+++...............######..##...",
            "#####___######....++...........................+++............#....____....",
            "_____________#.....++++..........................+..............__.....#...",
            "_____________#.....+....++........................++.........._.....__.#...",
            "_____________#.........+..+.....................+++...........###..__###...",
            "_____________#.............++..............................................");

    /**
     * The ground components of the limgrave map
     */
    private final static FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(), new Cliff(),
            new SummoningGround(),new Barrack(), new Cage(), new LakeOfRot());

    /**
     * Constructor for the LimGrave class
     * @param groundFactory the ground components of the map
     * @param lines map layout
     */
    public LimGrave(GroundFactory groundFactory, List<String> lines) {
        this.instance = new GameMap(groundFactory, lines);

    }

    /**
     * To get the instance of the LimGrave class and ensure
     * only one LimGrave class is created.
     * @return the instance of LimGrave
     */
    public static GameMap getInstance() {
        if(instance == null){
            new LimGrave(groundFactory, mapLayout);
        }
        return instance;
    }

    /**
     * To add the LimGrave to the world and add other components
     * to the map
     * @param world the world LimGrave class will be added into
     */
    public static void addToListOfWorlds(World world) {
        world.addGameMap(LimGrave.getInstance());

        // adds the trader Merchant Kale
        MapSettings.addTrader(40, 12, "Merchant Kale", LimGrave.getInstance());
        MapSettings.addActor(40,11, new FingerReaderEnia(), LimGrave.getInstance());

        // adds the player and sets it respawn location
        Player player = Player.getInstance("Tarnished", '@');
        world.addPlayer(player, instance.at(36, 10));

        ResetManager.getInstance().registerResettable(player);
        player.setRespawnLocation(LimGrave.getInstance().at(36,10));

        // adds golden runes
        MapSettings.addItem(32, 10, new GoldenRune(), LimGrave.getInstance());
        MapSettings.addItem(5, 3, new GoldenRune(), LimGrave.getInstance());
        MapSettings.addItem(30, 10, new GoldenRune(), LimGrave.getInstance());
        MapSettings.addItem(25, 10, new GoldenRune(), LimGrave.getInstance());

        // adds the site of lost grace
        MapSettings.addSiteOfLostGrace(38, 11, "The First Step", getInstance());

        // adds the golden fog doors
        MapSettings.addGoldenFogDoor(3, 23, new MapDestination(RoundTableHold.getInstance(), 9, 10, RoundTableHold.getName()), getInstance());
        MapSettings.addGoldenFogDoor(30, 0, new MapDestination(StormVeilCastle.getInstance(), 38, 23, StormVeilCastle.getName()), getInstance());


        //adds Golden seeds to stormveil castle
        MapSettings.addItem(32, 13, new GoldenSeeds(), LimGrave.getInstance());
        MapSettings.addItem(11, 21, new GoldenSeeds(), LimGrave.getInstance());
        MapSettings.addItem(24, 3, new GoldenSeeds(), LimGrave.getInstance());
    }

    /**
     * Name of the map
     * @return name of the map
     */
    public static String getName() {
        return "Limgrave";
    }
}
