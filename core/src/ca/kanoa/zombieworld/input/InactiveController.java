package ca.kanoa.zombieworld.input;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class InactiveController implements BaseController {

    @Override
    public Vector2 getMovementDirection() {
        return new Vector2(0, 0);
    }

    @Override
    public Vector2 getShootDirection() {
        return new Vector2(0, 0);
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
        return new ArrayList<ControllerAction>(0);
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
