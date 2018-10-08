import game.Main;
import tools.APICalls;
import tools.network.HttpCallBack;
import tools.network.HttpClient;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) {

        Main game = new Main("RectGame", 650, 450);
        game.start();

    }
}