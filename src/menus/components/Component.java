package menus.components;

import game.Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Component {

    protected int x, y, width, height;
    protected String name;

    protected BufferedImage image;
    protected BufferedImage image_arr[];

    public abstract void tick(Main game);
    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage[] getImage_arr() {
        return image_arr;
    }

    public void setImage_arr(BufferedImage[] image_arr) {
        this.image_arr = image_arr;
    }
}
