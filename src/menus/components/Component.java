package menus.components;

import game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Component {

    public int x, y, width, height;
    public String name;

    protected BufferedImage image;

    public abstract void tick(Main game);
    public abstract void render(Graphics g);

}
