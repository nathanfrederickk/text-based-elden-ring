package game.actors.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.EnemyType;
import game.behaviour.RespawnAble;
import game.item.rune.RuneManager;

/**
 * What SkeletalBandit and HeavySkeletalSwordsman will turn into
 * when it dies. If after three turns the PileOfBones is not hit,
 * it will turn back into SkeletalBandit or HeavySkeletalSwordsman.
 */
public class PileOfBones extends Enemy implements RespawnAble {

    /**
     * The number of turns needed for PileOfBones to turn back to its original
     * enemy class
     */
    private final int turnToRespawn = 3;

    /**
     * The current counter of PileOfBones.
     */
    private int counterTilRespawn = 0;

    /**
     * Which actor the PileOfBones will respawn into if
     * not destroyed within three turns.
     */
    private Actor respawnInto;

    /**
     * The constructor of PileOfBones. Will drop runes and weapon if destroyed by player.
     * Can be destroyed by other enemies as well.
     * @param actor What PileOfBones will respawn into
     */
    public PileOfBones(Actor actor) {
        super("Pile of Bones", 'X', 1, EnemyType.SKELETAL, 0);
        this.respawnInto = actor;
        RuneManager.getInstance().setActorRune(this, 0, 0);
    }

    /**
     * Every turn, it updates the counter until it reaches three so that it could be respawned if
     * not destroyed within three rounds.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action that PileOfBones can do each turn, which is DoNothingAction if not AlterActorAction.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.counterTilRespawn += 1;

        if(this.counterTilRespawn == this.turnToRespawn){
            return this.respawnInto(this, this.respawnInto);
        }
        return new DoNothingAction();
    }

    /**
     * Letting the user know that the PileOfBones has turned back
     * @return string to indicate that PileOfBones has turned back
     */
    public String menuDescription(){
        return this.respawnInto +  " has turned to Pile of Bones!";
    }

}
