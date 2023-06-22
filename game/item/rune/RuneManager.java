package game.item.rune;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.HashMap;

public class RuneManager {
    //attribute of hashmap that store runes according its actor
    private static HashMap<Actor, Rune> actorRune;
    //static variable
    private static RuneManager runeManager;

    //constructor of Rune Manager will do assigning actor rune ro hashmap
    private RuneManager() {
        actorRune = new HashMap<>();
    }


    /**
     * method get_Instance to make sure that in each game we will only have one
     * @return new runeManager only if we haven't create one else return already created runeManager
     */
    public static RuneManager getInstance() {
        if (runeManager == null) {
            runeManager = new RuneManager();
        }
        return runeManager;
    }


    /**
     * method to get rune value that hold by spesific actor
     * @param actor : actor as key to get actor's rune value
     * @return rune object that held by that actor
     */
    public Rune getRuneAmount(Actor actor) {
        return actorRune.get(actor);
    }

    /**
     * method to transfer actor from one actor to another actor
     * @param from : actor rune that will transfered from
     * @param to : target actor that will receive that rune
     */
    public void transferRune(Actor from, Actor to){
        Rune fromActor = actorRune.get(from);
        Rune toActor = actorRune.get(to);
        int val = fromActor.getRuneValue();
        toActor.addRune(val);
        fromActor.decreaseRune(val);
    }

    /**
     * method to set rune value of spesific actor
     * @param actor : actor that hold runes which will be set
     * @param lowerLimit : lowerlimit for runes object constructor
     * @param upperLimit :upper limit for rune object constructor
     */
    public void setActorRune(Actor actor, int lowerLimit, int upperLimit) {
        if (!actorRune.containsKey(actor)) {
            actorRune.put(actor, new Rune(lowerLimit, upperLimit));
        }
    }

    /**
     * remove Rune object of spesific actor
     * @param actor : actor that holf rune we will remove
     */
    public void removeActorRunes(Actor actor){
            actorRune.remove(actor);
        }

}


