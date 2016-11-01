package ca.kanoa.zombieworld.events;

public enum EventPriority {

    LOG(10),
    HIGH(8),
    DEFAULT(5),
    LOW(3);

    private int level;

    EventPriority(int level) {
        this.level = level;
    }

}
