package menus;

import assets.Assets;
import game.Main;
import assets.ImageLoader;
import assets.SpriteSheet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import menus.components.Button;
import menus.components.Component;
import tools.FileHandler;

public class LevelSelector
        extends Menu {

    private static final int LEVELS = 3;
    private BufferedImage image;
    private int x = 0;
    private int completed_levels;

    private Main game;

    public LevelSelector(Main game) {
        this.game = game;
        init();
    }

    private void init() {
        image = ImageLoader.loadImage("/textures/select_level_banner.png");

        getComponentList().add(new Button("select_level_banner",131, 7, 389, 60, image));
        BufferedImage image = ImageLoader.loadImage("/textures/levels.png");
        SpriteSheet spriteSheet = new SpriteSheet(image);

        //! Static loop for built in levels
        for(int i = 0; i < LEVELS; i++){
            buttonList.add(new Button("level_" + (i + 1), i * 100 + 20, 100, 100, 100, spriteSheet.getSub(i * 100, 0, 100, 100)));
        }

        String temp = FileHandler.read("options.txt").replaceAll("\n", " ");
        String[] data = temp.split(" ");
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("L")) {
                System.out.println(data[i]);
                System.out.println(data[(i + 1)]);
                setCompleted_levels(Integer.parseInt(data[(i + 1)]));
            }
        }


    }

    public void render(Graphics g) {

        for(int i = 0; i < getComponentList().size(); i++){
            getComponentList().get(i).render(g);
        }
    }

    public void tick() {
        if (x == 64886) {
            x = 0;
        }

        for(int i = 0; i < getComponentList().size(); i++){
//            Component c = getComponentList().
        }
    }

    public int getCompleted_levels() {
        return completed_levels;
    }

    public void setCompleted_levels(int completed_levels) {
        this.completed_levels = completed_levels;
    }
}
