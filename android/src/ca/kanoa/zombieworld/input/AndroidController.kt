package ca.kanoa.zombieworld.input

import ca.kanoa.zombieworld.utilities.Size
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import java.util.*

private val STICK_SIZE = 3.0f // the size of the sticks in centimeters

class AndroidController : BaseController() {
    private var moveIndex = -1
    private var shootIndex = -1

    override fun getMovementDirection(): Vector2 {
        if (isMoving()) {
            val movement = Vector2((getX(moveIndex) - Size.getWidthCenti(STICK_SIZE) / 2) / Size.getWidthCenti(STICK_SIZE / 2), (getY(moveIndex) - Size.getHeightCenti(STICK_SIZE) / 2) / Size.getHeightCenti(STICK_SIZE / 2))
            if (movement.len() > 1) {
                movement.nor()
            }
            return movement
        } else {
            return Vector2(0f, 0f)
        }
    }

    override fun getShootDirection(): Vector2 {
        if (isShooting()) {
            return Vector2((getX(shootIndex) - (Size.getScreenWidth() - Size.getWidthCenti(STICK_SIZE) / 2)) / Size.getWidthCenti(STICK_SIZE / 2),
                    (getY(shootIndex) - Size.getHeightCenti(STICK_SIZE) / 2) / Size.getHeightCenti(STICK_SIZE / 2)).nor()
        } else {
            return Vector2(0f, 0f)
        }
    }

    override fun isMoving(): Boolean {
        if (moveIndex != -1 && Gdx.input.isTouched(moveIndex)) {
            return true
        } else {
            moveIndex = -1
            return false
        }
    }

    override fun isShooting(): Boolean {
        if (shootIndex != -1 && Gdx.input.isTouched(shootIndex)) {
            return true
        } else {
            shootIndex = -1
            return false
        }
    }

    override fun getActions(): List<ControllerAction> = Collections.emptyList()

    override fun removeAction(action: ControllerAction) {

    }

    override fun render() {

    }

    override fun dispose() {

    }

    override fun update(delta: Long) {

    }

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        if (x < Size.getWidthCenti(STICK_SIZE) && Size.convertY(y.toFloat()) < Size.getHeightCenti(STICK_SIZE) &&
                moveIndex == -1) { // check if inside movement stick
            moveIndex = pointer
        } else if (x > Size.getScreenWidth() - Size.getWidthCenti(STICK_SIZE) &&
                Size.convertY(y.toFloat()) < Size.getHeightCenti(STICK_SIZE) && shootIndex == -1) { // check if inside shoot stick
            shootIndex = pointer
        }
        return true
    }

    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        if (pointer == moveIndex) {
            moveIndex = -1
        } else if (pointer == shootIndex) {
            shootIndex = -1
        }
        return true
    }

    fun getX(pointer: Int): Float = Gdx.input.getX(pointer).toFloat()

    fun getY(pointer: Int): Float = (Gdx.graphics.height - Gdx.input.getY(pointer)).toFloat()

}
