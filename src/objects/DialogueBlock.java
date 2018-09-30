package objects;

import game.Dailogs;
import game.Main;
import java.awt.Color;
import java.awt.Graphics;

public class DialogueBlock
  extends Object
{
  private Main game;
  private String dialogue;
  private String title;
  private static int height = 64;
  private static int width = 64;
  private static final long serialVersionUID = 7578058771993340220L;
  
  public DialogueBlock(Main game, int x, int y, String title, String dialogue)
  {
    super(x, y, width, height);
    this.title = title;
    this.dialogue = dialogue;
    this.game = game;
  }
  
  public void tick(double delta)
  {
    if (intersects(game.getPlayer()))
    {
      Dailogs.speaker = title;
      Dailogs.dailog = dialogue;
      Dailogs.toggle();
      game.getDialogueBlockList().remove(this);
    }
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.RED);
    g.drawRect(x - game.getCamera().getXOffset(), y, width, height);
  }
}
