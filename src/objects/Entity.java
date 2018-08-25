package objects;

import Game.Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity extends Object {
    private static final long serialVersionUID = 1820218228092288762L;
    protected static float gravity = 1.0f;
    protected int velX;
    protected float velY;
    protected int spawn_x;
    protected int spawn_y;
    protected int isJumping = 0;
    protected boolean cant_go_left;
    protected boolean cant_go_right;
    protected boolean isDead = false;
    protected int health = 5;
    protected BufferedImage texture;
    public int type = 0;
    private Main game;

    public Entity(Main game, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.game = game;
        spawn_x = x;
        spawn_y = y;
        cant_go_left = cant_go_right = false;
    }

    public Entity(Main game, int x, int y, int width, int height, int type) {
        super(x, y, width, height);
        this.game = game;
        spawn_x = x;
        spawn_y = y;
        cant_go_left = cant_go_right = false;
    }

    protected void fall() {
        if (velY < gravity * 8.0F) {
            velY += gravity;
        }
        y = (int) (y + velY);
        collision();
    }

    protected void jump(int height) {
        if (isJumping == 0) {
            velY = (-height);
            isJumping = 1;
        }
        if ((cant_go_right) && (isJumping != 2)) {
            velX = (-(velX + 10));
            velY = (-height);
            isJumping = 2;
        }
        if ((cant_go_left) && (isJumping != 3)) {
            velX = (-(velX + 10));
            velY = (-height);
            isJumping = 3;
        }
    }

    protected void collision() {
        for (int i = 0; i < game.getPlatform().size(); i++) {
            Platform temp = (Platform) game.getPlatform().get(i);
            if (intersects(temp) && (temp.isVisible())) {
                if (type == 2 && game.getPlayer().isObjectiveCompleted()) {
                    game.setGameState(9);
                }
                if (x + width > temp.x && x + width < temp.x + 10 && y + height > temp.y + 1) {
                    x = temp.x - width;
                    velX = 0;
                    cant_go_left = true;
                } else {
                    cant_go_left = false;
                }
                if ((x < temp.x + temp.width) && (x > temp.x + temp.width - 10) && (y + height > temp.y + 1)) {
                    x = temp.x + temp.width;
                    velX = 0;
                    cant_go_right = true;
                } else {
                    cant_go_right = false;
                }
                if ((y + height > temp.y) && (y + height < temp.y + 15) && (x + width > temp.x + 2) && (x < temp.x + temp.width - 1)) {
                    if (temp.getType() == 3) {
                        isDead = true;
                    }
                    if (temp.type == 2) {
                        y = temp.y - height + 1;
                    } else {
                        y = temp.y - height;
                    }
                    velY = 0;
                    isJumping = 0;
                }
                if ((y < temp.y + temp.height) && (y > temp.y + temp.height - 15) && (x + width > temp.x + 2) && (x < temp.x + temp.width - 1)) {
                    y = temp.y + temp.height + 1;
                    velY = 0;
                }
            }
        }
        for (int i = 0; i < game.getBulletList().size(); i++) {
            Bullets bullet = (Bullets) game.getBulletList().get(i);
            if (intersects(bullet)) {
                game.getBulletList().remove(i);
                health -= bullet.getDamage();
            }
        }
    }

    public void tick(double delta) {
    }

    public void render(Graphics g) {
    }

    public void deathChecker() {
        if (health == 0) {
            isDead = true;
        }
        if (isDead) {
            game.getMonsterList().remove(game.getMonsterList().indexOf(this));
        }
    }

    public void dispose() {
        dispose();
    }
}
