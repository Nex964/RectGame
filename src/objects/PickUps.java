package objects;

import Game.Camera;
import Game.Main;
import assets.Assets;
import java.awt.Graphics;

public class PickUps
  extends Object
{
  private static int WIDTH = 32;
  private static int HEIGHT = 32;
  private int type = 0;
  private Main game;
  private static final long serialVersionUID = -2724129093834900041L;
  
  public PickUps(Main game, int x, int y)
  {
    super(x, y, WIDTH, HEIGHT);
    this.game = game;
  }
  
  public PickUps(Main game, int x, int y, int type)
  {
    super(x, y, WIDTH, HEIGHT);
    this.game = game;
    setType(type);
  }
  
  public void tick(double delta) {}
  
  public void render(Graphics g)
  {
    if (getType() == 0) {
      g.drawImage(Assets.coin_texture, x - game.getCamera().getXOffset(), y, WIDTH, HEIGHT, null);
    }
    if (getType() == 1) {
      g.drawImage(Assets.ammo_texture, x - game.getCamera().getXOffset(), y, WIDTH, HEIGHT, null);
    }
  }
  
  public int getType()
  {
    return type;
  }
  
  public void setType(int type)
  {
    this.type = type;
  }
}
