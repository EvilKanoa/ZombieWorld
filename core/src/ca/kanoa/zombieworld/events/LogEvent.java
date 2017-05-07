package ca.kanoa.zombieworld.events;

import ca.kanoa.zombieworld.utilities.Logger;

public class LogEvent extends Event {

    private String message;
    private String source;
    private Logger.LogLevel level;

    public LogEvent(String message, String source, Logger.LogLevel level) {
        this.message = message;
        this.source = source;
        this.level = level;
    }


    public String getMessage() {
        return message;
    }

    public String getSource() {
        return source;
    }

    public Logger.LogLevel getLogLevel() {
        return level;
    }

    public String getOutput() {
        return Logger.getOutput(message, source, level);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
