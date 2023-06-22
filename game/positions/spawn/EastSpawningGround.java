package game.positions.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public interface EastSpawningGround {
    /**
     * method to generate monster for monster that can spwned in east
     * @param location : location of spawn
     * @param ground : spawning ground
     */
    void generateMonsterInEast(Location location,Ground ground);
}
