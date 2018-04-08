package objects;

import Game.Camera;
import Game.Main;
import assets.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintStream;

public class Key
  extends StaticObjects
{
  private static final long serialVersionUID = 5099037570929955748L;
  private Main game;
  private BufferedImage key_hud;
  private static int collected_pieces = 0;
  
  public Key(Main game, int x, int y, int width, int height)
  {
    super(game, x, y, width, height, 0);
    this.game = game;
    
    game.getPlayer().setObjectiveCompleted(false);
    key_hud = Assets.key_images[2];
    texture = Assets.key_images[2];
  }
  
  public void tick(double delta)
  {
    if (intersects(game.getPlayer()))
    {
      collected_pieces += 1;
      dispose();
      System.out.println("Key :" + Integer.toString(collected_pieces));
    }
    if (collected_pieces == 3)
    {
      game.getPlayer().setObjectiveCompleted(true);
      System.out.println("Objective Complete");
    }
    if (collected_pieces == 0) {
      key_hud = Assets.blank;
    }
    if (collected_pieces == 1) {
      key_hud = Assets.key_images[0];
    }
    if (collected_pieces == 2) {
      key_hud = Assets.key_images[1];
    }
    if (collected_pieces == 3) {
      key_hud = Assets.key_images[2];
    }
  }
  
  public void render(Graphics g)
  {
    g.drawImage(texture, x - game.getCamera().getXOffset(), y, width, height, null);
    g.drawImage(key_hud, 30, 20, 30, 30, null);
  }
  
  public static int getCollectedPieces()
  {
    return collected_pieces;
  }
  
  public static void setCollectedPieces(int collected_pieces)
  {
    collected_pieces = collected_pieces;
  }
}
