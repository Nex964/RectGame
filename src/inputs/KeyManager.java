package inputs;

import game.Dailogs;
import game.Inventory;
import game.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import objects.Player;
import tools.FileHandler;
import tools.MessageBox;
import tools.TextBox;
import world.LevelDesigner;
import world.World;

public class KeyManager
        implements KeyListener {
    private Main game;

    public KeyManager(Main game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_I) {
            new MessageBox("Developer Tips",
                    "Level Editor",
                    "You can press 'E' to see available Rects\n" +
                            "Use Left/Right Arrow Keys to Navigate while editing the world.\n" +
                            "Press 'O' Key to save/upload the Edited level\n" +
                            "Press 'L' to load last saved level\n" +
                            "You can also press 'Up' Arrow Key to start testing while editing.\n" +
                            "But make sure to save the level before testing!!");
        }

        if (game.getGameState() == 4) {
            if (key == 69) {
                Inventory.toggle();
            }
            if (key == 49) {
                LevelDesigner.TYPE = 0;
            }
            if (key == 51) {
                LevelDesigner.TYPE = 1;
            }
            if (key == 50) {
                LevelDesigner.TYPE = 2;
            }
            if (key == 52) {
                LevelDesigner.TYPE = 4;
            }
            if (key == 53) {
                LevelDesigner.TYPE = 3;
            }
            if (key == 54) {
                LevelDesigner.TYPE = 5;
            }
            if (key == 55) {
                LevelDesigner.TYPE = 6;
            }
            if (key == 56) {
                LevelDesigner.TYPE = 7;
            }
            if (key == 57) {
                LevelDesigner.TYPE = 8;
            }
            if (key == 48) {
                LevelDesigner.TYPE = 9;
            }
            if (key == 45) {
                LevelDesigner.TYPE = 10;
            }
            if (key == 85) {
                game.getLevelDesigner().undo();
            }
            if (key == 79) {
                game.getStoreWorld().storePlatData();
                new TextBox(game.getLevelDesigner(), 1);
            }
            if (key == 76) {
                game.reset();
                World.LEVEL = 3;
                game.getWorld().selectLevel();
            }
            if (key == 93) {
                Main.BLOCK_HEIGHT += Main.BLOCK_HEIGHT;
                Main.BLOCK_WIDTH += Main.BLOCK_WIDTH;
            }
            if (key == 91) {
                Main.BLOCK_WIDTH /= 2;
                Main.BLOCK_HEIGHT /= 2;
            }
            if (key == 71) {
                if (game.getLevelDesigner().isDrawingGrid()) {
                    game.getLevelDesigner().setDrawingGrid(false);
                } else {
                    game.getLevelDesigner().setDrawingGrid(true);
                }
            }
            if (key == 80) {
                game.getWorld().getWorldData().updateData();
            }
            if (key == 27) {
                game.setGameState(2);
                LevelDesigner.FIRST_TIME = true;
            }
            if (key == 37) {
                game.getCamera().setxOffset(game.getCamera().getXOffset() - 20);
            }
            if (key == 39) {
                game.getCamera().setxOffset(game.getCamera().getXOffset() + 20);
            }
            if (key == 38) {
                Main.starter = true;
                game.setPlayer(new Player(game, 10, 300, 20, 40));
            }
        }
        if ((game.getGameState() == 3) &&
                (key == 27)) {
            game.setGameState(2);
        }
        if ((game.getGameState() == 0) &&
                (Dailogs.isVisible())) {
            Dailogs.toggle();
        }
        if (key == 32) {
            game.getPlayer().fire = true;
        }
        if (key == 68) {
            game.getPlayer().right = true;
        }
        if (key == 65) {
            game.getPlayer().left = true;
        }
        if (key == 87) {
            game.getPlayer().up = true;
        }
        if (key == 83) {
            game.getPlayer().down = true;
        }
        if (key == 80) {
            game.pause = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 32) {
            game.getPlayer().fire = false;
        }
        if (key == 68) {
            game.getPlayer().right = false;
        }
        if (key == 65) {
            game.getPlayer().left = false;
        }
        if (key == 87) {
            game.getPlayer().up = false;
        }
        if (key == 83) {
            game.getPlayer().down = false;
        }
        if (key == 80) {
            game.pause = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}
