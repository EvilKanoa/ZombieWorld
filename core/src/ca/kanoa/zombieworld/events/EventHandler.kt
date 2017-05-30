package ca.kanoa.zombieworld.events

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
annotation class EventHandler(val eventPriority: EventPriority = EventPriority.DEFAULT, val ignoreCancelled: Boolean = true)
