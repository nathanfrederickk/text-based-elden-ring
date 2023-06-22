package game.actors.enemy;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import game.enums.EnemyType;

import game.item.rune.RuneManager;


/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemy {

    /**
     * Creating the lone wolf enemy. Drop runes when killed by Player
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, EnemyType.CANINE, 33);
        RuneManager.getInstance().setActorRune(this, 55, 1470);
    }

    /**
     * Intrinsic weapon of Lone Wolf
     * @return the Intrinsic weapon of Lone Wolf
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
