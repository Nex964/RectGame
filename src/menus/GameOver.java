package menus;

import game.Main;
import assets.Assets;
import java.awt.Color;
import java.awt.Graphics;

public class GameOver
  extends Menu
{
  private Main game;
  
  public GameOver(Main game)
  {
    this.game = game;
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.RED);
    g.setFont(Assets.game_over_font);
    g.drawString("game Over", 150, 100);
    g.drawImage(Assets.gui_retry, 100, 175, 100, 100, null);
    g.drawImage(Assets.gui_home, 300, 175, 100, 100, null);
  }
  
  public void tick()
  {
    Main.starter = false;
  }
}
