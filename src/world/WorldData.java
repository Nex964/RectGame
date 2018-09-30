package world;

import game.Main;
import objects.Platform;

public class WorldData
{
  private Main game;
  private int[] platformLocationData;
  
  public WorldData(Main game)
  {
    this.game = game;
    platformLocationData = new int[100];
  }
  
  public void updateData()
  {
    int row_num = 0;
    int col_num = 0;
    while (row_num < game.getCamera().getMap_size() / Main.BLOCK_WIDTH)
    {
      while (col_num < 8)
      {
        for (int i = 0; i < game.getPlatform().size(); i++)
        {
          Platform temp = (Platform)game.getPlatform().get(i);
          if ((row_num * 64 == temp.x) && (col_num * 64 == temp.y) && 
            (temp.type == 0)) {
            platformLocationData[(col_num + row_num * 7)] = 1;
          }
        }
        col_num++;
      }
      col_num = 0;
      row_num++;
    }
    System.out.println("Platform Data\n");
    for (int i = 0; i < platformLocationData.length; i++) {
      System.out.print(platformLocationData[i]);
    }
  }
  
  public void textureSetter()
  {
    for (int i = 0; i < game.getPlatform().size(); i++)
    {
      Platform temp = (Platform)game.getPlatform().get(i);
      if (temp.type == 0)
      {
        float currentPlatLocation = temp.y / 64 + temp.x / 64 * 7;
        try
        {
          if (platformLocationData[((int)(currentPlatLocation - 1.0F))] == 1) {
            ((Platform)game.getPlatform().get(i)).setTexture(assets.Assets.stone_tiles[6]);
          }
        }
        catch (Exception e)
        {
          System.out.println("Out of bounds");
        }
      }
    }
  }
  
  public int[] getTileMapping()
  {
    return platformLocationData;
  }
}
