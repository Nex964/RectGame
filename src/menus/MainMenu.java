package menus;

import Game.Main;
import assets.ImageLoader;
import assets.SpriteSheet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainMenu
        extends Menu {
    private BufferedImage background;
    private BufferedImage[] play_ui;
    private BufferedImage play_sheet;
    private BufferedImage play_image;
    private BufferedImage[] editor_ui;
    private BufferedImage editor_sheet;
    private BufferedImage editor_image;
    private int x = 0;
    private int a = 0;
    private int b = 0;
    private int temp = 0;

    private Main game;

    private int nextAnim = 0;

    public static boolean animate = false;

    public MainMenu(Main game) {
        background = ImageLoader.loadImage("/textures/background.png");
        play_sheet = ImageLoader.loadImage("/textures/menuUi.png");
        editor_sheet = ImageLoader.loadImage("/textures/editorUi.png");
        SpriteSheet sp = new SpriteSheet(play_sheet);
        SpriteSheet sp1 = new SpriteSheet(editor_sheet);
        play_ui = new BufferedImage[3];
        editor_ui = new BufferedImage[6];
        play_ui[0] = sp.getSub(0, 0, 300, 80);
        play_ui[1] = sp.getSub(302, 0, 298, 80);
        play_ui[2] = sp.getSub(604, 0, 296, 80);

        editor_ui[0] = sp1.getSub(0, 0, 300, 80);
        editor_ui[1] = sp1.getSub(301, 0, 299, 80);
        editor_ui[2] = sp1.getSub(600, 0, 299, 79);
        editor_ui[3] = sp1.getSub(899, 0, 299, 80);
        editor_ui[4] = sp1.getSub(1201, 0, 301, 79);
        editor_ui[5] = sp1.getSub(1500, 0, 299, 80);

        this.game = game;
    }

    public void render(Graphics g) {
        g.drawImage(background, nextAnim + x, 0, 650, 450, null);
        g.drawImage(background, nextAnim + x - 650, 0, 650, 450, null);
        g.drawImage(background, nextAnim + x + 650, 0, 650, 450, null);

        g.drawImage(play_image, nextAnim + 165, 200, 320, 80, null);

        g.drawImage(editor_image, nextAnim + 165, 300, 320, 80, null);
    }

    public void tick() {
        x -= 1;
        if (x == -650) {
            x = 0;
        }
        a += 1;
        if (a == 0) {
            play_image = play_ui[0];
        }
        if (a == 7) {
            play_image = play_ui[1];
        }
        if (a == 14) {
            play_image = play_ui[2];
        }
        if (a == 21) {
            a = -1;
        }
        temp += 1;
        if (temp > 8) {
            temp = 0;
            b += 1;
        }
        switch (b) {
            case 0:
                editor_image = editor_ui[0];
                break;
            case 1:
                editor_image = editor_ui[1];
                break;
            case 2:
                editor_image = editor_ui[2];
                break;
            case 3:
                editor_image = editor_ui[3];
                break;
            case 4:
                editor_image = editor_ui[4];
                break;
            case 5:
                editor_image = editor_ui[5];
                break;
            case 6:
                b = 0;
        }

        if(animate == true){
            nextAnim+= 20;
        }
        if(nextAnim >= 650){
            game.setGameState(3);
            game.getTransaction().setColor(1.0F, 1.0F, 1.0F, 1.0F);
            animate = false;
            nextAnim = 0;
        }
    }
}
