package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.Resettable;

public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable {
    //private attribute number of use remaining
    private int useCount;
    // private attribute to make sure we only have one crimson tears, since crimson tears only hold by player
    private static FlaskOfCrimsonTears FlaskOfCrimsonTearsInstance;

    /**
     * constructor of FlaskOfCrimsonTears  Item, will pass some attribute(name,displayChar,portable) to its super class(Item)
     * alse set number of use and register this item as resetable
     */
    private FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'f', false);
        this.useCount = 2;
        ResetManager.getInstance().registerResettable(this);
    }


    public static FlaskOfCrimsonTears getInstance(){
        if (FlaskOfCrimsonTearsInstance == null){
            FlaskOfCrimsonTearsInstance = new FlaskOfCrimsonTears();
        }
        return FlaskOfCrimsonTearsInstance;
    }

    /**
     * method for consuming this item, will heal user
     * @param actor : actor that will consume this item
     */
    public void consume(Actor actor){
        actor.heal(250);
        this.decreaseUse();
    }
    /**
     * method to decrease number of remaining times spesific item can be used
     */
    public void decreaseUse(){
        this.useCount -= 1;
    }

    /**
     * method to increase number of remaining times spesific item can be used
     */
    public void increaseUse(){
        this.useCount += 1;
    }

    /**
     * method to reset object
     * @param map : map that currently used
     */
    public void reset(GameMap map){
        this.useCount = 2;
    }

    /**
     * method to get number of remaining times spesific item can be used
     * @return number of remaining times spesific item can be used
     */
    public int getUseCount(){
        return this.useCount;
    }
}
