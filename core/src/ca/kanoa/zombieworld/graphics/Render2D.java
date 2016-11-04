package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.Drawable;
import ca.kanoa.zombieworld.Updateable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.BufferUtils;

import java.util.LinkedList;
import java.util.List;

import static com.badlogic.gdx.Gdx.gl;

public class Render2D implements Drawable, Updateable {

    private OrthographicCamera camera;
    private ShaderProgram triangleShader;
    private ShaderProgram circleShader;
    private int vbo;
    /**
     * List of 2d triangles to draw, array formatted like so: {xPos, yPos, length, height, r, g, b, a}
     */
    private List<Float[]> triangleQueue;
    /**
     * List of 2d circles to draw, array formatted like so: {xPos, yPos, radius, r, g, b, a}
     */
    private List<Float[]> circleQueue;

    public Render2D() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        triangleShader = ShaderLoader.compile("2dVS.glsl", "triangle2dFS.glsl");
        circleShader = ShaderLoader.compile("2dVS.glsl", "circle2dFS.glsl");
        triangleQueue = new LinkedList<Float[]>();
        circleQueue = new LinkedList<Float[]>();
        vbo = gl.glGenBuffer();
    }

    // TODO: Implement priority and layers
    @Override
    public void render() {
        // draw triangles
        triangleShader.begin();
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, vbo);

        // generate data
        float[] vertices = new float[10 * triangleQueue.size()];
        int i = 0;
        for (Float[] data : triangleQueue) {
            vertices[i++] = data[0];
            vertices[i++] = data[1];
            vertices[i++] = data[0] + data[2];
            vertices[i++] = data[1];
            vertices[i++] = data[0];
            vertices[i++] = data[1] + data[3];
            vertices[i++] = data[4];
            vertices[i++] = data[5];
            vertices[i++] = data[6];
            vertices[i++] = data[7];
        }
        gl.glBufferData(gl.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8, BufferUtils.newFloatBuffer(vertices.length * Float.SIZE / 8).put(vertices).flip(), gl.GL_DYNAMIC_DRAW);

        // draw triangles
        //triangleShader.setUniform2fv("position",);

        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, 0);
        triangleShader.end();

        // draw circles
        circleShader.begin();
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, vbo);
        for (Float[] data : circleQueue) {

        }
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, 0);
        circleShader.end();

        // clear queues
        triangleQueue.clear();
        circleQueue.clear();
    }

    @Override
    public void update(long delta) { }


    public void drawCircle(float x, float y, float radius, Color color) {
        circleQueue.add(new Float[]{x, y, radius, color.r, color.g, color.b, color.a});
    }

    public void drawTriangle(float x, float y, float length, float height, Color color) {
        triangleQueue.add(new Float[]{x, y, length, height, color.r, color.g, color.b, color.a});
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
