package menus;

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

    private int level = 2;
    private BufferedImage image;
    private BufferedImage[] levelImage;
    private int x = 0;
    private int completed_levels;

    private Main game;

    public LevelSelector(Main game) {
        this.game = game;
        init();
    }

    private void init() {
        image = ImageLoader.loadImage("/textures/select_level_banner.png");
        levelImage = new BufferedImage[10];
        SpriteSheet sp = new SpriteSheet(image);
        levelImage[0] = sp.getSub(0, 0, 100, 100);
        levelImage[1] = sp.getSub(100, 0, 100, 100);
        levelImage[2] = sp.getSub(200, 0, 100, 100);
        levelImage[9] = assets.Assets.background[0];

        getComponentList().add(new Button("select_level_banner",131, 7, 389, 60, image));

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
        g.drawImage(levelImage[9], x, 0, 650, 450, null);
        Font font = new Font("Arial", 40, 40);
        g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
        g.fillRect(0, 0, 650, 50);
        g.setFont(font);
        g.setColor(new Color(0.9F, 0.2F, 0.2F));
        g.drawString("World - 1 : Select Level", 20, 40);

        g.drawImage(levelImage[0], 30, 50, 60, 60, null);

        g.drawImage(levelImage[1], 130, 80, 50, 50, null);

        g.drawImage(levelImage[2], 40, 150, 80, 80, null);

        for(int i = 0; i < getComponentList().size(); i++){
            getComponentList().get(i).render(g);
        }
    }

    public void tick() {
        if (x == 64886) {
            x = 0;
        }

        for(int i = 0; i < getComponentList().size(); i++){
            getComponentList().get(i).tick(game);
        }
    }

    public int getCompleted_levels() {
        return completed_levels;
    }

    public void setCompleted_levels(int completed_levels) {
        this.completed_levels = completed_levels;
    }
}
