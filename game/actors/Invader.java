package game.actors;

import edu.monash.fit2099.engine.positions.GameMap;
import game.RandomNumberGenerator;
import game.ResetManager;
import game.Resettable;
import game.actors.enemy.Enemy;
import game.behaviour.WanderBehaviour;
import game.enums.EnemyType;
import game.enums.Job;
import game.item.rune.RuneManager;
import game.weapons.AstrologerStaff;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

/**
 * Represents the Invader in the map.
 */
public class Invader extends Enemy implements Resettable {

    /**
     * Constructor for Invader. Gives a role to Invader at the start with its corresponding hp and weapon
     * Puts an initial Wander Behaviour to Invader. Drop runes when killed.
     * Invader has a different behaviour than the parent class, so new behaviour is added here
     */
    public Invader(){
        super("Invader" , 'ඞ', 0, EnemyType.INVADER, 0);
        RuneManager.getInstance().setActorRune(this, 1358, 5578);
        ResetManager.getInstance().registerDeathReset(this);
        int probability = RandomNumberGenerator.getRandomInt(100);
        if(probability<=25){
            this.hitPoints = 455; // SAMURAI
            this.maxHitPoints = 455;
            this.addWeaponToInventory(new Uchigatana());
            this.addCapability(Job.SAMURAI);
        }
        else if(probability>=26 && probability<=50){
            this.hitPoints = 414; // BANDIT
            this.maxHitPoints = 414;
            this.addWeaponToInventory(new GreatKnife());
            this.addCapability(Job.BANDIT);
        }
        else if (probability>=51 && probability<=75){
            this.hitPoints = 414; // WRETCH
            this.maxHitPoints = 414;
            this.addWeaponToInventory(new Club());
            this.addCapability(Job.WRETCH);
        }
        else if (probability>= 76 && probability <= 100){
            this.hitPoints = 396;
            this.maxHitPoints = 396;
            this.addWeaponToInventory(new AstrologerStaff());
            this.addCapability(Job.ASTROLOGER);
        }
        this.resetBehaviour();
        this.addBehaviour(2, new WanderBehaviour());
    }

    /**
     * Invader and ally is reset when the player dies
     * @param map : map that currently used
     */
    @Override
    public void reset(GameMap map) {
        RuneManager.getInstance().removeActorRunes(this);
        ResetManager.getInstance().removeDeathReset(this);
        map.removeActor(this);
    }
}
