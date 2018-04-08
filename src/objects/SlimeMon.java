package objects;

import Game.Camera;
import Game.Main;
import java.awt.Color;
import java.awt.Graphics;

public class SlimeMon
  extends Entity
{
  private static int WIDTH = 40;
  private static int HEIGHT = 40;
  private int far_left = 0;
  private int far_right = 100;
  private int moving = 0;
  private int a = 0;
  private static final long serialVersionUID = 8507401490263544538L;
  private Main game;
  
  public SlimeMon(Main game, int x, int y, int far_left, int far_right)
  {
    super(game, x, y, WIDTH, HEIGHT);
    this.game = game;
    this.far_left = (x - far_left);
    this.far_right = (x + far_right);
    texture = assets.Assets.crab_images[0];
  }
  
  public void tick(double delta)
  {
    fall();
    collision();
    deathChecker();
    if (moving == 0)
    {
      x += 1;
      if (x > far_right) {
        moving = 1;
      }
    }
    else
    {
      x -= 1;
      if (x < far_left) {
        moving = 0;
      }
    }
    if (cant_go_left) {
      moving = 1;
    }
    if (cant_go_right) {
      moving = 0;
    }
    if (a == 10) {
      texture = assets.Assets.crab_images[0];
    }
    if (a == 20)
    {
      texture = assets.Assets.crab_images[1];
      a = 0;
    }
    a += 1;
  }
  
  public void render(Graphics g)
  {
    g.setColor(Color.RED);
    g.drawImage(texture, x - game.getCamera().getXOffset(), y, WIDTH, HEIGHT, null);
  }
}
