package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.FancyMessage;
import game.ResetManager;
import game.actors.Player;
import game.enums.Status;
import game.enums.EnemyType;
import game.item.rune.Rune;
import game.item.rune.RuneManager;
import game.map.BossRoom;
import game.map.utils.MapSettings;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        if(target.hasCapability(EnemyType.Boss)){
            target.removeWeaponFromInventory(target.getWeaponInventory().get(0));
            MapSettings.addSiteOfLostGrace(map.locationOf(target).x(),map.locationOf(target).y(),"Godrick the Grafted", BossRoom.getInstance());
        }

        else if(target.hasCapability(Status.PLAYER)){
            // reset hp of Player
            target.increaseMaxHp(0);

            Rune actionsRune = RuneManager.getInstance().getRuneAmount(target);
            int runeValue = actionsRune.getRuneValue();
            Rune toDrop = new Rune(runeValue, runeValue);
            target.addItemToInventory(toDrop);
            // rune to drop in player's last location
            if(!Player.getLastLocation().containsAnActor() && map.locationOf(target) != Player.getLastLocation()){
                map.moveActor(target, Player.getLastLocation());
            }
            new DropItemAction(toDrop).execute(target, map);
            RuneManager.getInstance().getRuneAmount(target).decreaseRune(runeValue);

            // Move player to their respawn location, last visited site of lost grace
            map.moveActor(target, Player.getRespawnLocation());

            // Reset the game
            ResetManager.getInstance().resetPlayerDeath(map);

            return  "\n" + FancyMessage.YOU_DIED + "\nPlayer died dropping " + runeValue + " runes!";
        }

        else if (this.attacker.hasCapability(Status.PLAYER)) {

            ActionList dropActions = new ActionList();
            target.addItemToInventory(RuneManager.getInstance().getRuneAmount(target));
            int amountOfRunes = RuneManager.getInstance().getRuneAmount(target).getRuneValue();

            // drop all items
            for (Item item : target.getItemInventory()) {
                dropActions.add(item.getDropAction(target));
            }
            for (WeaponItem weapon : target.getWeaponInventory()) {
                dropActions.add(weapon.getDropAction(target));
            }
            for (Action drop : dropActions) {
                drop.execute(target, map);
            }

            // remove the actor in the rune manager
            RuneManager.getInstance().removeActorRunes(target);

            // remove actor
            map.removeActor(target);

            result += System.lineSeparator() + target + " is killed dropping " +amountOfRunes + " runes!";
            return result;
        }

        ActionList dropActions = new ActionList();

        // drop all items
        for (Item item : target.getItemInventory()) {
            dropActions.add(item.getDropAction(target));
        }
        for (WeaponItem weapon : target.getWeaponInventory()) {
            dropActions.add(weapon.getDropAction(target));
        }
        for (Action drop : dropActions) {
            drop.execute(target, map);
        }

        // remove the actor in the rune manager
        RuneManager.getInstance().removeActorRunes(target);


        map.removeActor(target);
        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**
     * Display when enemy is killed
     * @param actor The actor performing the action.
     * @ display when an enemy dies or destroyed
     */
    @Override
    public String menuDescription(Actor actor) {
        if(actor.hasCapability(EnemyType.BREAKABLE)){
            return actor + " is destroyed";
        }
        return actor + " is killed.";
    }
}
