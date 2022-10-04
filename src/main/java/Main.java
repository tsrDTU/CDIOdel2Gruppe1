import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
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
        boolean gameEnd = false, lastMax=false;

        Die d1 = new Die();
        Die d2 = new Die();

        selectedPlayer = player1;  //???

        while (player1.getBalance() < 40 && player2.getBalance() < 40 && !gameEnd /*|| !getEquals(d1,d2)*/) {


            if (selection) selectedPlayer = player1;
            else selectedPlayer = player2;

            d1.die_normal();
            d2.die_normal();
//            d1.Die_test(1,6);
//            d2.Die_test(1,6);

            String s = gui.getUserButtonPressed("Det er " + selectedPlayer.getName() + " der har tur", "Rul med terningerne");

            //uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            //checks if score is equal to 2, ie if the player rolls 2 ones
            if (getSum(d1,d2)==12 && lastMax){
                gameEnd = true;
 //               continue;
            }

            lastMax = getSum(d1, d2) == 12;


            if(getSum(d1, d2)==2){
                selectedPlayer.setBalance(0);
            }
            else {
                selectedPlayer.setBalance(selectedPlayer.getBalance() + getSum(d1, d2));
            }
            //show dice on screen
            gui.setDice(d1.getFaceValue(), d2.getFaceValue());
            //switch selected player
           if (!getEquals(d1,d2)) {
               selection = !selection;
               lastMax = false;
           }
           else if (!(selectedPlayer.getBalance()>40)){
            gui.showMessage(selectedPlayer.getName() + " Du har fået en ekstra tur, fordi du har slået to ens: ") ; }
        }

        //when loop ends, show message
        gui.showMessage(selectedPlayer.getName() + " Har vundet med en score på: " + selectedPlayer.getBalance());


    }



    private static int getSum(Die d1,Die d2){
        return d1.getFaceValue()+d2.getFaceValue();
    }
    private static boolean getEquals(Die d1,Die d2){
        return (d1.getFaceValue()==d2.getFaceValue());
    }
}
