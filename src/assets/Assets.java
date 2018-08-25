package assets;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets
{
  public static BufferedImage blank;
  private static int delay = 0;
  private static int current_frame_num = 0;
  public static String Level_name = "Tutorial";
  public static BufferedImage[] background;
  private static BufferedImage inventory_sheet;
  public static BufferedImage inventory_background;
  public static BufferedImage inventory_outline;
  private static BufferedImage tile_sheet;
  public static BufferedImage[] stone_tiles;
  public static BufferedImage complete_tile;
  private static BufferedImage[] moving_tile_images;
  public static BufferedImage moving_tile;
  private static BufferedImage gui_sheet;
  public static BufferedImage gui_retry;
  public static BufferedImage gui_home;
  public static BufferedImage gui_next;
  private static BufferedImage player_sheet;
  public static BufferedImage[] player_images;
  public static BufferedImage spikes_img;
  private static BufferedImage crab_sheet;
  public static BufferedImage[] crab_images;
  private static BufferedImage coin_sheet;
  public static BufferedImage coin_texture;
  public static BufferedImage[] coin_images;
  private static BufferedImage ammo_sheet;
  public static BufferedImage ammo_texture;
  public static BufferedImage[] ammo_images;
  private static BufferedImage key_sheet;
  public static BufferedImage[] key_images;
  private static BufferedImage environment_sheet;
  public static BufferedImage environment_dark_tree;
  public static BufferedImage environment_light_tree;
  public static BufferedImage environment_bush;
  public static Font game_over_font;
  public static Font score_font;
  public static Font dailog_speaker_font;
  public static Font dailog_font;
  
  public static void init()
  {
    inventory_sheet = ImageLoader.loadImage("/textures/inventoryUI.png");
    tile_sheet = ImageLoader.loadImage("/textures/map_texture/" + Level_name + ".png");
    gui_sheet = ImageLoader.loadImage("/textures/GUI1.png");
    player_sheet = ImageLoader.loadImage("/textures/SpaceChar.png");
    crab_sheet = ImageLoader.loadImage("/textures/crab.png");
    coin_sheet = ImageLoader.loadImage("/textures/coin.png");
    ammo_sheet = ImageLoader.loadImage("/textures/Ammo.png");
    key_sheet = ImageLoader.loadImage("/textures/key.png");
    environment_sheet = ImageLoader.loadImage("/textures/environment.png");
    
    SpriteSheet inventory_sp = new SpriteSheet(inventory_sheet);
    SpriteSheet tile_sp = new SpriteSheet(tile_sheet);
    SpriteSheet player_sp = new SpriteSheet(player_sheet);
    SpriteSheet crab_sp = new SpriteSheet(crab_sheet);
    SpriteSheet coin_sp = new SpriteSheet(coin_sheet);
    SpriteSheet ammo_sp = new SpriteSheet(ammo_sheet);
    SpriteSheet key_sp = new SpriteSheet(key_sheet);
    
    background = new BufferedImage[3];
    background[0] = ImageLoader.loadImage("/textures/space_background.png");
    background[1] = ImageLoader.loadImage("/textures/level_complete_bg.png");
    
    spikes_img = ImageLoader.loadImage("/textures/spikes.png");
    
    inventory_background = inventory_sp.getSub(0, 0, 32, 32);
    inventory_outline = inventory_sp.getSub(32, 0, 32, 32);
    
    stone_tiles = new BufferedImage[9];
    stone_tiles[0] = tile_sp.getSub(0, 0, 64, 64);
    stone_tiles[1] = tile_sp.getSub(64, 0, 64, 64);
    stone_tiles[2] = tile_sp.getSub(128, 0, 64, 64);
    stone_tiles[3] = tile_sp.getSub(0, 64, 64, 64);
    stone_tiles[4] = tile_sp.getSub(64, 64, 64, 64);
    stone_tiles[5] = tile_sp.getSub(128, 64, 64, 64);
    stone_tiles[6] = tile_sp.getSub(0, 128, 64, 64);
    stone_tiles[7] = tile_sp.getSub(64, 128, 64, 64);
    stone_tiles[8] = tile_sp.getSub(128, 128, 64, 64);
    complete_tile = tile_sp.getSub(192, 0, 192, 192);
    moving_tile_images = new BufferedImage[3];
    moving_tile_images[0] = tile_sp.getSub(0, 192, 192, 172);
    moving_tile_images[1] = tile_sp.getSub(192, 192, 192, 172);
    moving_tile_images[2] = tile_sp.getSub(384, 192, 192, 172);
    
    gui_retry = gui_sheet.getSubimage(0, 0, 16, 16);
    gui_home = gui_sheet.getSubimage(16, 0, 16, 16);
    gui_next = gui_sheet.getSubimage(32, 0, 16, 16);
    
    player_images = new BufferedImage[10];
    player_images[0] = player_sp.getSub(0, 0, 44, 69);
    player_images[1] = player_sp.getSub(44, 0, 46, 69);
    player_images[2] = player_sp.getSub(90, 0, 46, 69);
    player_images[5] = player_sp.getSub(0, 69, 46, 69);
    player_images[4] = player_sp.getSub(46, 69, 46, 69);
    player_images[3] = player_sp.getSub(92, 69, 44, 69);
    
    crab_images = new BufferedImage[2];
    crab_images[0] = crab_sp.getSub(0, 0, 15, 16);
    crab_images[1] = crab_sp.getSub(16, 0, 15, 16);
    blank = crab_sp.getSub(32, 0, 16, 16);
    
    coin_images = new BufferedImage[4];
    coin_images[0] = coin_sp.getSub(0, 0, 16, 16);
    coin_images[1] = coin_sp.getSub(16, 0, 16, 16);
    coin_images[2] = coin_sp.getSub(32, 0, 16, 16);
    coin_images[3] = coin_sp.getSub(48, 0, 16, 16);
    coin_texture = coin_images[0];
    
    ammo_images = new BufferedImage[4];
    ammo_images[0] = ammo_sp.getSub(0, 0, 16, 16);
    ammo_images[1] = ammo_sp.getSub(16, 0, 16, 16);
    ammo_images[2] = ammo_sp.getSub(32, 0, 16, 16);
    ammo_images[3] = ammo_sp.getSub(48, 0, 16, 16);
    ammo_texture = ammo_images[0];
    
    key_images = new BufferedImage[3];
    key_images[0] = key_sp.getSub(32, 0, 16, 16);
    key_images[1] = key_sp.getSub(16, 0, 16, 16);
    key_images[2] = key_sp.getSub(0, 0, 16, 16);
    
    SpriteSheet environment_sp = new SpriteSheet(environment_sheet);
    environment_dark_tree = environment_sp.getSub(0, 0, 16, 16);
    environment_light_tree = environment_sp.getSub(16, 0, 16, 16);
    environment_bush = environment_sp.getSub(32, 0, 16, 16);
    
    game_over_font = new Font("Calibri", 80, 80);
    score_font = new Font("Calibri", 20, 20);
    dailog_speaker_font = new Font("Calibri", 20, 20);
    dailog_font = new Font("Calibri", 10, 10);
  }
  
  public static void update()
  {
    coin_texture = animate(4, coin_images, 15);
    moving_tile = animate(3, moving_tile_images, 10);
    ammo_texture = animate(4, ammo_images, 15);
  }
  
  public static BufferedImage animate(int frame_nums, BufferedImage[] frames, int speed)
  {
    BufferedImage frame = null;
    delay += 1;
    if (delay > speed)
    {
      delay = 0;
      current_frame_num += 1;
      if (current_frame_num > 5) {
        current_frame_num = 0;
      }
    }
    if (frame_nums <= current_frame_num) {
      frame = frames[(current_frame_num - frame_nums)];
    } else {
      frame = frames[current_frame_num];
    }
    return frame;
  }
}
