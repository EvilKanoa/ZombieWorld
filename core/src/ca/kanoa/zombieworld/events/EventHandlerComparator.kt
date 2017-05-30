package ca.kanoa.zombieworld.events

import java.util.Comparator

class EventHandlerComparator : Comparator<EventManager.EventHandlerContainer> {

    override fun compare(m1: EventManager.EventHandlerContainer, m2: EventManager.EventHandlerContainer): Int {
        try {
            val p1: EventHandler = m1.method.getAnnotation(EventHandler::class.java)
            val p2: EventHandler = m2.method.getAnnotation(EventHandler::class.java)
            return -1 * p1.eventPriority.compareTo(p2.eventPriority)
        } catch (exception: NullPointerException) {
            return 0
        }
    }

}
