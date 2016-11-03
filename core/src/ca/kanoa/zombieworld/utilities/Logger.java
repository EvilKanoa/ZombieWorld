package ca.kanoa.zombieworld.utilities;

import ca.kanoa.zombieworld.Config;
import ca.kanoa.zombieworld.ZombieWorldGame;
import ca.kanoa.zombieworld.events.LogEvent;
import com.badlogic.gdx.Gdx;

public class Logger {

    public static void log(String message) {
        LogEvent event = new LogEvent(message);
        ZombieWorldGame.getGame().events.dispatchEvent(event);
        Gdx.app.log(Config.DEFAULT_TAG, event.getMessage());
    }

}
