package game.enums;

/**
 * The EnemyType of the enemies
 */
public enum EnemyType {

    /**
     * Used by enemies spawned in the GustOfWind
     */
    CANINE,

    /**
     * Used by enemies spawned in the PuddleOfWater
     */
    CRAB,

    /**
     * Used by enemies spawned in the Graveyard
     */
    SKELETAL,

    /**
     * Used by the enemies that is destroyed and not killed
     * when it's HP is 0 or lower
     */
    BREAKABLE,

    /**
     * Used by enemies spawned in the East side of the GameMap
     */
    EAST,

    /**
     * Used by enemies spawned in the West side of the GameMap
     */
    WEST,
    INVADER,
    ALLY,
    stormveilCastle,
    Boss
}
