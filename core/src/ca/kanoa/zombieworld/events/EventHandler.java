package ca.kanoa.zombieworld.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    EventPriority eventPriority() default EventPriority.DEFAULT;

    boolean ignoreCancelled() default true;

}
