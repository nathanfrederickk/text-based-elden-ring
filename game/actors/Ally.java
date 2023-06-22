package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Behaviour;
import game.RandomNumberGenerator;
import game.ResetManager;
import game.Resettable;
import game.actions.attack.AttackAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.enums.EnemyType;
import game.enums.Job;
import game.enums.Status;
import game.item.rune.RuneManager;
import game.weapons.AstrologerStaff;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Ally in the map.
 */
public class Ally extends Actor implements Resettable {
    /**
     * Probability used to choose the Combat Role of Ally
     */
    private int probability;

    /**
     * Behaviour to control the actions of Ally
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Enemy type Ally
     */
    EnemyType enemyType;

    /**
     * Constructor for Ally. Gives a role to Ally at the start with its corresponding hp and weapon
     * Puts an initial Wander Behaviour to Ally
     */
    public Ally(){
        super("Ally", 'A', 0);
        this.probability = RandomNumberGenerator.getRandomInt(100);
        this.enemyType = EnemyType.ALLY;
        ResetManager.getInstance().registerDeathReset(this);
        if(probability<=25){
            this.hitPoints = 414; // BANDIT
            this.addWeaponToInventory(new GreatKnife());
            this.addCapability(Job.BANDIT);
        }
        else if(probability >=26 && probability <= 50){
            this.hitPoints = 455; // SAMURAI
            this.addWeaponToInventory(new Uchigatana());
            this.addCapability(Job.SAMURAI);
        }
        else if(probability >= 51 && probability <= 75){
            this.hitPoints = 414; // WRETCH
            this.addWeaponToInventory(new Club());
            this.addCapability(Job.WRETCH);
        }
        else if(probability >= 76 && probability <= 100){
            this.hitPoints = 396; // ASTROLOGER
            this.addWeaponToInventory(new AstrologerStaff());
            this.addCapability(Job.ASTROLOGER);
        }
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.behaviours.put(1, new WanderBehaviour());
    }

    /**
     * All the action that an Ally can execute in a turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action that the enemy would do on each turn of the game
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The allowable actions that another actor can perform to this class
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return all the allowable actions the other actor can do to this enemy class
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        // AttackBehaviour is assigned here so that it could actually attack every round
        // if there is a Status.HOSTILE_TO_ENEMY actor around.
        behaviours.put(0, new AttackBehaviour());
        ActionList actions = new ActionList();

        // enemy have a 50% chance of using their special skill to attack
        int specialSkillProbability = RandomNumberGenerator.getRandomInt(100);
        boolean useSpecialSkill = specialSkillProbability <= 50;

        // Only enemies that are also hostile to player can attack the Ally.
        // Ally will follow an enemy
        if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            this.behaviours.put(1, new FollowBehaviour(otherActor));
            if (otherActor.getWeaponInventory().isEmpty()) {
                actions.add(new AttackAction(this, direction));

            } else {
                // Check if another actor has a special skill
                if(useSpecialSkill && otherActor.getWeaponInventory().get(0).getSkill(otherActor) != null){
                    actions.add(otherActor.getWeaponInventory().get(0).getSkill(otherActor));
                }
                else{
                    actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
                }
            }
        }
        return actions;
    }

    /**
     * Invader and ally is reset when the player dies
     * @param map : map that currently used
     */
    public void reset(GameMap map) {
        RuneManager.getInstance().removeActorRunes(this);
        ResetManager.getInstance().removeDeathReset(this);
        map.removeActor(this);
    }
}
