package ca.kanoa.zombieworld.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.TreeSet;

public class EventManager {

    private HashMap<Class<? extends Event>, TreeSet<EventHandlerContainer>> handlers;
    private EventHandlerComparator comparator;

    public EventManager() {
        handlers = new HashMap<Class<? extends Event>, TreeSet<EventHandlerContainer>>();
        comparator = new EventHandlerComparator();
    }

    public void register(EventListener handler) throws EventRegistrationExeception {
        for (Method method : handler.getClass().getMethods()) {
            if (method.getAnnotation(EventHandler.class) != null) {
                // check for errors in event handler method
                if (method.getParameterTypes().length != 1) {
                    throw new EventRegistrationExeception(handler, "invalid number of parameters, should be 1");
                } else if (!Event.class.isAssignableFrom(method.getParameterTypes()[0])) {
                    throw new EventRegistrationExeception(handler, "handler parameter is not of the event type");
                } else {
                    registerEvent((Class<? extends Event>) method.getParameterTypes()[0], method, handler);
                }
            }
        }
    }

    private void registerEvent(Class<? extends Event> event, Method method, EventListener listener) {
        if (!handlers.containsKey(event)) {
            handlers.put(event, new TreeSet<EventHandlerContainer>(comparator));
        }
        handlers.get(event).add(new EventHandlerContainer(method, listener));
    }

    public int dispatchEvent(Event event) {
        if (handlers.containsKey(event.getClass())) {
            int failures = 0;
            TreeSet<EventHandlerContainer> eventHandlers = handlers.get(event.getClass());
            for (EventHandlerContainer eventHandler : eventHandlers) {
                try {
                    eventHandler.getMethod().invoke(eventHandler.getListener(), event);
                } catch (Exception e) {
                    e.printStackTrace();
                    failures++;
                }
            }
            return failures;
        } else {
            return -1;
        }
    }

    protected static class EventHandlerContainer {
        private Method method;
        private EventListener listener;

        private EventHandlerContainer(Method method, EventListener listener) {
            this.method = method;
            this.listener = listener;
        }

        protected Method getMethod() {
            return method;
        }

        protected EventListener getListener() {
            return listener;
        }
    }

}
