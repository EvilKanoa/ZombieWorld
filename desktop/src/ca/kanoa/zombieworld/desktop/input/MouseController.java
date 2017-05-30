package ca.kanoa.zombieworld.desktop.input;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class MouseController extends KeyboardController {

    @Override
    public Vector2 getShootDirection() {
        if (ZombieWorldGame.Companion.getGame().getWorld() != null &&
                ZombieWorldGame.Companion.getGame().getWorld().getPlayer() != null && isShooting()) {
            return new Vector2(getX(), getY()).sub(ZombieWorldGame.Companion.getGame().getWorld().getPlayer().getPosition()).nor();
        } else {
            return new Vector2(0, 0);
        }
    }

    @Override
    public boolean isShooting() {
        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }

    public float getX() {
        return Gdx.input.getX();
    }

    public float getY() {
        return Gdx.graphics.getHeight() - Gdx.input.getY();
    }

}
