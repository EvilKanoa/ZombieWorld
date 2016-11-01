package ca.kanoa.zombieworld.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class AndroidController extends InputAdapter implements BaseController {


    private static final float STICK_SIZE = 2.0f; // the size of the sticks in centimeters
    private int moveIndex = -1, shootIndex = -1;

    @Override
    public Vector2 getMovementDirection() {
        return null;
    }

    @Override
    public Vector2 getShootDirection() {
        return null;
    }

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public boolean isShooting() {
        return false;
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
        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return true;
    }

    public float getX() {
        return Gdx.input.getX();
    }

    public float getY() {
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }
}
