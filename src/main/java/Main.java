import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
     private static int  die1PrevValue = 0, die2PrevValue = 0, die1PrevPrevValue = 0, die2PrevPrevValue = 0;
    public static void main(String[] args) {

        //creates board with black border intead of squares
            GUI_Field[] fields = new GUI_Field[40];
            for (int i = 0; i < fields.length; i++) {
                fields[i] = new GUI_Street(" ", " ", " ", " ", Color.BLACK, Color.BLACK);

            }
            GUI gui = new GUI(fields, Color.WHITE);

            //Initializes players with name inputs
            GUI_Player player1 = new GUI_Player(gui.getUserString("Hvem er Spiller 1?"), 0);
            GUI_Player player2 = new GUI_Player(gui.getUserString("Hvem er Spiller 2?"), 0);
            gui.addPlayer(player1);
            gui.addPlayer(player2);

            //selects who starts the game by selection in gui
            boolean selection = gui.getUserLeftButtonPressed("Hvem starter spillet?", player1.getName(), player2.getName());

            //create a selected player that will point at active player
        GUI_Player selectedPlayer = null;
        while (player1.getBalance() < 40 && player2.getBalance() < 40) {


            if (selection) selectedPlayer = player1;
            else selectedPlayer = player2;


          //  Die d1 = new Die(); // Normalt slag
          //  Die d2 = new Die(); // Normalt slag

             Die d1 = new Die(6,6); // Test. der slås kun 6ere. Kommenter ud efter test
             Die d2 = new Die(6,6); // Test. der slås kun 6ere. Kommenter ud efter test



            String s = gui.getUserButtonPressed("Det er " + selectedPlayer.getName() + " der har tur", "Rul med terningerne");

            //uses balance value in GUI, since it displays on GUI at all times, and works like a score.
            selectedPlayer.setBalance(selectedPlayer.getBalance() + getSum(d1,d2));
            //show dice on screen
            gui.setDice(d1.getFaceValue(), d2.getFaceValue());
            //switch selected player
            selection = !selection;
        }
//when loop ends, show message
        gui.showMessage(selectedPlayer.getName() + " Har vundet med en score på: " + selectedPlayer.getBalance());


    }



    private static int getSum(Die d1,Die d2){
        int die1ActValue = 0, die2ActValue = 0, actSum = 0;

        die1ActValue = d1.getFaceValue();
        die2ActValue = d2.getFaceValue();

// die1PrevValue og die2PrevValue er den anden spillers foregående slag
// die1PrevPrevValue og die2PrevPrevValue er denne spillers foregående slag

        if (die1ActValue == 6 && die2ActValue  == 6 &&  die1PrevPrevValue == 6 &&  die2PrevPrevValue == 6)
            actSum = 40; //Spillet er slut når en spiller har nået 40 point
        else actSum = d1.getFaceValue()+d2.getFaceValue();

        die1PrevPrevValue = die1PrevValue;
        die2PrevPrevValue = die2PrevValue;

        die1PrevValue = die1ActValue;
        die2PrevValue = die2ActValue;
        return actSum;
    }
    private static boolean getEquals(Die d1,Die d2){
        return (d1.getFaceValue()==d2.getFaceValue());
    }
}
