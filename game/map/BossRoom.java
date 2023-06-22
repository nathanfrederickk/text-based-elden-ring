package game.map;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.enemy.GodrickTheGrafted;
import game.map.utils.MapSettings;
import game.positions.*;

import java.lang.reflect.GenericArrayType;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a boss room map
 */
public class BossRoom{

    /**
     * the instance of the BossRoom class
     */
    private static GameMap instance;

    /**
     * the map display layout
     */
    private final static List<String> mapLayout = Arrays.asList(
            "+++++++++++++++++++++++++",
            ".........................",
            "..=......................",
            ".........................",
            ".........................",
            ".........................",
            ".........................",
            ".........................",
            "+++++++++++++++++++++++++");

    /**
     * The ground components of the map
     */
    private final static FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Cliff(), new SummoningGround());

    /**
     * Constructor for the BossRoom class
     * @param groundFactory the ground components of the map
     * @param lines map layout
     */
    public BossRoom(GroundFactory groundFactory, List<String> lines) {
        this.instance = new GameMap(groundFactory, lines);
    }

    /**
     * To get the instance of the class, and make sure
     * only one instance of the boss room is created
     * @return the instance of the BossRoom class
     */
    public static GameMap getInstance() {
        if(instance == null){
            new BossRoom(groundFactory, mapLayout);
        }
        return instance;
    }

    /**
     * To add the BossRoom to the world and add other components
     * to the map
     * @param world the world BossRoom class will be added into
     */
    public static void addToListOfWorlds(World world) {
        world.addGameMap(BossRoom.getInstance());
        MapSettings.addActor(13,5,new GodrickTheGrafted(),BossRoom.getInstance());
    }

    /**
     * Map name
     * @return map name
     */
    public static String getName() {
        return "Boss Room";
    }
}
