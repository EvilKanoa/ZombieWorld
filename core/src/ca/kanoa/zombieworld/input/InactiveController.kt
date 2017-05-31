package ca.kanoa.zombieworld.input

import com.badlogic.gdx.math.Vector2
import java.util.*

class InactiveController : BaseController() {

    override fun getMovementDirection() = Vector2(0f, 0f)

    override fun getShootDirection() = Vector2(0f, 0f)

    override fun isMoving() = false

    override fun isShooting() = false

    override fun getActions(): List<ControllerAction> = Collections.emptyList()

    override fun removeAction(action: ControllerAction) {

    }

    override fun render() {

    }

    override fun dispose() {

    }

    override fun update(delta: Long) {

    }

}
