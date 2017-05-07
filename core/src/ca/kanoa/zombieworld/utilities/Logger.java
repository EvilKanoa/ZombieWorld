package ca.kanoa.zombieworld.utilities;

import ca.kanoa.zombieworld.Config;
import ca.kanoa.zombieworld.ZombieWorldGame;
import ca.kanoa.zombieworld.events.LogEvent;
import com.badlogic.gdx.Gdx;

public class Logger {

    private static final String FORMAT_STRING = "{tag}-{level} [{source}]: {message}";
    private static Logger DEFAULT_LOGGER = null;

    private String source;

    public Logger(Class clazz) {
        this.source = clazz.getSimpleName();
    }

    public Logger(String source) {
        this.source = source;
    }

    public void debug(String message) {
        this.log(message, LogLevel.DEBUG);
    }

    public void info(String message) {
        this.log(message, LogLevel.INFO);
    }

    public void warning(String message) {
        this.log(message, LogLevel.WARNING);
    }

    public void error(String message) {
        this.log(message, LogLevel.ERROR);
    }

    public void log(String message, LogLevel level) {
        log(message, this.source, level);
    }

    public static String getOutput(String message, String source, LogLevel level) {
        return new String(FORMAT_STRING.toLowerCase()).replace("{tag}", Config.DEFAULT_TAG)
                .replace("{message}", message).replace("{source}", source).replace("{level}", level.toString());
    }

    public static void debug(String message, String source) {
        log(message, source, LogLevel.DEBUG);
    }

    public static void info(String message, String source) {
        log(message, source, LogLevel.INFO);
    }

    public static void warning(String message, String source) {
        log(message, source, LogLevel.WARNING);
    }

    public static void error(String message, String source) {
        log(message, source, LogLevel.ERROR);
    }

    public static void log(String message, String source, LogLevel level) {
        LogEvent event = new LogEvent(message, source, level);
        ZombieWorldGame.getGame().events.dispatchEvent(event);
        System.out.println(event.getOutput());
    }

    public enum LogLevel {
        DEBUG(10, "DEBUG"),
        INFO(20, "INFO"),
        WARNING(30, "WARNING"),
        ERROR(40, "ERROR");

        private int weight;
        private String tag;

        LogLevel(int weight, String tag) {
            this.weight = weight;
            this.tag = tag;
        }

        public static int compare(LogLevel l1, LogLevel l2) {
            return l1.weight - l2.weight;
        }

        @Override
        public String toString() {
            return this.tag;
        }
    }

}
