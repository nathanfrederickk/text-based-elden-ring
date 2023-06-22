package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import game.RandomNumberGenerator;
import game.Resettable;
import game.enums.ItemType;
import game.enums.Job;
import game.enums.Status;
import game.actions.attack.AttackAction;
import game.actions.ConsumeAction;
import game.item.Consumable;
import game.item.FlaskOfCrimsonTears;
import game.item.GoldenSeeds;
import game.item.RemembranceOfTheGrafted;
import game.item.rune.RuneManager;
import game.weapons.*;

import java.util.List;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Frederick Thunardi
 * @author Jerry Hans Frenatta
 * @author Dandi Setiawan
 */
public class Player extends Actor implements Resettable {

	/**
	 * The options that the user can choose for the Player to do
	 */
	private final Menu menu = new Menu();

	/**
	 * The FlaskOfCrimsonTears the player has
	 */
	private FlaskOfCrimsonTears flaskOfCrimsonTears;

	/**
	 * The respawn location of the Player. Hardcoded in the Application class.
	 */
	private static Location respawnLocation;

	/**
	 * The last location of the Player before dying
	 */
	private static Location lastLocation;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hit points
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.PLAYER);
		this.flaskOfCrimsonTears = FlaskOfCrimsonTears.getInstance();
		RuneManager.getInstance().setActorRune(this, 0, 0);
	}

	/**
	 * So Player can choose which class to choose before playing
	 * the game.
	 * @param name The name of Player, usually Tarnished.
	 * @param displayChar The display char of the Player, usually '@'
	 * @return return the Player class chosen
	 */
	public static Player getInstance(String name, char displayChar){
		int selection;
		do {
			selection = MenuManagerChooseJob.menuItem();
			switch(selection){
				case 1:
					Player samurai = new Player(name,displayChar,455);
					samurai.addWeaponToInventory(new Uchigatana());
					samurai.addCapability(Job.SAMURAI);
					return samurai;
				case 2:
					Player Bandit = new Player(name,displayChar,414);
					Bandit.addWeaponToInventory(new GreatKnife());
					Bandit.addCapability(Job.BANDIT);
					return Bandit;
				case 3:
					Player Wretch = new Player(name,displayChar,414);
					Wretch.addWeaponToInventory(new Club());
					Wretch.addCapability(Job.WRETCH);
					return Wretch;
				case 4:
					Player Astrologer = new Player(name, displayChar, 396);
					Astrologer.addWeaponToInventory(new AstrologerStaff());
					Astrologer.addCapability(Job.ASTROLOGER);
					return Astrologer;
			}
		}while(selection < 5);
		return null;
	}

	/**
	 * The actions that the player can do for each turn.
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return The actions that the player can do for each turn.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Use flask of crimson tears if it is available to use
		if(this.flaskOfCrimsonTears.getUseCount()>0) {
			actions.add(new ConsumeAction(this.flaskOfCrimsonTears));
		}

		// save the last location of Player
		this.setLastLocation(map.locationOf(this));

		// return/print the console menu
		display.println(this.name +  " (" + this.hitPoints + "/" + this.maxHitPoints + "), has " + RuneManager.getInstance().getRuneAmount(this).getRuneValue() + " runes");
		return menu.showMenu(this, actions, display);
	}

	/**
	 * The allowableActions of other actors when around the Player. If otherActor is
	 * an enemy, it can attack the Player.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return the ActionList of what the otherActor can do to Player.
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		int specialSkillProbability = RandomNumberGenerator.getRandomInt(100);
		boolean useSpecialSkill = specialSkillProbability <= 50;

		// If otherActor is hostile to player, it can attack using
		// special skill or just normal attack
		if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
			if (otherActor.getWeaponInventory().isEmpty()) {
				actions.add(new AttackAction(this, direction));

			} else {
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
	 * Intrinsic weapon of Player
	 * @return the Intrinsic weapon of Player
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punch", 50);
	}

	/**
	 * Reset when the player enters the SiteOfLostGrace
	 * @param map the current GameMap
	 */
	@Override
	public void reset(GameMap map) {
		this.increaseMaxHp(0);
		respawnLocation = map.locationOf(this);
	}

	/**
	 * To get the respawn location of player, which is the
	 * last SiteOfGrace visited.
	 */
	public static Location getRespawnLocation() {
		return respawnLocation;
	}

	/**
	 * To set the respawn location of player, which is the
	 * last SiteOfGrace visited.
	 * @param respawnLocation respawn location of Player
	 */
	public void setRespawnLocation(Location respawnLocation) {
		this.respawnLocation = respawnLocation;
	}

	/**
	 * To get the last location of Player
	 * @return last location of Player
	 */
	public static Location getLastLocation(){
		return lastLocation;
	}

	/**
	 * To set the last location of Player
	 */
	public void setLastLocation(Location location){
		this.lastLocation = location;
	}
}
