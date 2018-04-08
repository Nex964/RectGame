package objects;

import Game.Camera;
import Game.Main;
import assets.Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {
    private static final long serialVersionUID = -7822919744851247134L;
    private float a = 0.0F;
    private float delay = 0.0F;
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean jump = false;
    public boolean fire = false;
    protected boolean collision_top;
    protected boolean collision_bottom;
    protected boolean collision_left;
    protected boolean collision_right;
    private int spawn_x;
    private int spawn_y;
    private int ammos;
    private boolean isObjectiveCompleted = true;
    private int score = 0;
    private Main game;
    private Random r;

    public Player(Main game, int x, int y, int width, int height) {
        super(game, x, y, width, height);
        this.game = game;
        velX = 0;
        velY = -5.0F;
        spawn_x = x;
        spawn_y = y;
        health = 5;
        ammos = 0;
        r = new Random();
        collision_top = collision_bottom = collision_left = collision_right = false;

        texture = Assets.player_images[0];
    }

    public void tick(double delta) {
        if (y > 450) {
            isDead = true;
        }
        if (health == 0) {
            isDead = true;
        }
        if (isDead) {
            game.setGameState(1);
        }
        fall();
        move();
        //This loop should be called before collision(). Why? figure out on our own.
        for (int i = 0; i < game.getPlatform().size(); i++) {
            Platform temp = game.getPlatform().get(i);
            if (this.intersects(temp) && temp.type == 2) {
                game.setGameState(9);
            }
        }
        collision();
        fire();

        for (int i = 0; i < game.getMonsterList().size(); i++) {
            Entity mon = (Entity) game.getMonsterList().get(i);
            if (intersects(mon)) {
                if ((x + width > x) && (x + width < x + 24)) {
                    collision_left = true;
                }
                if ((x < x + width) && (x > x + width - 24)) {
                    collision_right = true;
                }
                if ((y + height > y) && (y + height < y + 15)) {
                    collision_top = true;
                }
                if ((y < y + height) && (y > y + height - 15)) {
                    collision_top = true;
                }
                isHit();
            } else {
                collision_top = collision_bottom = collision_left = collision_right = false;
            }
        }
        for (int i = 0; i < game.getPickUpsList().size(); i++) {
            PickUps pickup = (PickUps) game.getPickUpsList().get(i);
            if (intersects(pickup)) {
                game.getPickUpsList().remove(i);
                if (pickup.getType() == 0) {
                    score += 50;
                }
                if (pickup.getType() == 1) {
                    ammos += 20;
                }
            }
        }
        if (delta >= 1.0D) {
            animate();
        }
    }

    private void move() {
        if (right) {
            if (velX < 5) {
                velX += 1;
            }
            x += velX;
        }
        if (left) {
            if (velX < 5) {
                velX += 1;
            }
            x -= velX;
        }
        if (up) {
            jump(14);
        }
    }

    private void fire() {
        if (ammos > 0) {
            delay += 1.0F;
            if ((fire) && (delay % 5.0F == 0.0F)) {
                if (delay == 6.0F) {
                    delay = 0.0F;
                }
                ammos -= 1;
                if (left) {
                    game.getBulletList().add(new Bullets(game, x - 5, y + 10 + r.nextInt(20), 5, 5, 1));
                } else {
                    game.getBulletList().add(new Bullets(game, x + width + 5, y + 10 + r.nextInt(10), 5, 5, 1));
                }
            }
        }
    }

    public void isHit() {
        health -= 1;
        if (collision_top) {
            jump(18);
        }
        if (collision_left) {
            x -= 25;
        }
        if (collision_right) {
            x += 25;
        }
    }

    public void animate() {
        a += 1.0F;
        if (a > 20.0F) {
            a = 0.0F;
        }
        if (right) {
            if (a == 1.0F) {
                texture = Assets.player_images[1];
            }
            if (a == 7.0F) {
                texture = Assets.player_images[0];
            }
            if (a == 12.0F) {
                texture = Assets.player_images[2];
            }
            if (a == 16.0F) {
                texture = Assets.player_images[0];
            }
        } else if (left) {
            if (a == 1.0F) {
                texture = Assets.player_images[4];
            }
            if (a == 7.0F) {
                texture = Assets.player_images[3];
            }
            if (a == 12.0F) {
                texture = Assets.player_images[5];
            }
            if (a == 16.0F) {
                texture = Assets.player_images[3];
            }
        } else if (!up) {
            texture = Assets.player_images[0];
            a = 0.0F;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawImage(texture, x - game.getCamera().getXOffset(), y, width, height, null);

        g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.3F));
        g.fillRect(0, 0, 650, 45);
        g.setFont(Assets.score_font);
        g.setColor(Color.WHITE);
        g.drawString("Score : ", 20, 20);
        g.drawString(Integer.toString(score), 90, 20);
        g.drawString("Health :", 520, 20);
        g.setColor(Color.RED);
        g.drawString(Integer.toString(health), 600, 20);
        if (ammos != 0) {
            g.setColor(Color.WHITE);
            g.drawString("Ammo :", 520, 40);
            g.setColor(Color.GREEN);
            g.drawString(Integer.toString(ammos), 600, 40);
        }
    }

    public void reset() {
        isDead = false;
        health = 5;
        x = spawn_x;
        y = spawn_y;
        score = 0;
        ammos = 0;
        setObjectiveCompleted(true);
    }

    public boolean isObjectiveCompleted() {
        return isObjectiveCompleted;
    }

    public void setObjectiveCompleted(boolean isObjectiveCompleted) {
        this.isObjectiveCompleted = isObjectiveCompleted;
    }
}
