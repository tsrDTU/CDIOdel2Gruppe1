package Del4;
import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
public class Main4 {
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
            Del_4.Die4 d1 = new Del_4.Die4();
            Del_4.Die4 d2 = new Del_4.Die4();
            String s = gui.getUserButtonPressed("Det er " + selectedPlayer.getName() + " der har tur", "Rul med terningerne");
            //uses balance value in GUI, since it displays on GUI at all times, and works like a score.
            selectedPlayer.setBalance(selectedPlayer.getBalance() + getSum(d1, d2));
            //show dice on screen
            gui.setDice(d1.getFaceValue(), d2.getFaceValue());
            //switch selected player
            selection = !selection;
        }
        int LastDie = 0;  // for the final display of the die
        while (player1.getBalance() >= 40 || player2.getBalance() >= 40) {
            if (selection) selectedPlayer = player1;
            else selectedPlayer = player2;
            Del_4.Die4 d1 = new Del_4.Die4();
            Del_4.Die4 d2 = new Del_4.Die4();
            String s = gui.getUserButtonPressed("Det er " + selectedPlayer.getName() + " der har tur", "Rul med terningerne");
            selectedPlayer.setBalance(selectedPlayer.getBalance() + getSum(d1, d2));
            gui.setDice(d1.getFaceValue(), d2.getFaceValue());

            // win with two equal dice
            if (selectedPlayer.getBalance() >= 40);
            {
                if (d1.getFaceValue() == d2.getFaceValue()) {
                    LastDie = d1.getFaceValue();
                    break;
                }
                //win with two equal dice
            }
            selection = !selection;
        }
        //when loop ends, show message
        gui.showMessage(selectedPlayer.getName() + " Har vundet med en score på: " + selectedPlayer.getBalance()
                + " og med sidste terninger på: " + LastDie);


    }



    private static int getSum(Del_4.Die4 d1, Del_4.Die4 d2){
        return d1.getFaceValue()+d2.getFaceValue();
    }
    private static boolean getEquals(Del_4.Die4 d1, Del_4.Die4 d2){
        return (d1.getFaceValue()==d2.getFaceValue());
    }
}
