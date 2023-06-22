package game.actors.enemy;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import game.behaviour.RespawnAble;
import game.enums.EnemyType;
import game.enums.Status;
import game.item.rune.RuneManager;
import game.weapons.Grossmesser;


/**
 * Represents the HeavySkeletalSwordsman enemy in the map. This enemy is
 * respawnAble, means that when it dies, it can turn into another actor.
 */
public class HeavySkeletalSwordsman extends Enemy implements RespawnAble {

    /**
     * The actor that the HeavySkeletalSwordsman will turn into
     * when it dies.
     */
    private final Actor respawnInto;

    /**
     * Constructor of the HeavySkeletalSwordsman class. It spawns with a Grossmesser, drops a rune when
     * killed, and turns into a PileOfBones if killed.
     */
    public HeavySkeletalSwordsman(){
        super("Heavy Skeletal Swordsman", 'q', 153, EnemyType.SKELETAL, 27);
        this.addWeaponToInventory(new Grossmesser());  // Spawns with grossmesser
        this.addCapability(Status.REVIVABLE);
        this.respawnInto = new PileOfBones(this);
        RuneManager.getInstance().setActorRune(this, 35, 892);
    }

    /**
     * If it is not conscious, then turn into PileOfBones, else call the parent class
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return whether it would respawn into PileOfBones or not.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            return this.respawnInto(this, this.respawnInto);
        }
        else{
            return super.playTurn(actions, lastAction, map, display);
        }
    }
}
