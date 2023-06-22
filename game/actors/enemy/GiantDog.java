package game.actors.enemy;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.item.rune.RuneManager;
import game.weapons.GiantDogHead;

/**
 * Represents the GiantDog enemy in the map.
 */
public class GiantDog extends Enemy {

    /**
     * Constructor of the GiantDog class. It spawns with a GiantDogHead, drops a rune when
     * killed.
     */
    public GiantDog(){
        super("Giant Dog", 'G', 693, EnemyType.CANINE, 4);
        this.addWeaponToInventory(new GiantDogHead());
        RuneManager.getInstance().setActorRune(this, 313, 1808);
    }

    /**
     * Intrinsic weapon of the GiantDog
     * @return the IntrinsicWeapon of the GiantDog
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams",90);
    }
}
