package ca.kanoa.zombieworld.events;

import java.lang.reflect.Method;
import java.util.Comparator;

public class EventHandlerComparator implements Comparator<EventManager.EventHandlerContainer> {

    @Override
    public int compare(EventManager.EventHandlerContainer m1, EventManager.EventHandlerContainer m2) {
        EventHandler p1, p2;
        if ((p1 = m1.getMethod().getAnnotation(EventHandler.class)) != null &&
                (p2 = m2.getMethod().getAnnotation(EventHandler.class)) != null) {
            return -1 * p1.eventPriority().compareTo(p2.eventPriority());
        } else {
            return 0;
        }
    }

}
