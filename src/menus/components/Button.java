package menus.components;

import assets.ImageLoader;
import game.Main;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Button extends Component{

    private BufferedImage selected_overlay;

    private boolean drawOverlay = false;
    private int animCount = 0;

    public Button(String name, int x, int y, int width, int height) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        init();
    }

    public Button(String name, int x, int y, int width, int height, BufferedImage image) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;

        init();
    }

    public Button(String name, int x, int y, int width, int height, BufferedImage images[])     {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image_arr = images;

        init();
    }

    public void init(){
        selected_overlay = ImageLoader.loadImage("/textures/selected_overlay.png");
    }

    public void tick(Main game){
        try{
            int mouse_x = game.getDisplay().getCanvas().getMousePosition().x;
            int mouse_y = game.getDisplay().getCanvas().getMousePosition().y;

            if(mouse_x > x && mouse_y > y && mouse_x < x + width && mouse_y < y + height){
                drawOverlay = true;
            }
            else
                drawOverlay = false;
        }
        catch (Exception e){

        }
        if(image_arr != null){
            animCount++;
            if(animCount > (image_arr.length - 1)  * 10 ){
                animCount = 0;
            }
        }
    }

    public void render(Graphics g){
        if(image != null)
            g.drawImage(image, x, y, width, height, null);
        if(image_arr != null)
            g.drawImage(image_arr[animCount / 10], x, y, width, height, null);
        if(drawOverlay)
            g.drawImage(selected_overlay, x, y, width, height, null);
        if(Main.DEBUG){
            g.setColor(Color.RED);
            g.drawRect(x, y, width, height);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
