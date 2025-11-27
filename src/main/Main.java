package main;

import panels.GameController;
import panels.State;

public class Main {

   public static void main(String[] args) {

       GameController controller = new GameController();
       controller.switchStates(State.MAIN_MENU);

   }

}
