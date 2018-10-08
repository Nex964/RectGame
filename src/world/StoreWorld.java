package world;

import game.Main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import objects.Entity;
import objects.PickUps;
import objects.Platform;
import objects.StaticObjects;

public class StoreWorld
{
  private File file;
  private BufferedWriter writer;
  private File folder;
  private Main game;
  private String home_dir;

  public StoreWorld(Main game)
  {
    this.game = game;
    home_dir = System.getProperty("user.home");
    folder = new File(home_dir + "/RectGameWorlds");
    file = new File(home_dir + "/RectGameWorlds" + "/World3.txt");
    if (!folder.exists())
    {
      folder.mkdirs();
      try
      {
        file.createNewFile();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void storePlatData()
  {
    try
    {
      writer = new BufferedWriter(new FileWriter(file));
      for (Platform platform : game.getPlatform())
      {
        writer.write(platform.x + "-");
        writer.write(platform.y + "-");
        writer.write(platform.width + "-");
        writer.write(platform.height + "-");
        System.out.println(platform.getType());
        System.out.println(platform.type);
        String temp = Integer.toString(platform.type);
        writer.write(temp);
        writer.newLine();
      }
      if (game.getPickUpsList().size() > 0)
      {
        writer.write("coins");
        writer.newLine();
      }
      for (PickUps coins : game.getPickUpsList())
      {
        writer.write(coins.x + "-");
        writer.write(coins.y + "-");
        writer.write(coins.width + "-");
        String temp = Integer.toString(coins.height);
        writer.write(temp);
        writer.newLine();
      }
      if (game.getMonsterList().size() > 0)
      {
        writer.write("enemys");
        writer.newLine();
      }
      for (Entity enemys : game.getMonsterList())
      {
        writer.write(enemys.x + "-");
        writer.write(enemys.y + "-");
        writer.write(enemys.width + "-");
        String temp = Integer.toString(enemys.height);
        writer.write(temp + "-");
        temp = Integer.toString(enemys.type);
        writer.write(temp);
        writer.newLine();
      }
      if (game.getStaticObjectList().size() > 0)
      {
        writer.write("static objects");
        writer.newLine();
      }
      for (StaticObjects staticObjects : game.getStaticObjectList())
      {
        writer.write(staticObjects.x + "-");
        writer.write(staticObjects.y + "-");
        writer.write(staticObjects.width + "-");
        String temp = Integer.toString(staticObjects.height);
        writer.write(temp + "-");
        temp = Integer.toString(staticObjects.getType());
        writer.write(temp);
        writer.newLine();
      }
      writer.close();

      System.out.println("World Saved Succesfully");
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
