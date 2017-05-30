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
    public void dispose() {

    }

    @Override
    public void update(long delta) {
        if (ZombieWorldGame.Companion.getGame().getController().isMoving()) {
            getPosition().add(ZombieWorldGame.Companion.getGame().getController().getMovementDirection().scl(delta));
        }
    }

}
