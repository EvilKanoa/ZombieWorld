package ca.kanoa.zombieworld.events;

public class LogEvent extends Event {

    private String message;

    public LogEvent(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
