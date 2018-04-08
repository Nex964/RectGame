package objects;

import Game.Camera;
import Game.Main;
import assets.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import world.World;

public class Platform extends Object {
	private Main game;
	public int type = 0;
	private int number = 0;
	private Color c;
	private boolean go = false;
	private int move_hori_speed = 1;
	private int move_vert_speed = 1;
	private boolean visible = true;
	private BufferedImage texture;
	private int a = 0;
	private static final long serialVersionUID = 5817370818518285071L;

	public Platform(Main game, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.game = game;
		init();
	}

	public Platform(Main game, int x, int y, int width, int height, int type) {
		super(x, y, width, height);
		this.game = game;
		this.type = type;
		init();
	}

	public Platform(Main game, int x, int y, int width, int height, int type, int number) {
		super(x, y, width, height);
		this.game = game;
		this.type = type;
		this.number = number;
		init();
	}

	public void init() {
		if (type == 2) {
			c = new Color(0.0f, 1.0f, 0.4f);
		}
		setTexture(Assets.stone_tiles[0]);
	}

	public void tick(double delta) {
		if (World.LEVEL == 1) {
			if ((number == 1) && (type == 1) && (game.getPlayer().x > 801)) {
				go = true;
			}
			if ((number == 1) && (type == 1) && (go)) {
				if (x < 1178) {
					x += 1;
				} else {
					y -= 1;
					if (x < 110) {
						go = false;
					}
				}
			}
		} else {
			if (type == 1) {
				for (int i = 0; i < game.getPlatform().size(); i++) {
					Platform temp = (Platform) game.getPlatform().get(i);
					if (intersects(temp)) {
						if (x > x) {
							move_hori_speed = 1;
						}
						if (x < x) {
							move_hori_speed = -1;
						}
					}
				}
				x += move_hori_speed;
			}
			if (type == 4) {
				for (int i = 0; i < game.getPlatform().size(); i++) {
					Platform temp = (Platform) game.getPlatform().get(i);
					if (intersects(temp)) {
						if (y > y) {
							move_vert_speed = 1;
						}
						if (y < y) {
							move_vert_speed = -1;
						}
					}
				}
				y += move_vert_speed;
			}
		}
		if (type == 5) {
			a += 1;
			if (a >= 300) {
				if (visible) {
					visible = false;
					a = 0;
				} else {
					visible = true;
					a = 0;
				}
			}
		}
		for (int i = 0; i < game.getBulletList().size(); i++) {
			Bullets bullet = (Bullets) game.getBulletList().get(i);
			if (intersects(bullet)) {
				game.getBulletList().remove(i);
			}
		}
	}

	public void render(Graphics g) {
		if (type == 3) {
			g.drawImage(Assets.spikes_img, x - game.getCamera().getXOffset(), y, width, height, null);
		} else if ((type == 0) || (type == 5)) {
			if (visible) {
				g.drawImage(texture, x - game.getCamera().getXOffset(), y, width, height, null);
			}
		} else if (type == 2) {
			g.drawImage(Assets.complete_tile, x - game.getCamera().getXOffset(), y, width, height, null);
		} else if ((type == 1) || (type == 4)) {
			g.drawImage(Assets.moving_tile, x - game.getCamera().getXOffset(), y, width, height, null);
		} else {
			g.setColor(c);
			g.fillRect(x - game.getCamera().getXOffset(), y, width, height);
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public int getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
}
