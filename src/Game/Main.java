package Game;

import assets.Assets;
import display.Display;
import inputs.KeyManager;
import inputs.MouseInput;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import menus.GameOver;
import menus.LevelCompleteMenu;
import menus.LevelSelector;
import menus.MainMenu;
import menus.Menu;
import objects.Bullets;
import objects.DialogueBlock;
import objects.Entity;
import objects.EventBlock;
import objects.Key;
import objects.PickUps;
import objects.Platform;
import objects.Player;
import objects.StaticObjects;
import world.LevelDesigner;
import world.StoreWorld;
import world.World;

public class Main implements Runnable {
	public static int BLOCK_WIDTH = 64;
	public static int BLOCK_HEIGHT = 64;
	private boolean running = false;
	public boolean isLoggedIn = false;
	private static int gameState = 2;
	public boolean pause = false;
	public static boolean starter = false;
	private ArrayList<Platform> platList = new ArrayList();
	private ArrayList<Entity> monsterList = new ArrayList();
	private ArrayList<PickUps> pickUpsList = new ArrayList();
	private ArrayList<StaticObjects> staticObjectList = new ArrayList();
	private ArrayList<Bullets> bulletList = new ArrayList();
	private ArrayList<DialogueBlock> dialogueBlockList = new ArrayList();
	private ArrayList<EventBlock> eventBlockList = new ArrayList();
	private String title;
	private int width;
	private int height;
	private Display display;
	private Player player;
	private World world;
	private Camera camera;
	private Menu menu;
	private Menu level;
	private Menu gameOver;
	private Menu levelComplete;
	private LevelDesigner levelDesigner;
	private Transaction trans;
	private StoreWorld storeWorld;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private Color backgroundColor;

