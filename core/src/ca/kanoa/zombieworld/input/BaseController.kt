package ca.kanoa.zombieworld.input

import ca.kanoa.zombieworld.Drawable
import ca.kanoa.zombieworld.Updateable
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.math.Vector2

abstract class BaseController : InputAdapter(), Drawable, Updateable {

    /**
     * If the user is attempting to move the player, this determines the correct direction and speed
     * @return The direction and speed to move the player
     */
    abstract fun getMovementDirection(): Vector2

    /**
     * If the user is attempting to shoot, this determines the correct direction
     * @return The direction to shoot in as a normalized vector
     */
    abstract fun getShootDirection(): Vector2

    /**
     * @return Whether the player is moving or not
     */
    abstract fun isMoving(): Boolean

    /**
     * @return Whether is player is shooting or not
     */
    abstract fun isShooting(): Boolean

    /**
     * All pending actions that are not involved in the direct control of the character. i.e., reloads
     * @return A list of all unhandled actions
     */
    abstract fun getActions(): List<ControllerAction>

    /**
     * Removes an action from the action queue (should be called when an action is dealt with)
     * @param action The action to remove
     */
    abstract fun removeAction(action: ControllerAction)

}
