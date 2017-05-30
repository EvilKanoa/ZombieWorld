package ca.kanoa.zombieworld.desktop.input

import ca.kanoa.zombieworld.input.BaseController
import ca.kanoa.zombieworld.input.ControllerAction
import com.badlogic.gdx.math.Vector2
import java.util.*

open class KeyboardController : BaseController() {

    private val bindings = KeyBindings()

    override fun getMovementDirection(): Vector2 = keyDirection("up", "down", "left", "right").nor()

    override fun getShootDirection(): Vector2 = keyDirection("aim_up", "aim_down", "aim_left", "aim_right").nor()

    override fun isMoving(): Boolean = getMovementDirection().len2() != 0f

    override fun isShooting(): Boolean = getShootDirection().len2() != 0f

    override fun getActions(): List<ControllerAction> = Collections.emptyList()

    override fun removeAction(action: ControllerAction) {

    }

    override fun render() {

    }

    override fun dispose() {

    }

    override fun update(delta: Long) {

    }

    private fun keyDirection(up: String, down: String, left: String, right: String): Vector2 {
        val direction = Vector2()

        if (bindings.keyDown(up))
            direction.add(0f, 1f)
        if (bindings.keyDown(down))
            direction.add(0f, -1f)
        if (bindings.keyDown(left))
            direction.add(-1f, 0f)
        if (bindings.keyDown(right))
            direction.add(1f, 0f)

        return direction
    }

}
