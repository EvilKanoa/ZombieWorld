package ca.kanoa.zombieworld.input;

import ca.kanoa.zombieworld.Drawable;
import ca.kanoa.zombieworld.Updateable;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public interface BaseController extends Drawable, Updateable {

    /**
     * If the user is attempting to move the player, this determines the correct direction and speed
     * @return The direction and speed to move the player
     */
    Vector2 getMovementDirection();

    /**
     * If the user is attempting to shoot, this determines the correct direction
     * @return The direction to shoot in as a normalized vector
     */
    Vector2 getShootDirection();

    /**
     * @return Whether the player is moving or not
     */
    boolean isMoving();

    /**
     * @return Whether is player is shooting or not
     */
    boolean isShooting();

    /**
     * All pending actions that are not involved in the direct control of the character. i.e., reloads
     * @return A list of all unhandled actions
     */
    List<ControllerAction> getActions();

    /**
     * Removes an action from the action queue (should be called when an action is dealt with)
     * @param action The action to remove
     */
    void removeAction(ControllerAction action);

}
