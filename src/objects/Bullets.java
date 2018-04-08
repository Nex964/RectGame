package objects;

import Game.Camera;
import Game.Main;
import java.awt.Graphics;

public class Bullets
  extends Entity
{
  private Main game;
  private int direction = 0;
  private int damage = 0;
  private static final long serialVersionUID = 463242910959148581L;
  
  public Bullets(Main game, int x, int y, int width, int height, int damage)
  {
    super(game, x, y, width, height);
    this.game = game;
    this.damage = damage;
    init();
  }
  
  public void init()
  {
    if (game.getPlayer().left) {
      direction = 1;
    } else {
      direction = 0;
    }
  }
  
  public void tick(double delta)
  {
    if (direction == 0) {
      x += 6;
    } else {
      x -= 6;
    }
  }
  
  public void render(Graphics g)
  {
    g.drawImage(assets.Assets.ammo_images[0], x - game.getCamera().getXOffset() - 3, y - 3, width + 5, height + 5, null);
  }
  
  public int getDamage()
  {
    return damage;
  }
}
