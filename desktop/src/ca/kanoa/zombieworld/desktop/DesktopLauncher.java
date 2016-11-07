package ca.kanoa.zombieworld.desktop;

import ca.kanoa.zombieworld.Config;
import ca.kanoa.zombieworld.desktop.input.KeyboardController;
import ca.kanoa.zombieworld.desktop.input.MouseController;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ca.kanoa.zombieworld.ZombieWorldGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Config.TITLE;
		new LwjglApplication(new ZombieWorldGame(new KeyboardController()), config);
	}
}
