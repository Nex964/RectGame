package inputs;

import Game.Dailogs;
import Game.Inventory;
import Game.Main;
import Game.Transaction;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;

import menus.MainMenu;
import objects.Player;
import tools.TextBox;
import world.LevelDesigner;
import world.World;

public class MouseInput
  implements MouseListener
{
  private Main game;
  private World world;
  private int temp_x;
  private int temp_y;
  
  public MouseInput(Main game, World world)
  {
    this.game = game;
    this.world = world;
    
  }
  
  public void mouseClicked(MouseEvent e) {}
  
  public void mouseReleased(MouseEvent e)
  {
    int x = e.getX();
    int y = e.getY();
    if ((game.getGameState() == 4) && 
      (!Inventory.isVisible()))
    {
      game.getLevelDesigner().setDrawing(false);
      game.getLevelDesigner().setSize(x - temp_x, y - temp_y);
      if (LevelDesigner.TYPE != 5) {
        if (LevelDesigner.TYPE == 6) {
          game.getLevelDesigner().addSpikeMonster();
        } else if ((LevelDesigner.TYPE > 6) && (LevelDesigner.TYPE < 12)) {
          game.getLevelDesigner().addStaticOjects();
        } else {
          game.getLevelDesigner().addToPlatform();
        }
      }
    }
  }
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public void mousePressed(MouseEvent e)
  {
    int x = e.getX();
    int y = e.getY();
    if ((game.getGameState() == 0) && 
      (Dailogs.isVisible())) {
      Dailogs.toggle();
    }
    if (game.getGameState() == 4)
    {
      temp_x = x;
      temp_y = y;
      if (Inventory.isVisible())
      {
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            if ((x > 40 + 143 * j) && (y > 28 + 99 * i) && (x < 40 + 143 * j + 143) && (y < 28 + 99 * i + 99))
            {
              if ((j == 0) && (i == 0)) {
                LevelDesigner.TYPE = 0;
              }
              if ((j == 1) && (i == 0)) {
                LevelDesigner.TYPE = 2;
              }
              if ((j == 2) && (i == 0)) {
                LevelDesigner.TYPE = 1;
              }
              if ((j == 3) && (i == 0)) {
                LevelDesigner.TYPE = 4;
              }
              if ((j == 0) && (i == 1)) {
                LevelDesigner.TYPE = 3;
              }
              if ((j == 1) && (i == 1)) {
                LevelDesigner.TYPE = 5;
              }
              if ((j == 2) && (i == 1)) {
                LevelDesigner.TYPE = 6;
              }
              if ((j == 3) && (i == 1)) {
                LevelDesigner.TYPE = 7;
              }
              if ((j == 0) && (i == 2)) {
                LevelDesigner.TYPE = 8;
              }
              if ((j == 1) && (i == 2)) {
                LevelDesigner.TYPE = 9;
              }
              if ((j == 2) && (i == 2)) {
                LevelDesigner.TYPE = 10;
              }
              if ((j == 3) && (i == 2)) {
                LevelDesigner.TYPE = 11;
              }
              if ((j == 0) && (i == 3))
              {
                LevelDesigner.TYPE = 12;
                System.out.println("Key Pressed");
              }
            }
          }
        }
      }
      else
      {
        game.getLevelDesigner().setPosition(x, y);
        if (LevelDesigner.TYPE == 5) {
          game.getLevelDesigner().addToCoinList();
        } else if (LevelDesigner.TYPE == 11) {
          new TextBox(game.getLevelDesigner());
        } else {
          game.getLevelDesigner().setDrawing(true);
        }
      }
    }
    if ((game.getGameState() == 2) && (x > 175) && (x < 475))
    {
      if ((y > 200) && (y < 280))
      {

        MainMenu.animate = true;
//        game.setGameState(3);
      }
      if ((y > 300) && (y < 380))
      {
        game.setGameState(4);
        game.getLevelDesigner();LevelDesigner.FIRST_TIME = true;
      }
    }
    if (game.getGameState() == 3)
    {
      if ((x > 30) && (y > 50) && (x < 90) && (y < 110))
      {
        game.getTransaction().setColor(1.0F, 1.0F, 1.0F, 1.0F);
        World.LEVEL = 1;
        world.selectLevel();
        game.setGameState(0);
      }
      if ((x > 130) && (y > 80) && (x < 180) && (y < 130))
      {
        game.getTransaction().setColor(1.0F, 1.0F, 1.0F, 1.0F);
        World.LEVEL = 2;
        world.selectLevel();
        game.setGameState(0);
      }
      if ((x > 40) && (y > 150) && (x < 120) && (y < 230))
      {
        game.getTransaction().setColor(1.0F, 1.0F, 1.0F, 1.0F);
        World.LEVEL = 3;
        world.selectLevel();
        game.setGameState(0);
      }
    }
    if (game.getGameState() == 1)
    {
      if ((x > 100) && (x < 200) && (y > 175) && (y < 275))
      {
        game.getTransaction().setColor(1.0F, 1.0F, 1.0F, 1.0F);
        game.reset();
        game.getPlayer().reset();
        world.selectLevel();
        game.setGameState(0);
      }
      if ((x > 300) && (x < 400) && (y > 175) && (y < 275))
      {
        game.getTransaction().setColor(0.0F, 0.0F, 0.0F, 1.0F);
        game.reset();
        game.getPlayer().reset();
        game.setGameState(2);
      }
    }
    if (game.getGameState() == 9)
    {
      if ((x > 100) && (x < 200) && (y > 175) && (y < 275))
      {
        game.getTransaction().setColor(1.0F, 1.0F, 1.0F, 1.0F);
        game.reset();
        game.getPlayer().reset();
        world.selectLevel();
        game.setGameState(0);
      }
      if ((x > 300) && (x < 400) && (y > 175) && (y < 275))
      {
        game.getTransaction().setColor(0.0F, 0.0F, 0.0F, 1.0F);
        game.reset();
        game.getPlayer().reset();
        game.setGameState(2);
      }
      if ((x > 500) && (x < 600) && (y > 175) && (y < 275))
      {
        game.reset();
        game.getPlayer().reset();
        game.getTransaction().setColor(0.0F, 0.0F, 0.0F, 1.0F);
        game.setGameState(0);
        World.LEVEL += 1;
        world.selectLevel();
      }
    }
  }
}
