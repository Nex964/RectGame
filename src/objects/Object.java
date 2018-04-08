package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object
  extends Rectangle
{
  private static final long serialVersionUID = 1L;
  
  public Object(int x, int y, int width, int height)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  public abstract void tick(double paramDouble);
  
  public abstract void render(Graphics paramGraphics);
}
