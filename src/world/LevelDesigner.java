package world;

import Game.Camera;
import Game.Main;
import display.Display;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;
import objects.Bullets;
import objects.DialogueBlock;
import objects.Entity;
import objects.EventBlock;
import objects.Key;
import objects.PickUps;
import objects.Platform;
import objects.SlimeMon;
import objects.StaticObjects;

public class LevelDesigner
{
  private Main game;
  private int temp_x;
  private int temp_y;
  private int temp_width;
  private int temp_height;
  private int num_plats;
  private int num_coins;
  private int num_enemys = 0;
  public static int TYPE = 0;
  public static boolean FIRST_TIME = true;
  private boolean drawing = false;
  private boolean drawing_grid = true;
  private int mouse_x;
  public String event_cmd = "";
  private String start_up_message = "Press E to select item to add in gamePress L to load saved world\nPress O to save world\nPress up key to test current down world\nand if the red rectangle is filled with red color\nwhile drawing then undo it with U key or it wont work";
  
  public LevelDesigner(Main game)
  {
    this.game = game;
    game.reset();
  }
  
  public void setPosition(int x, int y)
  {
    temp_x = (x - (x + game.getCamera().getXOffset()) % Main.BLOCK_WIDTH);
    temp_y = (y - y % Main.BLOCK_HEIGHT);
  }
  
  public void setSize(int width, int height)
  {
    temp_width = width;
    temp_height = height;
  }
  
  public void addToPlatform()
  {
    if (TYPE == 12)
    {
      game.getPlatform().add(new Platform(game, temp_x + game.getCamera().getXOffset(), temp_y, 
        Main.BLOCK_WIDTH, Main.BLOCK_HEIGHT, 5));
      System.out.println("Called");
    }
    else
    {
      game.getPlatform().add(new Platform(game, temp_x + game.getCamera().getXOffset(), temp_y, 
        Main.BLOCK_WIDTH, Main.BLOCK_HEIGHT, TYPE));
    }
    num_plats += 1;
    if (FIRST_TIME)
    {
      game.getPlatform().clear();
      FIRST_TIME = false;
      num_plats = 0;
      num_enemys = 0;
      num_coins = 0;
    }
  }
  
  public void addStaticOjects()
  {
    if (TYPE == 7) {
      game.getStaticObjectList().add(new Key(game, temp_x + game.getCamera().getXOffset(), temp_y, 
        temp_width, temp_height));
    }
    if (TYPE > 7) {
      game.getStaticObjectList().add(new StaticObjects(game, temp_x + game.getCamera().getXOffset(), temp_y, 
        temp_width, temp_height, TYPE - 7));
    }
  }
  
  public void addSpikeMonster()
  {
    game.getMonsterList().add(new SlimeMon(game, temp_x + game.getCamera().getXOffset(), temp_y, 0, temp_width));
    num_enemys += 1;
  }
  
  public void addToCoinList()
  {
    game.getPickUpsList().add(new PickUps(game, temp_x + game.getCamera().getXOffset(), temp_y));
    num_coins += 1;
  }
  
  public void addToEventBlockList()
  {
    game.getEventBlockList().add(new EventBlock(game, temp_x + game.getCamera().getXOffset(), temp_y, event_cmd));
  }
  
  public void undo()
  {
    if (TYPE == 5)
    {
      if (num_coins == 0) {
        return;
      }
      game.getPickUpsList().remove(num_coins - 1);
      num_coins -= 1;
    }
    else if (TYPE == 6)
    {
      if (num_enemys == 0) {
        return;
      }
      game.getMonsterList().remove(num_enemys - 1);
      num_enemys -= 1;
    }
    else
    {
      if (num_plats == 0) {
        return;
      }
      game.getPlatform().remove(num_plats - 1);
      num_plats -= 1;
    }
  }
  
  public void tick() {}
  
  public void render(Graphics g)
  {
    for (int i = 0; i < game.getDialogueBlockList().size(); i++) {
      ((DialogueBlock)game.getDialogueBlockList().get(i)).render(g);
    }
    for (int i = 0; i < game.getStaticObjectList().size(); i++) {
      ((StaticObjects)game.getStaticObjectList().get(i)).render(g);
    }
    for (int i = 0; i < game.getPlatform().size(); i++) {
      ((Platform)game.getPlatform().get(i)).render(g);
    }
    for (int i = 0; i < game.getMonsterList().size(); i++) {
      ((Entity)game.getMonsterList().get(i)).render(g);
    }
    for (int i = 0; i < game.getPickUpsList().size(); i++) {
      ((PickUps)game.getPickUpsList().get(i)).render(g);
    }
    for (Bullets bullets : game.getBulletList()) {
      bullets.render(g);
    }
    if (isDrawingGrid()) {
      for (int i = 0; i < 100; i++)
      {
        g.setColor(Color.RED);
        g.drawLine(0 - game.getCamera().getXOffset(), Main.BLOCK_HEIGHT * i, 5000, Main.BLOCK_HEIGHT * i);
        g.drawLine(Main.BLOCK_WIDTH * i - game.getCamera().getXOffset(), 0, Main.BLOCK_WIDTH * i - game.getCamera().getXOffset(), 450);
      }
    }
    if (drawing)
    {
      g.setColor(Color.CYAN);
      try
      {
        mouse_x = game.getDisplay().getCanvas().getMousePosition().x;
      }
      catch (Exception localException) {}
      if (TYPE == 6) {
        g.drawRect(temp_x, temp_y, mouse_x - temp_x, 1);
      } else {
        g.drawRect(temp_x, temp_y, Main.BLOCK_WIDTH, Main.BLOCK_HEIGHT);
      }
    }
  }
  
  public boolean isDrawing()
  {
    return drawing;
  }
  
  public void setDrawing(boolean drawing)
  {
    this.drawing = drawing;
  }
  
  public boolean isDrawingGrid()
  {
    return drawing_grid;
  }
  
  public void setDrawingGrid(boolean draw_grid)
  {
    drawing_grid = draw_grid;
  }
}
