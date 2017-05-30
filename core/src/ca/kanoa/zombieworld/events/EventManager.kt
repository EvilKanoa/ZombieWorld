package ca.kanoa.zombieworld.events

import java.lang.reflect.Method
import java.util.*

class EventManager {

    private val handlers: HashMap<Class<out Event>, TreeSet<EventHandlerContainer>>
    private val comparator: EventHandlerComparator

    init {
        handlers = HashMap<Class<out Event>, TreeSet<EventHandlerContainer>>()
        comparator = EventHandlerComparator()
    }

    @Throws(EventRegistrationExeception::class)
    fun register(handler: EventListener) {
        for (method in handler.javaClass.methods) {
            if (method.getAnnotation(EventHandler::class.java) != null) {
                // check for errors in event handler method
                if (method.parameterTypes.size != 1) {
                    throw EventRegistrationExeception(handler, "invalid number of parameters, should be 1")
                } else if (!Event::class.java.isAssignableFrom(method.parameterTypes[0])) {
                    throw EventRegistrationExeception(handler, "handler parameter is not of the event type")
                } else {
                    registerEvent(method.parameterTypes[0] as Class<out Event>, method, handler)
                }
            }
        }
    }

    private fun registerEvent(event: Class<out Event>, method: Method, listener: EventListener) {
        if (!handlers.containsKey(event)) {
            handlers.put(event, TreeSet(comparator))
        }
        handlers[event]!!.add(EventHandlerContainer(method, listener))
    }

    fun dispatchEvent(event: Event): Int {
        if (handlers.containsKey(event.javaClass)) {
            var failures = 0
            val eventHandlers: Set<EventManager.EventHandlerContainer> = handlers[event.javaClass] ?: Collections.emptySet()
            for (eventHandler in eventHandlers) {
                try {
                    eventHandler.method.invoke(eventHandler.listener, event)
                } catch (e: Exception) {
                    e.printStackTrace()
                    failures++
                }

            }
            return failures
        } else {
            return -1
        }
    }

    class EventHandlerContainer constructor(val method: Method, val listener: EventListener)

}
