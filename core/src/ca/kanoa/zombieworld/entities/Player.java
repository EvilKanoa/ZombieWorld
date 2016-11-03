package ca.kanoa.zombieworld.entities;

import ca.kanoa.zombieworld.ZombieWorldGame;
import ca.kanoa.zombieworld.events.EventListener;
import com.badlogic.gdx.Gdx;

public class Player extends Entity implements EventListener {

    public Player() {
        super(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void render() {

    }

    @Override
    public void update(long delta) {
        if (ZombieWorldGame.getGame().controller.isMoving()) {
            getPosition().add(ZombieWorldGame.getGame().controller.getMovementDirection().scl(delta));
        }
    }

}
