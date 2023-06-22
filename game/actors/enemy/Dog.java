package game.actors.enemy;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.item.rune.RuneManager;

/**
 * Dog class
 */
public class Dog extends Enemy{

    /**
     * Constructor
     */
    public Dog(){
        super("Dog",'a',104, EnemyType.stormveilCastle,37);
        RuneManager.getInstance().setActorRune(this, 52, 1390);
    }

    /**
     * The intrinsic weapon of dog
     * @return
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

}
