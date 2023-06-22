package game.actors.enemy;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.item.rune.RuneManager;
import game.weapons.GiantCrabPincher;

/**
 * Represents the GiantCrab enemy in the map.
 */
public class GiantCrab extends Enemy {

    /**
     * Constructor of the GiantCrab class. It spawns with a GiantCrabPincher, drops a rune when
     * killed.
     */
    public GiantCrab(){
        super("Giant Crab", 'C', 407, EnemyType.CRAB, 2);
        RuneManager.getInstance().setActorRune(this, 318, 4961);
        this.addWeaponToInventory(new GiantCrabPincher());
    }

    /**
     * Intrinsic weapon of the GiantCrab
     * @return the IntrinsicWeapon of GiantCrab
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams",90);
    }
}
