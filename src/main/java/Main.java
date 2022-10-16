import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;





public class Main {
    public static void main(String[] args) {
        Scanner indtasning = new Scanner(System.in);
        String string_in, language;
        int antal_kant, n;
        String[] dialog = new String[11];
        File fil;



        //Fields get defined:
        GUI_Street[] fields = new GUI_Street[11];
        fields[0] = new GUI_Street("2 Tower ", "+250", "","250", Color.GREEN, Color.BLACK);
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

        GUI gui = new GUI(fields, Color.WHITE);

        language=gui.getUserString("d: dansk e: english");

        try {
              if (language.equals("d")) {
                   fil = new File("C:/Users/tsr_0/IdeaProjects/CDIOdel2Gruppe1/src/Sprog/Dansk");
                  //           File file = new File("$INTELLIJ_ROOT$/CDIOdel2Gruppe1/src/Sprog/Dansk");
              }
              else
              {
                   fil = new File("C:/Users/tsr_0/IdeaProjects/CDIOdel2Gruppe1/src/Sprog/English");
                  //           File file = new File("$INTELLIJ_ROOT$/CDIOdel2Gruppe1/src/Sprog/English");
              }
            FileReader fil_leaser = new FileReader(fil);
            BufferedReader br = new BufferedReader(fil_leaser);

            n=0;
            while ((string_in = br.readLine()) != null) {

                dialog[n]=string_in;
                System.out.println(string_in);
                n++;
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        string_in=gui.getUserString(dialog[0]);
        System.out.println(string_in);
        if (string_in.length() > 0) {
            antal_kant = (int) string_in.charAt(0) - '0';
        }
        else antal_kant = 6;
        System.out.println(antal_kant);
        //Initializes players with name inputs
        GUI_Player player1 = new GUI_Player(gui.getUserString(dialog[1]), 1000);
        GUI_Player player2 = new GUI_Player(gui.getUserString(dialog[2]), 1000);
        gui.addPlayer(player1);
        gui.addPlayer(player2);

        //Sets the initial car location
        fields[0].setCar(player1, true);
        fields[0].setCar(player2, true);

        //Selects who starts the game by selection in gui
        boolean selection = gui.getUserLeftButtonPressed(dialog[3], player1.getName(), player2.getName());

        //Create a selected player that will point at active player
        GUI_Player selectedPlayer = null;
        boolean gameEnd = false, lastMax=false;

        Die d1 = new Die();
        Die d2 = new Die();

        if (antal_kant != 6)
        {
            d1.setNumberOfSides(antal_kant);
            d2.setNumberOfSides(antal_kant);
        }


        //Game loop
        while (player1.getBalance() < 3000 && player2.getBalance() < 3000 && !gameEnd ) {
            if (selection) selectedPlayer = player1;
            else selectedPlayer = player2;

            d1.dice_roll();
            d2.dice_roll();

            String s = gui.getUserButtonPressed(dialog[4]+" " + selectedPlayer.getName() + dialog[5], dialog[6]);
            //Uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            //Moving cars on the fields - and taking consequence of field
            if(player1.getBalance()<3000 && player2.getBalance()<3000) {
                //Check car locations and set new car locations
                for (int i = 0;i<=11;i++) {
                    //Checks for 2 players on the same field
                    if (fields[i].hasCar(player1) && fields[i].hasCar((player2))) {
                        fields[i].removeAllCars();
                        if (selectedPlayer == player1){
                            fields[i].setCar(player2, true);
                            i=11;
                        }
                        //Sets car back in its original place
                        else fields[i].setCar(player1, true);
                        fields[getSum(d1,d2)-2].setCar(selectedPlayer, true);
                        i=11;
                    }
                    //Push through with changing car location
                    else if (fields[i].hasCar(selectedPlayer)) {
                        fields[i].removeAllCars();
                        fields[getSum(d1,d2)-2].setCar(selectedPlayer, true);
                        i=11;
                    }

                }
                //Deposit/Withdraw money from fields on the board
                int konsekvens = Integer.parseInt(fields[getSum(d1,d2)-2].getRent());
                selectedPlayer.setBalance(selectedPlayer.getBalance() + konsekvens);
            }

            //Randomiser for dice positioning on the board
            int random_numx = ThreadLocalRandom.current().nextInt(4, 6);
            int random_numy = ThreadLocalRandom.current().nextInt(4, 6);
            int random_num1 = ThreadLocalRandom.current().nextInt(1, 2);
            int random_num2 = ThreadLocalRandom.current().nextInt(0, 2);
            //Show dice on screen
            gui.setDice(d1.getFaceValue(),random_numx,random_numy,d2.getFaceValue(),random_numx+random_num1,random_numy+random_num2);

            //Switch selected player
            if (!(Integer.parseInt(fields[getSum(d1,d2)-2].getRent())==-80))
                selection = !selection;

                //Extra tur
            else if (!(selectedPlayer.getBalance()>3000)){
                gui.showMessage(selectedPlayer.getName() + dialog[7]) ; }
        }

        //when loop ends, show message
        gui.showMessage(selectedPlayer.getName() + dialog[8] + selectedPlayer.getBalance());
    }

    private static int getSum(Die d1,Die d2){
        return d1.getFaceValue()+d2.getFaceValue();
    }
    private static boolean getEquals(Die d1,Die d2){
        return (d1.getFaceValue()==d2.getFaceValue());
    }
}
