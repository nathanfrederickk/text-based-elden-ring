package game.actors.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Behaviour;
import game.enums.EnemyType;
import game.RandomNumberGenerator;
import game.enums.Status;
import game.*;
import game.behaviour.*;
import game.actions.attack.AttackAction;
import game.item.rune.RuneManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the enemy clas that is hostile to player
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * The behaviours of the enemy.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The enemy type of the enemy. Usually, if they have the same
     * spawn Ground, their enemyType will be equal.
     */
    private final EnemyType enemyType;

    /**
     * The spawn chance of each type of enemy
     */
    private final int spawnChance;

    /**
     * To describe the characters of an enemy, such as AttackBehaviour, DespawnBehaviour, and
     * WanderBehaviour. An enemy is also hostile to both enemy and player.
     * @param name name of the enemy
     * @param displayChar the display of the enemy in map
     * @param hitPoints the amount of hit points to kill the enemy
     * @param enemyType the enemy type
     */
    public Enemy (String name, char displayChar, int hitPoints, EnemyType enemyType, int spawnChance){
        super(name, displayChar, hitPoints);
        behaviours.put(2, new DeSpawnBehaviour());
        behaviours.put(3, new WanderBehaviour());

        this.enemyType = enemyType;
        this.spawnChance = spawnChance;
        this.addCapability(enemyType);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
    }

    /**
     * All the behaviours that the enemy could do on each turn of the match
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
     * The allowable actions the other actor can do to this enemy class
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return all the allowable actions the other actor can do to this enemy class
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        // AttackBehaviour is assigned here so that it could actually attack every round
        // if there is a Status.HOSTILE_TO_ENEMY actor around.
        behaviours.put(0, new AttackBehaviour());
        ActionList actions = new ActionList();

        // enemy have a 50% chance of using their special skill to attack
        int specialSkillProbability = RandomNumberGenerator.getRandomInt(100);
        boolean useSpecialSkill = specialSkillProbability <= 50;

        // if the other attacker is Status.HOSTILE_TO_ENEMY and not their own kind, attack is possible
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(this.enemyType)) {

            // if the attacker is a player
            if(otherActor.hasCapability(Status.PLAYER)){
                actions.add(new AttackAction(this, direction));

                // collect all the unique weapons of Player
                if(!otherActor.getWeaponInventory().isEmpty()) {

                    List<WeaponItem> uniqueListWeapon = new ArrayList<>();

                    for (WeaponItem s : otherActor.getWeaponInventory()) {
                        boolean unique = true;

                        for (WeaponItem a : uniqueListWeapon) {
                            if (s.toString().equals(a.toString())) {
                                unique = false;
                                break;
                            }
                        }
                        if (unique) {
                            uniqueListWeapon.add(s);
                        }
                    }

                    for (WeaponItem weaponItem : uniqueListWeapon){
                        actions.add(new AttackAction(this, direction, weaponItem));
                        actions.add(weaponItem.getSkill(this, direction));
                    }
                }

                // to follow the player
                this.behaviours.put(1, new FollowBehaviour(otherActor));

            }
            else{ // if the attacker is not a Player
                if (otherActor.getWeaponInventory().isEmpty()) {
                    actions.add(new AttackAction(this, direction));

                } else {
                    if(useSpecialSkill&& otherActor.getWeaponInventory().get(0).getSkill(otherActor) != null){
                        actions.add(otherActor.getWeaponInventory().get(0).getSkill(this));
                    }
                    else{
                        actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
                    }
                }
            }

        }
        return actions;
    }

    /**
     * Used in the reset when the Player rests in the Site Of Lost Grace.
     * When the Player rests, the enemy is removed from the map and their runes
     * are removed from the RuneManager class.
     * @param map current GameMap
     */
    @Override
    public void reset(GameMap map) {
        RuneManager.getInstance().removeActorRunes(this);
        ResetManager.getInstance().removeResettable(this);
        map.removeActor(this);
    }

    /**
     * To get the spawn chance of each respective enemy.
     * @return the spawn chance.
     */
    public int getSpawnChance() {
        return spawnChance;
    }

    public void resetBehaviour(){
        this.behaviours.clear();
    }

    public void addBehaviour(int key, Behaviour behaviour){
        this.behaviours.put(key, behaviour);
    }

    public void removeBehaviour(int key){
        this.behaviours.remove(key);
    }
}
