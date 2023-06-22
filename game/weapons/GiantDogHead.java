package game.weapons;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attack.AreaAttackAction;

public class GiantDogHead extends WeaponItem {
    /**
     * Constructor of GiantDogHead item class , set attribute (name,displayChar,damage,damage,verb,and hit rate)
     * togle portability to false so this item won't be dropped upon wielder death
     */
    public GiantDogHead(){
        super("Giant Dog Head",'G',314,"slam",90);
        this.togglePortability();
    }

    @Override
    /**
     * Get an active skill action from the weapon. This should be used for weapon skills that do not involve a target actor
     * For instance, healing the holder of the weapon, switching current weapon's attack, e.g. from normal attack to fire attack
     * @param holder weapon holder
     * @return a special Action that can be performed by this weapon (heal the player, etc.)
     */
    public Action getSkill(Actor holder) {
        return new AreaAttackAction(holder, this);
    }

}
