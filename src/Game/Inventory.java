package Game;

import assets.Assets;
import java.awt.Color;
import java.awt.Graphics;

public class Inventory
{
  public static boolean show = false;
  private static int drawing_count = 0;
  
  public static void tick() {}
  
  public static void render(Graphics g)
  {
    if (show)
    {
      g.drawImage(Assets.inventory_background, 0, 0, 650, 450, null);
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++)
        {
          g.drawImage(Assets.inventory_outline, 40 + 143 * j, 28 + 99 * i, 143, 99, null);
          switch (drawing_count)
          {
          case 0: 
            g.drawImage(Assets.stone_tiles[0], 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 1: 
            g.drawImage(Assets.complete_tile, 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 2: 
            g.setColor(new Color(0.0F, 0.4F, 1.0F));
            g.fillRect(61 + 143 * j, 41 + 101 * i, 102, 71);
            break;
          case 3: 
            g.setColor(new Color(0.0F, 0.4F, 1.0F));
            g.fillRect(61 + 143 * j, 41 + 101 * i, 102, 71);
            break;
          case 4: 
            g.drawImage(Assets.spikes_img, 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 5: 
            g.drawImage(Assets.coin_images[0], 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 6: 
            g.drawImage(Assets.crab_images[0], 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 7: 
            g.drawImage(Assets.key_images[2], 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 8: 
            g.drawImage(Assets.environment_dark_tree, 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 9: 
            g.drawImage(Assets.environment_light_tree, 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 10: 
            g.drawImage(Assets.environment_bush, 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 11: 
            g.drawImage(Assets.blank, 61 + 143 * j, 41 + 101 * i, 102, 71, null);
            break;
          case 12: 
            g.drawImage(Assets.stone_tiles[0], 61 + 143 * j, 41 + 101 * i, 102, 71, null);
          }
          if (drawing_count < 12) {
            drawing_count += 1;
          }
        }
      }
      drawing_count = 0;
    }
  }
  
  public static void toggle()
  {
    if (show) {
      show = false;
    } else {
      show = true;
    }
  }
  
  public static boolean isVisible()
  {
    return show;
  }
}
