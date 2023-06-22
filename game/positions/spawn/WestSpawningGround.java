package game.positions.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public interface WestSpawningGround {
    /**
     * method to generate monster for monster that can spwned in west
     * @param location : location of spawn
     * @param ground : spawning ground
     */
    void generateMonsterInWest(Location location,Ground ground);
}
