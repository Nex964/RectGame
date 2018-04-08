package world;

import Game.Camera;
import Game.Dailogs;
import Game.Main;
import assets.Assets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import objects.EventBlock;
import objects.Key;
import objects.PickUps;
import objects.Platform;
import objects.SlimeMon;
import objects.StaticObjects;

public class World
{
  private Main game;
  public static int LEVEL;
  private String[] datas;
  private String current_item = "platform";
  private int map_size = 0;
  private WorldData worldData;
  
  public World(Main game)
  {
    this.game = game;
    worldData = new WorldData(game);
    selectLevel();
  }
  
  public void selectLevel()
  {
    game.reset();
    if (LEVEL == 1)
    {
      Assets.Level_name = "Tutorial";
      Assets.init();
      loadLevelOne();
    }
    if (LEVEL == 2) {
      Assets.Level_name = "Moon_Null";
    }
    if (LEVEL == 3) {
      Assets.Level_name = "Mech_World";
    }
    Assets.init();
    if (LEVEL > 1) {
      loadLevelFromFile();
    }
    worldData.updateData();
    worldData.textureSetter();
  }
  
  private void loadLevelOne()
  {
    game.getPlayer().x = 100;
    game.getPlayer().y = 100;
    game.getCamera().setMap_size(1870);
    game.getPlatform().add(new Platform(game, 801, 410, 40, 40, 1, 1));
    
    game.getPickUpsList().add(new PickUps(game, 360, 200));
    game.getPickUpsList().add(new PickUps(game, 540, 350, 1));
    
    game.getEventBlockList().add(new EventBlock(game, 100, 390, "PRINT_Controls_Use A,D to move\nand W to jump."));
    game.getEventBlockList().add(new EventBlock(game, 310, 330, "PRINT_Tip_Collect Coins to increase score."));
    game.getEventBlockList().add(new EventBlock(game, 540, 350, "PRINT_Tip_When you have ammos.\nYou can use \"SPACE\" key to shoot."));
    game.getEventBlockList().add(new EventBlock(game, 1240, 90, "PRINT_Tip_Complete the level by touching green Blocks"));
    
    game.getMonsterList().add(new SlimeMon(game, 600, 300, 10, 100));
    
    game.getPlatform().add(new Platform(game, 0, 0, 3000, 20));
    game.getPlatform().add(new Platform(game, 300, 350, 100, 20));
    game.getPlatform().add(new Platform(game, 0, 0, 20, 450));
    game.getPlatform().add(new Platform(game, 20, 410, 390, 40));
    game.getPlatform().add(new Platform(game, 200, 390, 40, 20, 3));
    game.getPlatform().add(new Platform(game, 500, 410, 300, 40));
    game.getPlatform().add(new Platform(game, 800, 0, 330, 270));
    game.getPlatform().add(new Platform(game, 1220, 110, 150, 340));
    game.getPlatform().add(new Platform(game, 1370, 110, 30, 20));
    game.getPlatform().add(new Platform(game, 1470, 210, 30, 240));
    game.getPlatform().add(new Platform(game, 1570, 210, 30, 240));
    game.getPlatform().add(new Platform(game, 1670, 210, 30, 240));
    game.getPlatform().add(new Platform(game, 1770, 160, 60, 30));
    game.getPlatform().add(new Platform(game, 1790, 190, 20, 40));
    game.getPlatform().add(new Platform(game, 1790, 230, 100, 30));
    game.getPlatform().add(new Platform(game, 1830, 160, 40, 30, 2));
    game.getPlatform().add(new Platform(game, 1870, 0, 20, 450));
    Dailogs.toggle();
  }
  
  private void loadLevelFromFile()
  {
    worldLoader("World" + LEVEL + ".txt");
    game.getPlayer().x = 64;
    game.getPlayer().y = 64;
  }
  
  private void worldLoader(String path)
  {
    File file = new File(System.getProperty("user.home") + "/RectGameWorlds/" + path);
    if (!file.exists()) {
      System.out.println("World Data Not Available Please create one in Level Designer");
    }
    try
    {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      
      String string_data = "";
      current_item = "platform";
      String line = "";
      while ((line = reader.readLine()) != null){
        if (line.equals("coins"))
        {
          current_item = line;
        }
        else if (line.equals("enemys"))
        {
          current_item = line;
        }
        else if (line.equals("static objects"))
        {
          current_item = line;
        }
        else if (line.equals("event"))
        {
          current_item = line;
        }
        else
        {
          datas = line.split("'");
          int[] data = new int[5];
          for (int i = 0; i < datas.length; i++) {
            if ((current_item.equals("event")) && (i == 2)) {
              string_data = datas[i];
            } else {
              data[i] = Integer.parseInt(datas[i]);
            }
          }
          if (current_item.equals("platform"))
          {
            game.getPlatform().add(new Platform(game, data[0], data[1], data[2], data[3], data[4]));
            if (map_size < data[0] + data[2]) {
              map_size = (data[0] + data[2]);
            }
          }
          if (current_item.equals("coins")) {
            game.getPickUpsList().add(new PickUps(game, data[0], data[1]));
          }
          if ((current_item.equals("enemys")) && 
            (data[4] == 0)) {
            game.getMonsterList().add(new SlimeMon(game, data[0], data[1], data[2], data[3]));
          }
          if (current_item.equals("static objects")) {
            if (data[4] == 0) {
              game.getStaticObjectList().add(new Key(game, data[0], data[1], data[2], data[3]));
            } else {
              game.getStaticObjectList().add(new StaticObjects(game, data[0], data[1], data[2], data[3], data[4]));
            }
          }
          if (current_item.equals("event")) {
            game.getEventBlockList().add(new EventBlock(game, data[0], data[1], string_data));
          }
        }
      }
      game.getCamera().setMap_size(map_size);
      reader.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println("Cannot read world data");
    }
  }
  
  public WorldData getWorldData()
  {
    return worldData;
  }
}
