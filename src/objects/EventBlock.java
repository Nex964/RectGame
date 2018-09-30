package objects;

import game.Dailogs;
import game.Main;
import java.awt.Graphics;

public class EventBlock
  extends Object
{
  private static int width = 64;
  private static int height = 64;
  private String script = "";
  private boolean executed = false;
  private static final long serialVersionUID = -1567522284970198326L;
  private Main game;
  
  public EventBlock(Main game, int x, int y, String script)
  {
    super(x, y, width, height);
    this.game = game;
    this.script = script;
  }
  
  public void tick(double delta)
  {
    if (intersects(game.getPlayer()))
    {
      String[] cmd = script.split("_");
      if (cmd[0].equals("PRINT"))
      {
        System.out.println("Calling");
        Dailogs.speaker = cmd[1];
        Dailogs.dailog = cmd[2];
        Dailogs.toggle();
        executed = true;
      }
      if (cmd[0].equals("PLACE")) {
        game.getPlatform().add(new Platform(game, Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), 
          Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]), Integer.parseInt(cmd[5])));
      }
    }
    dispose();
  }
  
  public void render(Graphics g)
  {
    g.drawRect(x - game.getCamera().getXOffset(), y, width, height);
  }
  
  private void dispose()
  {
    if (executed) {
      game.getEventBlockList().remove(this);
    }
    if (script == "") {
      game.getEventBlockList().remove(this);
    }
  }
}
