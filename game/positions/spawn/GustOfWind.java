package game.positions.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemy.Enemy;
import game.actors.enemy.GiantDog;
import game.actors.enemy.LoneWolf;
import game.enums.EnemyType;

import java.util.ArrayList;
import java.util.List;

public class GustOfWind extends SpawningGround {
    private List<Enemy> enemiesInWest;
    private List<Enemy> enemiesInEast;
    /**
     * constructor of gustofwind ground, assign display char to its super class
     * and add capability canine as kind of enemy may spawned from this ground
     */
    public GustOfWind(){
        super('&');
        this.addCapability(EnemyType.CANINE);
    }

    /**
     * add list of enemy  may spawned from this ground in either east or west
     */
    public void resetArrayList(){
        this.enemiesInEast = new ArrayList<>();
        this.enemiesInEast.add(new GiantDog());

        this.enemiesInWest = new ArrayList<>();
        this.enemiesInWest.add(new LoneWolf());
    }
    @Override
    /**
     * method to generate monster for monster that can spwned in east
     * @param location : location of spawn
     * @param ground : spawning ground
     */
    public void generateMonsterInEast(Location location, Ground ground) {
        this.resetArrayList();

        for(Enemy enemy: this.enemiesInEast){
            super.spawn(location, enemy, enemy.getSpawnChance());
        }
    }

    @Override
    /**
     * method to generate monster for monster that can spwned in west
     * @param location : location of spawn
     * @param ground : spawning ground
     */
    public void generateMonsterInWest(Location location, Ground ground) {
        this.resetArrayList();

        for(Enemy enemy: this.enemiesInWest){
            super.spawn(location, enemy, enemy.getSpawnChance());
        }
    }
}
