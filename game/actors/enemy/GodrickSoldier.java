package game.actors.enemy;

import game.enums.EnemyType;
import game.item.rune.RuneManager;
import game.weapons.HeavyCrossbows;

/**
 * Godrick Soldier in StormVeil castle
 */
public class GodrickSoldier extends Enemy{

    /**
     * Constructor
     */
    public GodrickSoldier(){
        super("Godrick Soldier",'p',198, EnemyType.stormveilCastle,45);
        this.addWeaponToInventory(new HeavyCrossbows());
        RuneManager.getInstance().setActorRune(this, 38, 70);
    }
}
