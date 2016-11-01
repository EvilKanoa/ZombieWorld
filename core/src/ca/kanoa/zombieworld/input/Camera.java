package ca.kanoa.zombieworld.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;

public class Camera {
    Matrix4 projection;
    Matrix4 view;
    Vector3 target = new Vector3(0.0f, 0.0f, 1.0f);
    Vector3 up = new Vector3(0.0f, 0.0f, -1.0f);

    public Camera(Vector2 position) {
        projection.setToOrtho(0.0f, Gdx.graphics.getWidth(), 0.0f, Gdx.graphics.getHeight(), 0.0f, 1.0f);
        view.setToLookAt(new Vector3(position.x, position.y, 0.0f), target, up);
    }

    public void update(Vector2 position) {
        view.setToLookAt(new Vector3(position.x, position.y, 0.0f), target, up);
    }
}
