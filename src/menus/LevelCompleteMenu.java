package menus;

import game.Main;
import assets.Assets;
import java.awt.Color;
import java.awt.Graphics;

import tools.FileHandler;
import world.World;

public class LevelCompleteMenu
  extends Menu
{
  private Main game;
  
  public LevelCompleteMenu(Main game)
  {
    this.game = game;
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.RED);
    g.drawImage(Assets.background[1],0, 0, 650, 450, null);
    g.drawImage(Assets.gui_retry, 100, 175, 100, 100, null);
    g.drawImage(Assets.gui_home, 300, 175, 100, 100, null);
    g.drawImage(Assets.gui_next, 500, 175, 100, 100, null);
  }
  
  public void tick()
  {
    Main.starter = false;
    String temp = FileHandler.read("options.txt");
    StringBuilder output = new StringBuilder();
    String[] line = temp.split("\n");
    for (int i = 0; i < line.length; i++)
    {
      if (line[i].startsWith("L"))
      {
        String[] data = line[i].split(" ");
        if (game.getLevelSelector().getCompleted_levels() >= World.LEVEL) {
          return;
        }
        data[1] = Integer.toString(World.LEVEL);
        line[i] = (data[0] + " " + data[1]);
        System.out.println(line[i]);
      }
      output.append(line[i] + "\n");
    }
    FileHandler.write("options.txt", output.toString());
  }
}
