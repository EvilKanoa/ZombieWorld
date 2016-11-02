package ca.kanoa.zombieworld.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GameObject {
    Matrix4 translation;
    Matrix4 scale;
    Matrix4 rotation;
    Matrix4 world;
    Matrix4 WVP; // world view projection matrix

    Vertex vertices[] = new Vertex[4];
    int indices[];
    int vbo, ebo;
    FloatBuffer vertexBuffer;
    IntBuffer indexBuffer;

    public GameObject() {
        vertices[0] = new Vertex(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        vertices[1] = new Vertex(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
        vertices[2] = new Vertex(1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        vertices[3] = new Vertex(0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f);

        vertexBuffer = BufferUtils.newFloatBuffer(vertices.length * 8);
        for (int i = 0; i < 4; i++)
            vertexBuffer.put(vertices[i].getFloats());
        vertexBuffer.flip();

        indices = new int[] {0, 1, 2, 0, 2, 3};
        indexBuffer = BufferUtils.newIntBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();

        vbo = Gdx.gl.glGenBuffer();
        ebo = Gdx.gl.glGenBuffer();

        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(GL20.GL_ARRAY_BUFFER, vertices.length, vertexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);

        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, indices.length, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);

        Gdx.gl30.glBindVertexArray(0);
    }

    public void update() {
        translation.setToTranslation(0.0f, 0.0f, 0.0f);
        scale.setToScaling(1.0f, 1.0f, 1.0f);
        rotation.setToRotationRad(new Vector3(0.0f, 1.0f, 0.0f), 0.0f);

        world = translation.mul(scale).mul(rotation);
        WVP = world.mul(ZombieWorldGame.getGame().getCamera().view).mul(ZombieWorldGame.getGame().getCamera().projection);
    }

    void render() {
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, vbo);
        Gdx.gl30.glEnableVertexAttribArray(0);
        Gdx.gl.glVertexAttribPointer(0, 3, Gdx.gl.GL_FLOAT, false, 0, 0);

        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glDrawElements(Gdx.gl.GL_TRIANGLES, 6, Gdx.gl.GL_INT, 0);
    }
}
