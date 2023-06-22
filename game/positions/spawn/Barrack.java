package game.positions.spawn;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemy.Enemy;
import game.actors.enemy.GodrickSoldier;
import game.enums.EnemyType;

import java.util.ArrayList;
import java.util.List;

public class Barrack extends SpawningGround {
    private List<Enemy> enemiesInWest;
    private List<Enemy> enemiesInEast;

    public Barrack(){
        super('B');
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
        this.enemiesInEast.add(new GodrickSoldier());

        this.enemiesInWest = new ArrayList<>();
        this.enemiesInWest.add(new GodrickSoldier());

    }

    @Override
    public void generateMonsterInWest(Location location, Ground ground) {
        this.resetArrayList();

        for(Enemy enemy: this.enemiesInWest){
            super.spawn(location, enemy, enemy.getSpawnChance());
        }
    }
}
