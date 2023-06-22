package game.positions.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemy.Dog;
import game.actors.enemy.Enemy;
import game.enums.EnemyType;

import java.util.ArrayList;
import java.util.List;

public class Cage extends SpawningGround {
    // list of enemy may spawned from east and west
    private List<Enemy> enemiesInWest;
    private List<Enemy> enemiesInEast;

    public Cage(){
        super('<');
        this.addCapability(EnemyType.stormveilCastle);
    }

    @Override
    public void generateMonsterInEast(Location location, Ground ground) {
        this.resetArrayList();

        for(Enemy enemy: this.enemiesInEast){
            super.spawn(location, enemy, enemy.getSpawnChance());
        }

    }

    @Override
    public void resetArrayList() {
        this.enemiesInEast = new ArrayList<>();
        this.enemiesInEast.add(new Dog());

        this.enemiesInWest = new ArrayList<>();
        this.enemiesInWest.add(new Dog());

    }

    @Override
    public void generateMonsterInWest(Location location, Ground ground) {
        this.resetArrayList();

        for(Enemy enemy: this.enemiesInWest){
            super.spawn(location, enemy, enemy.getSpawnChance());
        }
    }
}
