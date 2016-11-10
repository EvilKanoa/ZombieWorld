package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.Gdx;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Sprite {
    Matrix4 projWorld;
    int vbo, ebo;

    ShaderProgram shader;

    Texture texture;

    public Sprite() {
        shader = ZombieWorldGame.getGame().shaderLoader.compile("spriteVS.glsl", "spriteFS.glsl");

        texture = new Texture("badlogic.jpg");

        projWorld = new Matrix4();
        projWorld = ZombieWorldGame.getGame().getOrthographicCamera().combined;

        float vertices[] = new float[]
                {-100.0f, -100.0f, 0.0f, 0.0f, 0.0f,
                 -100.0f, 100.0f, 0.0f, 0.0f, 1.0f,
                 100.0f, 100.0f, 0.0f, 1.0f, 1.0f,
                 100.0f, -100.0f, 0.0f, 1.0f, 0.0f};

        FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.flip();

        short indices[] = new short[] {0, 1, 2, 0, 2, 3};
        ShortBuffer indexBuffer = BufferUtils.newShortBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();

        vbo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(GL20.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8, vertexBuffer, Gdx.gl.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);

        ebo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, indices.length * Short.SIZE / 8, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void update() {
        ZombieWorldGame.getGame().getOrthographicCamera().translate(0.0f, 0.0f);
        projWorld = ZombieWorldGame.getGame().getOrthographicCamera().combined;
    }

    public void render() {
        shader.begin();

        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, ebo);

        texture.bind();

        shader.setVertexAttribute("inPosition", 3, GL20.GL_FLOAT, false, 20, 0);
        shader.setVertexAttribute("inTexCoord", 2, GL20.GL_FLOAT, false, 20, 12);
        shader.enableVertexAttribute("position");
        shader.enableVertexAttribute("inTexCoord");

        shader.setUniformMatrix4fv(shader.getUniformLocation("projWorld"), projWorld.getValues(), 0, 16);
        shader.setUniformi(shader.getUniformLocation("texture"), 0);

        Gdx.gl.glDrawElements(GL20.GL_TRIANGLES, 6, GL20.GL_UNSIGNED_SHORT, 0);

        shader.end();
    }

    public void dispose() {

    }
}
