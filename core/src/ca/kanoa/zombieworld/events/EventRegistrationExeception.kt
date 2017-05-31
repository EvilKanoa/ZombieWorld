package ca.kanoa.zombieworld.events

class EventRegistrationExeception(handler: EventListener, message: String) : Exception("Error while registering events for " + handler.javaClass.toString() + ": " + message)
