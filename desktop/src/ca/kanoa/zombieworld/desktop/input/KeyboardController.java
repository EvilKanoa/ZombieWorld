package ca.kanoa.zombieworld.desktop.input;

import ca.kanoa.zombieworld.input.BaseController;
import ca.kanoa.zombieworld.input.ControllerAction;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class KeyboardController extends BaseController {

    private KeyBindings bindings;

    public KeyboardController() {
        this.bindings = new KeyBindings();
    }

    @Override
    public Vector2 getMovementDirection() {
        return keyDirection("up", "down", "left", "right").nor();
    }

    @Override
    public Vector2 getShootDirection() {
        return keyDirection("aim_up", "aim_down", "aim_left", "aim_right").nor();
    }

    @Override
    public boolean isMoving() {
        return getMovementDirection().len2() != 0;
    }

    @Override
    public boolean isShooting() {
        return getShootDirection().len2() != 0;
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
    public void dispose() {

    }

    @Override
    public void update(long delta) {

    }

    private Vector2 keyDirection(String up, String down, String left, String right) {
        Vector2 direction = new Vector2();

        if (bindings.keyDown(up))
            direction.add(0, 1);
        if (bindings.keyDown(down))
            direction.add(0, -1);
        if (bindings.keyDown(left))
            direction.add(-1, 0);
        if (bindings.keyDown(right))
            direction.add(1, 0);

        return direction;
    }

}
