package game;

public class Camera
{
  private int xOffset;
  private int yOffset;
  private Main game;
  private int map_size;
  
  public Camera(Main game)
  {
    this.game = game;
    xOffset = game.getPlayer().x;
    yOffset = game.getPlayer().y;
  }
  
  public void tick()
  {
    if (game.getGameState() == 0)
    {
      xOffset = (game.getPlayer().x - 100);
      yOffset = (game.getPlayer().y - 100);
      if (xOffset > map_size - 650) {
        xOffset = (map_size - 650);
      }
    }
    if (xOffset < 0) {
      xOffset = 0;
    }
    if (yOffset < 0) {
      yOffset = 0;
    }
  }
  
  public void setxOffset(int xOffset)
  {
    this.xOffset = xOffset;
  }
  
  public void setyOffset(int yOffset)
  {
    this.yOffset = yOffset;
  }
  
  public int getXOffset()
  {
    return xOffset;
  }
  
  public int getYOffset()
  {
    return yOffset;
  }
  
  public int getMap_size()
  {
    return map_size;
  }
  
  public void setMap_size(int map_size)
  {
    this.map_size = map_size;
  }
}
