import gui_fields.*;
import gui_main.GUI;
import gui_fields.GUI_Car;
import gui_resources.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
        public static void main(String[] args) {

        GUI_Field[] fields = new GUI_Field[11];
            GUI_Car[] cars = new GUI_Car[2];

        cars[0] = new GUI_Car();
        cars[1] = new GUI_Car();


        fields[0] = new GUI_Street("2 Tower ", "+250", "", " ", Color.GREEN, Color.BLACK);
        fields[1] = new GUI_Street("3 Crater ", "-100", "", " ", Color.RED, Color.BLACK);
        fields[2] = new GUI_Street("4 Palace gates", "+100", "c", " ", Color.GREEN, Color.BLACK);
        fields[3] = new GUI_Street("5 Cold Desert", "-20", "E", " ", Color.RED, Color.BLACK);
        fields[4] = new GUI_Street("6 Walled city", "+180", "c", " ", Color.GREEN, Color.BLACK);
        fields[5] = new GUI_Street("7 Monastery ", "0", "E", " ", Color.YELLOW, Color.BLACK);
        fields[6] = new GUI_Street("8 Black cave ", "-70", "c", " ", Color.RED, Color.BLACK);
        fields[7] = new GUI_Street("9 Huts in the mountain", "+60", "E", " ", Color.GREEN, Color.BLACK);
        fields[8] = new GUI_Street("10 The Werewall", "-80 Ekstra tur", "c", " ", Color.GRAY, Color.BLACK);
        fields[9] = new GUI_Street("11 The pit ", "-50", "", " ", Color.RED, Color.BLACK);
        fields[10] = new GUI_Street("12 Goldmine ", "+650", "", " ", Color.GREEN, Color.BLACK);



//
        //creates board with black border intead of squares
        /*
            GUI_Field[] fields1 = new GUI_Field[40];
            for (int i = 0; i < fields.length; i++) {
                fields[i] = new GUI_Street(" ", " ", " ", " ", Color.BLACK, Color.BLACK);
            }
        */

            GUI gui = new GUI(fields, Color.WHITE);


            //Initializes players with name inputs
            GUI_Player player1 = new GUI_Player(gui.getUserString("Hvem er Spiller 1?"), 0);
            GUI_Player player2 = new GUI_Player(gui.getUserString("Hvem er Spiller 2?"), 0);
            gui.addPlayer(player1);
            gui.addPlayer(player2);

            // sets the initial car location
            fields[0].setCar(player1, true);
            fields[0].setCar(player2, true);

            //selects who starts the game by selection in gui
            boolean selection = gui.getUserLeftButtonPressed("Hvem starter spillet?", player1.getName(), player2.getName());

            //create a selected player that will point at active player
        GUI_Player selectedPlayer = null;
        boolean gameEnd = false, lastMax=false;

        Die d1 = new Die();
        Die d2 = new Die();

        selectedPlayer = player1;  //???



        while (player1.getBalance() < 40000 && player2.getBalance() < 40000 && !gameEnd ) {


            if (selection) selectedPlayer = player1;
            else selectedPlayer = player2;







            d1 = new Die();
            d2 = new Die();









//            d1.die_normal();
//            d2.die_normal();
//            d1.Die_test(1,6);
//            d2.Die_test(1,6);


            String s = gui.getUserButtonPressed("Det er " + selectedPlayer.getName() + " der har tur", "Rul med terningerne");
            //uses balance value in GUI, since it displays on GUI at all times, and works like a score.

            // Moving cars on the fields
            if(player1.getBalance()<3000 && player2.getBalance()<3000) {
        // for statement som checker hvilke biler der er på felterne, og som sætter biler ind i nye felter
           for (int i = 0;i<=11;i++) {
                if (fields[i].hasCar(player1) && fields[i].hasCar((player2))) {
                    fields[i].removeAllCars();                                      // slætter begge biler i et felt med 2 biler
                    if (selectedPlayer == player1){
                        fields[i].setCar(player2, true);                    //  sætter manglende bil ind i sin originale position
                        i=11;
                    }
                    else fields[i].setCar(player1, true);                   // sætter manglende bil ind i sin originale position
                    fields[getSum(d1,d2)-2].setCar(selectedPlayer, true); // sætter bil ind efter det nye terningekast
                    i=11;
                }
               else if (fields[i].hasCar(selectedPlayer)) {
                    fields[i].removeAllCars();                                  // slætter bil fra sin gamle position
                    fields[getSum(d1,d2)-2].setCar(selectedPlayer, true); // sætter bil ind efter det nye terningekast
                    i=11;
               }

           }
                selectedPlayer.setBalance(selectedPlayer.getBalance() + getSum(d1, d2)); // øger banken med de nye terningekast
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
        gui.showMessage(selectedPlayer.getName() + " Har vundet med en score på: " + selectedPlayer.getBalance()+2);

//
    }



    private static int getSum(Die d1,Die d2){
        return d1.getFaceValue()+d2.getFaceValue();
    }
    private static boolean getEquals(Die d1,Die d2){
        return (d1.getFaceValue()==d2.getFaceValue());
    }
}