	public Main(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public void tick(double delta) {
		if (gameState == 0) {
			if (!pause) {
				player.tick(delta);
				camera.tick();
				for (int i = 0; i < dialogueBlockList.size(); i++) {
					((DialogueBlock) dialogueBlockList.get(i)).tick(delta);
				}
				for (int i = 0; i < eventBlockList.size(); i++) {
					((EventBlock) eventBlockList.get(i)).tick(delta);
				}
				for (int i = 0; i < monsterList.size(); i++) {
					((Entity) monsterList.get(i)).tick(delta);
				}
				for (int i = 0; i < staticObjectList.size(); i++) {
					((StaticObjects) staticObjectList.get(i)).tick(delta);
				}
				for (int i = 0; i < platList.size(); i++) {
					((Platform) platList.get(i)).tick(delta);
				}
				for (int i = 0; i < bulletList.size(); i++) {
					((Bullets) bulletList.get(i)).tick(delta);
				}
				for (int i = 0; i < pickUpsList.size(); i++) {
					((PickUps) pickUpsList.get(i)).tick(delta);
				}
			}
			Dailogs.tick();
		}
		if (gameState == 4) {
			levelDesigner.tick();
			camera.tick();
			Inventory.tick();
			if (starter) {
				player.tick(delta);
				for (int i = 0; i < dialogueBlockList.size(); i++) {
					((DialogueBlock) dialogueBlockList.get(i)).tick(delta);
				}
				for (int i = 0; i < monsterList.size(); i++) {
					((Entity) monsterList.get(i)).tick(delta);
				}
				for (int i = 0; i < platList.size(); i++) {
					((Platform) platList.get(i)).tick(delta);
				}
				for (int i = 0; i < pickUpsList.size(); i++) {
					((PickUps) pickUpsList.get(i)).tick(delta);
				}
				for (int i = 0; i < bulletList.size(); i++) {
					((Bullets) bulletList.get(i)).tick(delta);
				}
				for (int i = 0; i < staticObjectList.size(); i++) {
					((StaticObjects) staticObjectList.get(i)).tick(delta);
				}
				for (int i = 0; i < eventBlockList.size(); i++) {
					((EventBlock) eventBlockList.get(i)).tick(delta);
				}
			}
		}
		if (gameState == 2) {
			menu.tick();
		}
		if (gameState == 3) {
			level.tick();
		}
		if (gameState == 1) {
			gameOver.tick();
		}
		if (gameState == 9) {
			levelComplete.tick();
		}
		trans.tick();
		Assets.update();
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(backgroundColor);
		g.clearRect(0, 0, width, height);
		g.fillRect(0, 0, width, height);
		if (gameState == 0) {
			for (int i = 0; i < staticObjectList.size(); i++) {
				((StaticObjects) staticObjectList.get(i)).render(g);
			}
			for (int i = 0; i < platList.size(); i++) {
				((Platform) platList.get(i)).render(g);
			}
			for (int i = 0; i < bulletList.size(); i++) {
				((Bullets) bulletList.get(i)).render(g);
			}
			for (int i = 0; i < pickUpsList.size(); i++) {
				((PickUps) pickUpsList.get(i)).render(g);
			}
			for (int i = 0; i < monsterList.size(); i++) {
				((Entity) monsterList.get(i)).render(g);
			}
			player.render(g);
			Dailogs.render(g);
		}
		if (gameState == 9) {
			levelComplete.render(g);
		}
		if (gameState == 1) {
			gameOver.render(g);
		}
		if (gameState == 4) {
			levelDesigner.render(g);
			if (starter) {
				player.render(g);
			}
			Inventory.render(g);
		}
		if (gameState == 2) {
			menu.render(g);
		}
		if (gameState == 3) {
			level.render(g);
		}
		trans.render(g);

		bs.show();
		g.dispose();
	}

	public void init() {
		display = new Display(title, width, height);
		Assets.init();
		Dailogs.init(this);
		menu = new MainMenu(this);
		gameOver = new GameOver(this);
		levelComplete = new LevelCompleteMenu(this);
		levelDesigner = new LevelDesigner(this);
		level = new LevelSelector(this);
		storeWorld = new StoreWorld(this);
		player = new Player(this, 20, 10, 20, 40);
		camera = new Camera(this);
		display.getFrame().addKeyListener(new KeyManager(this));
		world = new World(this);
		backgroundColor = new Color(1, 1, 1, 1);
		trans = new Transaction(this);
		display.getCanvas().addMouseListener(new MouseInput(this, world));
	}

	public void run() {
		init();
		double fps = 60;
		double delta = 0;
		long lastTime = System.nanoTime();
		while (running) {
			long now = System.nanoTime();
			double ns = 1000000000 / fps;
			long timer = System.currentTimeMillis();
			int frames = 0;
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				tick(delta);

				delta--;
			}
			if (running) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - timer > 1000L) {
				timer += 1000L;
				System.out.println("FPS : " + frames);
				frames = 0;
			}
		}
		stop();
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reset() {
		platList.clear();
		monsterList.clear();
		pickUpsList.clear();
		staticObjectList.clear();
		bulletList.clear();
		dialogueBlockList.clear();
		eventBlockList.clear();
		Key.setCollectedPieces(0);
	}

	public Camera getCamera() {
		return camera;
	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

	public LevelSelector getLevelSelector() {
		return (LevelSelector) level;
	}

	public ArrayList<Platform> getPlatform() {
		return platList;
	}

	public ArrayList<Entity> getMonsterList() {
		return monsterList;
	}

	public ArrayList<PickUps> getPickUpsList() {
		return pickUpsList;
	}

	public ArrayList<DialogueBlock> getDialogueBlockList() {
		return dialogueBlockList;
	}

	public ArrayList<EventBlock> getEventBlockList() {
		return eventBlockList;
	}

	public ArrayList<Bullets> getBulletList() {
		return bulletList;
	}

	public ArrayList<StaticObjects> getStaticObjectList() {
		return staticObjectList;
	}

	public void setGameState(int a) {
		gameState = a;
	}

	public Transaction getTransaction() {
		return trans;
	}

	public int getGameState() {
		return gameState;
	}

	public LevelDesigner getLevelDesigner() {
		return levelDesigner;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public StoreWorld getStoreWorld() {
		return storeWorld;
	}

	public Display getDisplay() {
		return display;
	}
}
