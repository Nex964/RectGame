package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler
{
  private static BufferedReader reader;
  private static BufferedWriter writer;
  private static File file;
  private static String game_dir = System.getProperty("user.home") + "/RectGameWorlds/";
  
  public static String read(String path)
  {
    StringBuilder builder = new StringBuilder();
    file = new File(game_dir + path);
    try
    {
      reader = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = reader.readLine()) != null) {
        builder.append(line + "\n");
      }
      reader.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return builder.toString();
  }
  
  public static void write(String path, String data)
  {
    file = new File(game_dir + path);
    try
    {
      writer = new BufferedWriter(new FileWriter(file));
      writer.write(data);
      writer.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
