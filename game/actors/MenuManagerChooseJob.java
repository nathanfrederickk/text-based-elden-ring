package game.actors;

import edu.monash.fit2099.engine.displays.Display;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * To choose the starting class of the Player.
 */
public class MenuManagerChooseJob {

    /**
     * Used for displaying the choices of the available classes Player
     * is able to choose from.
     */
    private static Display display = new Display();

    /**
     * Displaying the choices.
     * @return all the available choices.
     */
    public static int menuItem(){
        Scanner sel =new Scanner(System.in);
        display.println("Please Choose Job :");
        display.println("1) Samurai");
        display.println("2) Bandit");
        display.println("3) Wretch");
        display.println("4) Astrologer");
        try {
            int choice = sel.nextInt();
            display.println("Your choice: "+choice);
            return choice;
        }catch(InputMismatchException e){
            display.println("please input number");
        }
        return 0;
    }
}
