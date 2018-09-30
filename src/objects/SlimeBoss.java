package objects;

import game.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SlimeBoss
  extends Entity
{
  private static final long serialVersionUID = 1813768775992923922L;
  private int a = 0;
  private int time = 0;
  private int total_health;
  private Main game;
  private Random r;
  
  public SlimeBoss(Main game, int x, int y, int width, int height)
  {
    super(game, x, y, width, height);
    this.game = game;
    health = 100;
    total_health = health;
    r = new Random(System.currentTimeMillis());
  }
  
  public void tick(double delta)
  {
    fall();
    collision();
    deathChecker();
    if ((time == 60) && (r.nextInt(2) == 1)) {
      game.getMonsterList().add(new SlimeMon(game, x, y + height / 2, 800, 100));
    }
    if (time >= 60) {
      time = 0;
    }
    time += 1;
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
    g.drawImage(texture, x - game.getCamera().getXOffset(), y, width, height, null);
    g.setColor(Color.RED);
    g.drawRect(x - game.getCamera().getXOffset(), y - 10, width / total_health * total_health, 10);
    g.fillRect(x - game.getCamera().getXOffset(), y - 10, width / total_health * health, 10);
  }
}
