package ca.kanoa.zombieworld.input;

import ca.kanoa.zombieworld.utilities.Size;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class AndroidController extends BaseController {


    private static final float STICK_SIZE = 3.0f; // the size of the sticks in centimeters
    private int moveIndex = -1, shootIndex = -1;

    @Override
    public Vector2 getMovementDirection() {
        if (isMoving()) {
            Vector2 movement = new Vector2((getX(moveIndex) - Size.getWidthCenti(STICK_SIZE) / 2) /
                    Size.getWidthCenti(STICK_SIZE / 2), (getY(moveIndex) - Size.getHeightCenti(STICK_SIZE) / 2) /
                    Size.getHeightCenti(STICK_SIZE / 2));
            if (movement.len() > 1) {
                movement.nor();
            }
            return movement;
        } else {
            return new Vector2(0, 0);
        }
    }

    @Override
    public Vector2 getShootDirection() {
        if (isShooting()) {
            return new Vector2((getX(shootIndex) - (Size.getScreenWidth() - Size.getWidthCenti(STICK_SIZE) / 2)) / Size.getWidthCenti(STICK_SIZE / 2),
                    (getY(shootIndex) - Size.getHeightCenti(STICK_SIZE) / 2) / Size.getHeightCenti(STICK_SIZE / 2)).nor();
        } else {
            return new Vector2(0, 0);
        }
    }

    @Override
    public boolean isMoving() {
        if (moveIndex != -1 && Gdx.input.isTouched(moveIndex)) {
            return true;
        } else {
            moveIndex = -1;
            return false;
        }
    }

    @Override
    public boolean isShooting() {
        if (shootIndex != -1 && Gdx.input.isTouched(shootIndex)) {
            return true;
        } else {
            shootIndex = -1;
            return false;
        }
    }

    @Override
    public List<ControllerAction> getActions() {
        return null;
    }

    @Override
    public void removeAction(ControllerAction action) {

    }

    @Override
    public void render() {

    }

    @Override
    public void update(long delta) {

    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        if (x < Size.getWidthCenti(STICK_SIZE) && Size.convertY(y) < Size.getHeightCenti(STICK_SIZE) &&
                moveIndex == -1) { // check if inside movement stick
            moveIndex = pointer;
        } else if (x > (Size.getScreenWidth() - Size.getWidthCenti(STICK_SIZE)) &&
                Size.convertY(y) < Size.getHeightCenti(STICK_SIZE) && shootIndex == -1) { // check if inside shoot stick
            shootIndex = pointer;
        }
        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        if (pointer == moveIndex) {
            moveIndex = -1;
        } else if (pointer == shootIndex) {
            shootIndex = -1;
        }
        return true;
    }

    public float getX(int pointer) {
        return Gdx.input.getX(pointer);
    }

    public float getY(int pointer) {
        return Gdx.graphics.getHeight() - Gdx.input.getY(pointer);
    }

}
