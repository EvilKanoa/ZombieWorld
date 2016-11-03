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
    Matrix4 rotation;
    Matrix4 world;
    Matrix4 wvp; // world view projection matrix

    Vertex vertices[] = new Vertex[4];
    short indices[];
    int vbo, ebo;
    FloatBuffer vertexBuffer;
    ShortBuffer indexBuffer;

    ShaderProgram shader;

    Texture texture;

    public GameObject() {
        shader = ShaderLoader.compile("vs.glsl", "fs.glsl");

        texture = new Texture("badlogic.jpg");

        translation = new Matrix4();
        scale = new Matrix4();
        rotation = new Matrix4();
        world = new Matrix4();
        wvp = new Matrix4();

        vertices[0] = new Vertex(-100.0f, -100.0f, 1.0f/*, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f*/);
        vertices[1] = new Vertex(100.0f, -100.0f, 1.0f/*, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f*/);
        vertices[2] = new Vertex(100.0f, 100.0f, 1.0f/*, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f*/);
        vertices[3] = new Vertex(-100.0f, 100.0f, 1.0f/*, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f*/);

        //vertexBuffer = BufferUtils.newFloatBuffer(vertices.length * 8);
        vertexBuffer = BufferUtils.newFloatBuffer(12);
        for (int i = 0; i < 4; i++) {
            vertexBuffer.position(i * 3);
            vertexBuffer.put(vertices[i].getFloats());
        }
        vertexBuffer.flip();
        vertexBuffer.position(0);

        indices = new short[] {0, 1, 2, 0, 2, 3};
        indexBuffer = BufferUtils.newShortBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();
        indexBuffer.position(0);

        vbo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(Gdx.gl.GL_ARRAY_BUFFER, vertices.length * 3 * 4, vertexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, 0);

        ebo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, indices.length * 2, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, 0);


        //Gdx.gl30.glBindVertexArray(0);
    }

    @Override
    public void update(long delta) {
        translation.setToTranslation(0.0f, 0.0f, 0.0f);
        scale.setToScaling(1.0f, 1.0f, 1.0f);
        rotation.setToRotationRad(new Vector3(0.0f, 0.0f, 1.0f), 0.0f);

        world.idt();
        //world = translation.mul(scale).mul(rotation);
        world = translation.mul(scale);
        //wvp = world.mul(ZombieWorldGame.getGame().getCamera().view).mul(ZombieWorldGame.getGame().getCamera().projection);
        wvp = ZombieWorldGame.getGame().getCamera().projection.mul(ZombieWorldGame.getGame().getCamera().view).mul(world);
    }

    @Override
    public void render() {
        shader.begin();

        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, vbo);

        //int posLoc = shader.getAttributeLocation("position");
        //int normalLoc = shader.getAttributeLocation("inNormal");
        //int texCoordLoc = shader.getAttributeLocation("inTexCoord");


        shader.enableVertexAttribute("position");
        shader.setVertexAttribute("position", 3, Gdx.gl.GL_FLOAT, false, 0, 0);
        // Position attribute
        //Gdx.gl.glEnableVertexAttribArray("position");
        //Gdx.gl.glVertexAttribPointer(0, 3, Gdx.gl.GL_FLOAT, false, 0, 0);
        // Normal attribute
        //Gdx.gl.glEnableVertexAttribArray(normalLoc);
        //Gdx.gl.glVertexAttribPointer(normalLoc, 3, Gdx.gl.GL_FLOAT, false, 8 * 4, 0);
        // TexCoord attribute
        //Gdx.gl.glEnableVertexAttribArray(texCoordLoc);
        //Gdx.gl.glVertexAttribPointer(texCoordLoc, 2, Gdx.gl.GL_FLOAT, false, 8 * 4, 0);

        //int worldLoc = shader.getUniformLocation("world");
        //int viewLoc = shader.getUniformLocation("view");
        //int projLoc = shader.getUniformLocation("projection");
        int wvpLoc = shader.getUniformLocation("wvp");

        //Gdx.gl.glActiveTexture(Gdx.gl.GL_TEXTURE0);
        //Gdx.gl.glBindTexture(Gdx.gl.GL_TEXTURE_2D, texture.getTextureObjectHandle());
        //Gdx.gl.glUniform1i(shader.getUniformLocation("ourTexture1"), 0);

        // may or may not need to transpose matrices, may or may not need to offset
        //Gdx.gl.glUniformMatrix4fv(worldLoc, 1, false, world.getValues(), 0);
        //Gdx.gl.glUniformMatrix4fv(viewLoc, 1, false, ZombieWorldGame.getGame().getCamera().view.getValues(), 0);
        //Gdx.gl.glUniformMatrix4fv(projLoc, 1, false, ZombieWorldGame.getGame().getCamera().projection.getValues(), 0);
        Gdx.gl.glUniformMatrix4fv(wvpLoc, 1, false, wvp.getValues(), 0);

        float floats[] = new float[12];
        vertexBuffer.get(floats);
        System.out.println(floats);

        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);

        Gdx.gl.glDrawElements(Gdx.gl.GL_TRIANGLES, 6, Gdx.gl.GL_SHORT, 0);
        //Gdx.gl.glDrawArrays(Gdx.gl.GL_TRIANGLES, 0, 6);
        //Gdx.gl.glDrawElements(Gdx.gl.GL_TRIANGLES, 6, Gdx.gl.GL_UNSIGNED_SHORT, 0);

        shader.end();
    }
}
