package menus;

import game.Main;
import assets.ImageLoader;
import assets.SpriteSheet;
import menus.components.Button;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainMenu
        extends Menu {

    private BufferedImage background[];
    private BufferedImage title_banner[];
    private BufferedImage play_image[];
    private BufferedImage editor_image[];

    private Main game;

    private int animCount = 1;

    public MainMenu(Main game) {
        background = new BufferedImage[3];
        background = new BufferedImage[3];
        background[0] = ImageLoader.loadImage("/textures/background.png").getSubimage(0, 0, 650, 450);
        background[1] = ImageLoader.loadImage("/textures/background_1.png").getSubimage(0, 0, 650, 450);
        background[2] = ImageLoader.loadImage("/textures/background_2.png").getSubimage(0, 0, 650, 450);
        play_image = new BufferedImage[3];
        play_image[0] = ImageLoader.loadImage("/textures/menuUi.png").getSubimage(0, 0, 300, 80);
        play_image[1] = ImageLoader.loadImage("/textures/menuUi.png").getSubimage(300, 0, 300, 80);
        play_image[2] = ImageLoader.loadImage("/textures/menuUi.png").getSubimage(600, 0, 300, 80);

        editor_image = new BufferedImage[6];
        editor_image[0] = ImageLoader.loadImage("/textures/editorUi.png").getSubimage(0, 0, 300, 80);
        editor_image[1] = ImageLoader.loadImage("/textures/editorUi.png").getSubimage(300, 0, 300, 80);
        editor_image[2] = ImageLoader.loadImage("/textures/editorUi.png").getSubimage(600, 0, 300, 80);
        editor_image[3] = ImageLoader.loadImage("/textures/editorUi.png").getSubimage(900, 0, 300, 80);
        editor_image[4] = ImageLoader.loadImage("/textures/editorUi.png").getSubimage(1200, 0, 300, 80);
        editor_image[5] = ImageLoader.loadImage("/textures/editorUi.png").getSubimage(1500, 0, 300, 80);

        title_banner = new BufferedImage[3];
        title_banner[0] = ImageLoader.loadImage("/textures/title_banner.png");
        title_banner[1] = ImageLoader.loadImage("/textures/title_banner_1.png");
        title_banner[2] = ImageLoader.loadImage("/textures/title_banner_2.png");

        buttonList.add(new Button("play", 177, 205, 296, 54, play_image));
        buttonList.add(new Button("online", 177, 280, 296, 54, editor_image));

        this.game = game;
    }

    public void render(Graphics g) {
        g.drawImage(background[(int)animCount / 1000], 0, 0, 650, 450, null);
        g.drawImage(title_banner[(int)animCount / 1000], 31, 16, 602, 135, null);

        animCount++;
        if(animCount >= 3000){
            animCount = 1;
        }
        for(int i = 0; i < buttonList.size(); i++){
            buttonList.get(i).render(g);
        }
    }

    public void tick() {
        for(int i = 0; i < buttonList.size(); i++){
            buttonList.get(i).tick(game);
        }
    }

}
