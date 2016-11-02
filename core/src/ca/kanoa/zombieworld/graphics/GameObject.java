package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.Drawable;
import ca.kanoa.zombieworld.Updateable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GameObject implements Updateable, Drawable {
    Matrix4 translation;
    Matrix4 scale;
    Matrix4 rotation;
    Matrix4 world;
    //Matrix4 WVP; // world view projection matrix

    Vertex vertices[] = new Vertex[4];
    int indices[];
    int vbo, ebo;
    FloatBuffer vertexBuffer;
    IntBuffer indexBuffer;

    ShaderProgram shader;

    Texture texture;

    public GameObject() {
        shader = ShaderLoader.compile("vs.glsl", "ps.glsl");

        texture = new Texture("badlogic.jpg");

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
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(GL20.GL_ARRAY_BUFFER, vertices.length, vertexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);

        ebo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, indices.length, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, 0);

        //Gdx.gl30.glBindVertexArray(0);
    }

    @Override
    public void update(long delta) {
        translation.idt();
        scale.idt();
        rotation.idt();
        //translation.setToTranslation(0.0f, 0.0f, 0.0f);
        //scale.setToScaling(1.0f, 1.0f, 1.0f);
        //rotation.setToRotationRad(new Vector3(0.0f, 1.0f, 0.0f), 0.0f);

        //world.idt();
        //world = translation.mul(scale).mul(rotation);
        //WVP = world.mul(ZombieWorldGame.getGame().getCamera().view).mul(ZombieWorldGame.getGame().getCamera().projection);
    }

    @Override
    public void render() {
        shader.begin();

        // Position attribute
        Gdx.gl.glVertexAttribPointer(0, 3, Gdx.gl.GL_FLOAT, false, 8 * 4, 0);
        Gdx.gl.glEnableVertexAttribArray(0);
        // Position attribute
        Gdx.gl.glVertexAttribPointer(1, 3, Gdx.gl.GL_FLOAT, false, 8 * 4, 3 * 4);
        Gdx.gl.glEnableVertexAttribArray(1);
        // TexCoord attribute
        Gdx.gl.glVertexAttribPointer(2, 2, Gdx.gl.GL_FLOAT, false, 5 * 4, 6 * 4);
        Gdx.gl.glEnableVertexAttribArray(2);

        int worldLoc = shader.getUniformLocation("world");
        int viewLoc = shader.getUniformLocation("view");
        int projLoc = shader.getUniformLocation("projection");

        Gdx.gl.glActiveTexture(Gdx.gl.GL_TEXTURE0);
        Gdx.gl.glBindTexture(Gdx.gl.GL_TEXTURE_2D, texture.getTextureObjectHandle());
        Gdx.gl.glUniform1i(shader.getUniformLocation("ourTexture1"), 0);

        // may or may not need to transpose matrices, may or may not need to offset
        Gdx.gl.glUniformMatrix4fv(worldLoc, 1, false, world.getValues(), 0);
        Gdx.gl.glUniformMatrix4fv(viewLoc, 1, false, ZombieWorldGame.getGame().getCamera().view.getValues(), 0);
        Gdx.gl.glUniformMatrix4fv(viewLoc, 1, false, ZombieWorldGame.getGame().getCamera().projection.getValues(), 0);

        Gdx.gl.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, vbo);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);

        Gdx.gl.glDrawElements(Gdx.gl.GL_TRIANGLES, 6, Gdx.gl.GL_INT, 0);

        shader.end();
    }
}
