package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class PerspectiveCamera {
    public com.badlogic.gdx.graphics.PerspectiveCamera perspectiveCamera;
    private float speed;
    private float angle;

    public PerspectiveCamera() {
        speed = 0.1f;
        angle = -70.0f;
        perspectiveCamera = new com.badlogic.gdx.graphics.PerspectiveCamera(60.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.near = 0.1f;
        perspectiveCamera.far = 10000.0f;
        perspectiveCamera.position.set(0.0f, 50.0f, -10.0f);
        perspectiveCamera.lookAt(0, 0, 0);
        perspectiveCamera.update(true);
        perspectiveCamera.direction.set(0.0f, MathUtils.sinDeg(angle), MathUtils.cosDeg(angle));
        perspectiveCamera.up.set(0.0f, 1.0f, 0.0f);
    }

    public void update() {
        perspectiveCamera.direction.set(0.0f, MathUtils.sinDeg(angle), MathUtils.cosDeg(angle));
        if (perspectiveCamera.direction.y > 0.0f) perspectiveCamera.direction.y = 0.0f;

        perspectiveCamera.position.add(ZombieWorldGame.getGame().controller.getMovementDirection().x * speed, 0, ZombieWorldGame.getGame().controller.getMovementDirection().y * speed);
        perspectiveCamera.update();
    }
}
