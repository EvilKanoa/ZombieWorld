package ca.kanoa.zombieworld.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;

public class Camera {
    Matrix4 projection;
    Matrix4 view;
    Vector3 target = new Vector3(0.0f, 0.0f, -1.0f);
    Vector3 up = new Vector3(0.0f, 0.0f, 1.0f);

    public Camera(Vector2 position) {
        projection = new Matrix4();
        view = new Matrix4();
        projection.setToProjection(0.01f, 1000.0f, 90.0f, (float) (Gdx.graphics.getWidth()) / (float) (Gdx.graphics.getHeight()));
        view.setToLookAt(new Vector3(position.x, position.y, 100.0f), target, up);
    }

    public void update(Vector2 position) {
        view.setToLookAt(new Vector3(position.x, position.y, 100.0f), target, up);
    }
}
