package ca.kanoa.zombieworld.events;

public class EventRegistrationExeception extends Exception {

    public EventRegistrationExeception(EventListener handler, String message) {
        super("Error while registering events for " + handler.getClass().toString() + ": " + message);
    }

}
