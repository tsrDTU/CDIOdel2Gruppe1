import gui_fields.GUI_Board;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

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



}
