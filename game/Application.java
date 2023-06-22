package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

import game.map.BossRoom;
import game.map.LimGrave;
import game.map.RoundTableHold;
import game.map.StormVeilCastle;
import game.positions.*;
import game.actors.*;
import game.positions.spawn.Graveyard;
import game.positions.spawn.GustOfWind;
import game.positions.spawn.PuddleOfWater;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		LimGrave.addToListOfWorlds(world);
		RoundTableHold.addToListOfWorlds(world);
		StormVeilCastle.addToListOfWorlds(world);
		BossRoom.addToListOfWorlds(world);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		world.run();
	}
}
