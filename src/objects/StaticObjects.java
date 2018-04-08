package objects;

import Game.Camera;
import Game.Main;
import assets.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StaticObjects
  extends Object
{
  private static final long serialVersionUID = 5002601308786660339L;
  protected boolean collision_top;
  protected boolean collision_bottom;
  protected boolean collision_left;
  protected boolean collision_right;
  private int type = 0;
  private Main game;
  protected BufferedImage texture;
  
  public StaticObjects(Main game, int x, int y, int width, int height, int type)
  {
    super(x, y, width, height);
    this.game = game;
    setType(type);
    
    init();
  }
  
  private void init()
  {
    if (getType() == 1) {
      texture = Assets.environment_dark_tree;
    }
    if (getType() == 2) {
      texture = Assets.environment_light_tree;
    }
    if (getType() == 3) {
      texture = Assets.environment_bush;
    }
  }
  
  public void tick(double delta) {}
  
  public void render(Graphics g)
  {
    g.drawImage(texture, x - game.getCamera().getXOffset(), y, width, height, null);
  }
  
  public void dispose()
  {
    game.getStaticObjectList().remove(this);
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
