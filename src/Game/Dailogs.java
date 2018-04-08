package Game;

import assets.Assets;
import java.awt.Color;
import java.awt.Graphics;

public class Dailogs {
	public static String dailog = "This is a tutorial World\nComplete the tutorial to continue";
	private static String[] dailog_lines;
	public static String speaker = "Creator";
	public static boolean show = false;
	private static Main game;

	public static void init(Main game) {
		game = game;
	}

	public static void toggle() {
		if (show) {
			show = false;
		} else {
			show = true;
		}
	}

	public static boolean isVisible() {
		return show;
	}

	public static void tick() {

	}

	public static void render(Graphics g) {
		if (show) {
			g.setColor(new Color(1.0F, 1.0F, 1.0F, 0.7F));
			g.fillRect(20, 150, 610, 150);
			g.setColor(Color.BLACK);
			g.drawRect(20, 150, 610, 150);
			g.setFont(Assets.dailog_speaker_font);
			g.drawString(speaker + " :", 30, 170);
			g.setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
			dailog_lines = dailog.split("\n");
			for (int i = 0; i < dailog_lines.length; i++) {
				g.drawString(dailog_lines[i], 35, 190 + i * 20);
			}
		}
	}
}
