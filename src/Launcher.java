import Game.Main;
import tools.DataBaseManager;

public class Launcher
{
  public static void main(String[] args){
      Main game = new Main("RectGame", 650, 450);
      game.start();
  }
}