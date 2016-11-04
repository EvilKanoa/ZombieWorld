package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.Drawable;
import ca.kanoa.zombieworld.Updateable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class GameObject implements Updateable, Drawable {
    Matrix4 translation;
    Matrix4 scale;
    public Matrix4 rotation;
    Matrix4 world;
    Matrix4 wvp; // world view projection matrix

    int vbo, ebo;

    ShaderProgram shader;

    Texture texture;

    public GameObject() {
        shader = ShaderLoader.compile("cubeVS.glsl", "cubeFS.glsl");

        //texture = new Texture("badlogic.jpg");

        translation = new Matrix4();
        scale = new Matrix4();
        rotation = new Matrix4();
        world = new Matrix4();
        wvp = new Matrix4();

        Vertex vertices[] = new Vertex[4];
        vertices[0] = new Vertex(-10.0f, 0.0f, -10.0f/*, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f*/);
        vertices[1] = new Vertex(10.0f, 0.0f, -10.0f/*, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f*/);
        vertices[2] = new Vertex(10.0f, 0.0f, 10.0f/*, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f*/);
        vertices[3] = new Vertex(0.0f, 100.0f, 0.0f/*, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f*/);

        //vertexBuffer = BufferUtils.newFloatBuffer(vertices.length * 8);
        FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(12);
        for (int i = 0; i < 4; i++)
            vertexBuffer.put(vertices[i].getFloats());
        vertexBuffer.flip();

        short indices[] = new short[] {0, 1, 2, 0, 1, 3, 1, 2, 3, 2, 0 ,3};
        ShortBuffer indexBuffer = BufferUtils.newShortBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();

        vbo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(Gdx.gl.GL_ARRAY_BUFFER, vertices.length * 3 * 4, vertexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, 0);

        ebo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, indices.length * 2, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void update(long delta) {
        translation.setToTranslation(0.0f, 0.0f, 0.0f);
        scale.setToScaling(1.0f, 1.0f, 1.0f);
        rotation.setToRotationRad(new Vector3(0.0f, 1.0f, 0.0f), 0.0f);

        world.idt();
        //world = translation.mul(scale).mul(rotation);
        world = translation.mul(scale);
        //wvp = world.mul(ZombieWorldGame.getGame().getOrthographicCamera().view).mul(ZombieWorldGame.getGame().getOrthographicCamera().projection);
        wvp = ZombieWorldGame.getGame().getPerspectiveCamera().invProjectionView;
    }

    @Override
    public void render() {
        shader.begin();

        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);

        shader.setVertexAttribute("position", 3, Gdx.gl.GL_FLOAT, false, 12, 0);
        shader.enableVertexAttribute("position");

        shader.setUniformMatrix4fv("wvp", wvp.getValues(), 0, 16);

        Gdx.gl.glDrawElements(Gdx.gl.GL_TRIANGLES, 12, Gdx.gl.GL_UNSIGNED_SHORT, 0);

        shader.end();
    }
}
