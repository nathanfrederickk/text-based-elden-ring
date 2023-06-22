package game.positions.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.RandomNumberGenerator;
import game.ResetManager;
import game.actors.enemy.Enemy;
import game.enums.EnemyType;

public abstract class SpawningGround extends Ground implements WestSpawningGround, EastSpawningGround {
    private EnemyType enemyLocation;


    /**
     * Constructor.
     * A ground class with an ability to spawn enemies
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Spawn action to spawn an actor
     * @param location the location of where the actor might be spawned
     * @param actor the actor wished to be spawned by the ground
     * @param probability the chances of actor spawning
     */
    public void spawn(Location location, Enemy actor, int probability){
        int spawnChance = RandomNumberGenerator.getRandomInt(100);

        if(spawnChance <= probability){

            if(!location.containsAnActor()){

                location.addActor(actor);
                ResetManager.getInstance().registerResettable(actor);
            }
        }
    }
    /**
     * what will happen to enemy factory each turn
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        if (this.enemyLocation == null) {
            if (location.x() <= location.map().getXRange().max() / 2) {
                this.enemyLocation = EnemyType.WEST;
            }

            else {
                this.enemyLocation  = EnemyType.EAST;
            }

        } else if (this.enemyLocation == EnemyType.EAST) {
            this.generateMonsterInEast(location, this);

        } else {
            this.generateMonsterInWest(location, this);
        }
    }
    /**
     * add list of enemy  may spawned from this ground in either east or west
     */
    public abstract void resetArrayList();

}
