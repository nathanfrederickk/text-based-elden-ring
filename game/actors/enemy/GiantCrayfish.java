package game.actors.enemy;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.item.rune.RuneManager;
import game.weapons.GiantCrayFishPincher;

/**
 * Represents the GiantCrayFish enemy in the map.
 */
public class GiantCrayfish extends Enemy {

    /**
     * Constructor of the GiantCrayFish class. It spawns with a GiantCraysFishPincher, drops a rune when
     * killed.
     */
    public GiantCrayfish(){
        super("Giant Crayfish", 'R', 4803, EnemyType.CRAB, 1);
        this.addWeaponToInventory(new GiantCrayFishPincher());
        RuneManager.getInstance().setActorRune(this, 500, 2374);
    }

    /**
     * Intrinsic weapon of the GiantCrayFish
     * @return the IntrinsicWeapon of GiantCrayFish
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams",100);
    }
}
