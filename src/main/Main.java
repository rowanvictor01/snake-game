package main;

public class Main {

   public static void main(String[] args) {
       Window window = new Window();
       startGamePanel(window.getGamePanel());
   }

   public static void startGamePanel(GamePanel gp) {
       Thread gameThread = new Thread(gp);
       gameThread.start();
   }

}
