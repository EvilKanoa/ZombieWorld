package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.Gdx;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Jonathan on 2016-11-03.
 */
public abstract class Render2D {
    Matrix4 projWorld;
    private int vbo, ebo, numIndices;

    ShaderProgram shader; // main shader holder

    void setVertices(float vertices[]) {
        FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.flip();

        vbo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(GL20.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8, vertexBuffer, Gdx.gl.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
    }

    void setIndices(short indices[]) {
        numIndices = indices.length;
        ShortBuffer indexBuffer = BufferUtils.newShortBuffer(numIndices);
        indexBuffer.put(indices);
        indexBuffer.flip();

        ebo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, numIndices * Short.SIZE / 8, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public Render2D() {
        //shader = ZombieWorldGame.getGame().shaderLoader.compile("spriteVS.glsl", "spriteFS.glsl");

        projWorld = new Matrix4();
        projWorld = ZombieWorldGame.getGame().getOrthographicCamera().combined;

        /*
        float vertices[] = new float[]
                {-100.0f, -100.0f, 0.0f, 0.0f, 0.0f,
                 -100.0f, 100.0f, 0.0f, 0.0f, 1.0f,
                 100.0f, 100.0f, 0.0f, 1.0f, 1.0f,
                 100.0f, -100.0f, 0.0f, 1.0f, 0.0f};
        */


    }

    public void update() {
        ZombieWorldGame.getGame().getOrthographicCamera().translate(0.0f, 0.0f);
        projWorld = ZombieWorldGame.getGame().getOrthographicCamera().combined;
    }

    abstract void setShaderVariables();

    public void render() {
        shader.begin();

        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, ebo);

        setShaderVariables();
        shader.setUniformMatrix4fv(shader.getUniformLocation("projWorld"), projWorld.getValues(), 0, 16);

        Gdx.gl.glDrawElements(GL20.GL_TRIANGLES, numIndices, GL20.GL_UNSIGNED_SHORT, 0);

        shader.end();
    }

    public void dispose() {

    }
}
