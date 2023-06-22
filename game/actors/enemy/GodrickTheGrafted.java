package game.actors.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.EnemyType;
import game.item.rune.RuneManager;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;
import game.item.RemembranceOfTheGrafted;

/**
 * Implementation of the Godrick the Grafted boss
 */
public class GodrickTheGrafted extends Enemy{
    /**
     * To describe the characters of an enemy, such as AttackBehaviour, DespawnBehaviour, and
     * WanderBehaviour. An enemy is also hostile to both enemy and player.
     *
     */
    private int phase = 1;

    /**
     * Constructor
     */
    public GodrickTheGrafted() {
        super("Godrick The Grafted", 'Y', 6080, EnemyType.Boss, 0);
        this.removeBehaviour(2);
        this.addWeaponToInventory(new AxeOfGodrick());
        this.addItemToInventory(new RemembranceOfTheGrafted());
        RuneManager.getInstance().setActorRune(this, 1, 2);
    }

    /**
     * Represents what Godrick the Grafted can do
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return actions that Godrick the Grafted do every turn
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hitPoints <= this.maxHitPoints/2 & phase==1){
            this.removeWeaponFromInventory(this.getWeaponInventory().get(0));
            this.addWeaponToInventory(new GraftedDragon());
            this.phase = 2;
        }
        return super.playTurn(actions,lastAction,map,display);
    }

}
