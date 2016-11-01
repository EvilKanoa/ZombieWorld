package ca.kanoa.zombieworld.entities;

import ca.kanoa.zombieworld.Drawable;
import ca.kanoa.zombieworld.Updateable;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity implements Drawable, Updateable {

    private Vector2 position;
    private Vector2 direction;

    public Entity(float x, float y) {
        this.position = new Vector2(x, y);
        this.direction = new Vector2(0, 1);
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getDirection() {
        return this.getDirection();
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void setDirection(float x, float y) {
        direction.set(x, y);
        direction.nor();
    }

}
