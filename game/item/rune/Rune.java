package game.item.rune;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import game.RandomNumberGenerator;
import game.actions.PickUpRuneAction;

public class Rune extends Item {
    //private integer value to represent rune value
    private int value;

    /**
     * constructor of Runes  Item, will pass some attribute(name,displayChar,portable) to its super class(Item)
     * set value using random number generator depend on inputted lower and higher bound
     */
    public Rune(int lowerBound, int higherBound) {
        super("Runes", '$', true);
        this.value = RandomNumberGenerator.getRandomInt(lowerBound, higherBound);
    }

    /**
     * method to increase rune value of this item
     * @param val : number of intended increase value
     */
    public void addRune(int val){
        this.value += val;
    }

    /**
     * method to get rune value
     * @return integer represent this rune value
     */
    public int getRuneValue(){
        return this.value;
    }

    /**
     * method to decrease rune value of this item
     * @param val : number of intended decrese value
     */
    public void decreaseRune(int val){
        this.value -= val;
    }




    @Override
    /**
     * Create and return an action to pick this Item up.
     * If this Item is not portable, returns null.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    public PickUpAction getPickUpAction(Actor actor){
        return new PickUpRuneAction(this);
    }
}
