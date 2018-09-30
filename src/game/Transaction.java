package game;

import java.awt.Color;
import java.awt.Graphics;

public class Transaction
{
  private Color c;
  private float r;
  private float g;
  private float b;
  private float alpha;
  private int waiter = 0;
  
  public Transaction(Main game)
  {
    r = (b = g = alpha = 1.0F);
  }
  
  public void setColor(float R, float G, float B)
  {
    r = R;
    b = B;
    g = G;
  }
  
  public void setColor(float R, float G, float B, float alpha)
  {
    r = R;
    b = B;
    g = G;
    this.alpha = alpha;
  }
  
  public void render(Graphics g)
  {
    c = new Color(r, b, this.g, alpha);
    g.setColor(c);
    g.fillRect(0, 0, 650, 450);
  }
  
  public void tick()
  {
    if (alpha >= 0.1F)
    {
      if (waiter == 1)
      {
        alpha -= 0.01F;
        waiter = 0;
      }
      waiter += 1;
    }
  }
  
  public void setAlphaTo(int alpha)
  {
    this.alpha = alpha;
  }
}
