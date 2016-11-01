package ca.kanoa.zombieworld.input;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class AndroidController implements BaseController {
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
}
