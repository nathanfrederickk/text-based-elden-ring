package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AlterActorAction;
import game.item.rune.RuneManager;

/**
 * Used if the Actor can transform from one actor to another if it dies.
 */
public interface RespawnAble {

    /**
     * The action of moving the inventory of Actor from to Actor to.
     * @param from The Actor that dies and wants to be replaced by Actor to
     * @param to What Actor from will transfer into
     * @return AlterActorAction
     */
    default Action respawnInto(Actor from, Actor to){

        for (WeaponItem weapon : from.getWeaponInventory()) {
            to.addWeaponToInventory(weapon);
        }
        for (WeaponItem weapon : to.getWeaponInventory()) {
            from.removeWeaponFromInventory(weapon);
        }
        for (Item item : from.getItemInventory()) {
            to.addItemToInventory(item);
        }
        for (Item item : to.getItemInventory()) {
            from.removeItemFromInventory(item);
        }

        RuneManager.getInstance().transferRune(from, to);

        return new AlterActorAction(to);
    }
}
