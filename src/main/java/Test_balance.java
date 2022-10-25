import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

public class Test_balance {
    public static void main(String[] args) {

        int minimumBalance = 10000, i;

        String[] dialog = new String[11];
        //Fields get defined:

        // initializeDialog(dialog, "d"); // Initialize the game dialog

    GUI_Street[] fields = new GUI_Street[11];
    fields[0] = new GUI_Street("2 Tower ", "+250", "", "250",Color.GREEN, Color.BLACK);
    fields[1] = new GUI_Street("3 Crater ", "-100", "", "-100", Color.RED, Color.BLACK);
    fields[2] = new GUI_Street("4 Palace gates", "+100", "c", "+100", Color.GREEN, Color.BLACK);
    fields[3] = new GUI_Street("5 Cold Desert", "-20", "E", "-20", Color.RED, Color.BLACK);
    fields[4] = new GUI_Street("6 Walled city", "+180", "c", "+180", Color.GREEN, Color.BLACK);
    fields[5] = new GUI_Street("7 Monastery ", "0", "E", "0", Color.YELLOW, Color.BLACK);
    fields[6] = new GUI_Street("8 Black cave ", "-70", "c", "-70", Color.RED, Color.BLACK);
    fields[7] = new GUI_Street("9 Huts in the mountain", "+60", "E", "+60", Color.GREEN, Color.BLACK);
    fields[8] = new GUI_Street("10 The Werewall", "-80 Ekstra tur", "c", "-80", Color.GRAY, Color.BLACK);
    fields[9] = new GUI_Street("11 The pit ", "-50", "", "-50", Color.RED, Color.BLACK);
    fields[10] = new GUI_Street("12 Goldmine ", "+650", "", "+650", Color.GREEN, Color.BLACK);

    GUI gui = new GUI(fields, Color.WHITE); //setup GUI
     for (i=0;i<1000;i++) {
         //Initializes players with name inputs
         GUI_Player player1 = new GUI_Player("Test spiller", 1000);

         //Create the dices. Default 6 sides
         Die d1 = new Die();
         Die d2 = new Die();

         GUI_Player selectedPlayer = player1;
         while (player1.getBalance() < 3000) {
             //roll the dices
             d1.dice_roll();
             d2.dice_roll();

             //Deposit/Withdraw money from fields on the board
             int konsekvens = Integer.parseInt(fields[getSum(d1, d2) - 2].getRent());
             selectedPlayer.setBalance(selectedPlayer.getBalance() + konsekvens);

//        System.out.println(selectedPlayer.getBalance());
             if (minimumBalance > selectedPlayer.getBalance()) minimumBalance = selectedPlayer.getBalance();
         }

     }
        System.out.println(minimumBalance);
 //    System.exit();
    }


    private static int getSum(Die d1, Die d2)
    {
        return d1.getFaceValue() + d2.getFaceValue();
    }
}
