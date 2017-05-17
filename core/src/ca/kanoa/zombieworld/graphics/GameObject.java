package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.Drawable;
import ca.kanoa.zombieworld.events.AssetsLoadedEvent;
import ca.kanoa.zombieworld.events.EventHandler;
import ca.kanoa.zombieworld.events.EventListener;
import ca.kanoa.zombieworld.utilities.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class GameObject implements Drawable, EventListener {
    private Matrix4 translation;
    private Matrix4 scale;
    private Matrix4 rotation;
    private Matrix4 world;
    private Matrix4 wvp; // world view projection matrix

    ShaderProgram shader;

    public ModelInstance modelInstance;

    public GameObject(ModelInstance modelInstance) {
        ZombieWorldGame.getGame().registerEventListener(this);

        this.modelInstance = new ModelInstance(modelInstance);
    }

    //@Override
    public void update(long delta, Vector3 translation, Vector3 scale) {
        this.translation.setToTranslation(translation);
        this.scale.setToScaling(scale);
        rotation.setToRotationRad(new Vector3(0.0f, 1.0f, 0.0f), 0.0f);

        world.idt();
        //world = translation.mul(scale).mul(rotation);
        world.set(this.translation);
        world.mul(this.scale);
        wvp.set(ZombieWorldGame.getGame().getPerspectiveCamera().projection);
        wvp.mul(ZombieWorldGame.getGame().getPerspectiveCamera().view);
        wvp.mul(world);
    }

    @Override
    public void render() {

    }
    /*
    @EventHandler
    public void onAssetsLoaded(AssetsLoadedEvent event) {
        modelInstance = loadModel(ModelAsset.ZOMBIE_1);
    }
    */
    @Override
    public void dispose() {

    }
}
    /*
    private Matrix4 translation;
    private Matrix4 scale;
    private Matrix4 rotation;
    private Matrix4 world;
    private Matrix4 wvp; // world view projection matrix

    private int vbo, ebo;

    private Vertex vertices[];
    private short indices[];

    ShaderProgram shader;

    ModelInstance zombie;
    ModelInstance cube;

    Texture texture;

    public GameObject() {
        shader = ZombieWorldGame.getGame().shaderLoader.compile("cubeVS.glsl", "cubeFS.glsl");

        ZombieWorldGame.getGame().registerEventListener(this);

        texture = new Texture("badlogic.jpg");

        translation = new Matrix4();
        scale = new Matrix4();
        rotation = new Matrix4();
        world = new Matrix4();
        wvp = new Matrix4();

        // pos.x, pos.y, pos.z, nor.x, nor.y, nor.z, tex.x, tex.y
        vertices = new Vertex[8];
        vertices[0] = new Vertex(-100.0f, -100.0f, 200.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
        vertices[1] = new Vertex( 100.0f, -100.0f, 200.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        vertices[2] = new Vertex( 100.0f,  100.0f, 200.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f);
        vertices[3] = new Vertex(-100.0f,  100.0f, 200.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f);
        vertices[4] = new Vertex(-100.0f, -100.0f, 400.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
        vertices[5] = new Vertex( 100.0f, -100.0f, 400.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        vertices[6] = new Vertex( 100.0f,  100.0f, 400.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f);
        vertices[7] = new Vertex(-100.0f,  100.0f, 400.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f);

        FloatBuffer vertexBuffer = BufferUtils.newFloatBuffer(vertices.length * 8);
        for (int i = 0; i < vertices.length; i++)
            vertexBuffer.put(vertices[i].getFloats());
        vertexBuffer.flip();

        indices = new short[] {
                0, 1, 2,
                0, 2, 3,
                1, 2, 6,
                1, 5, 6,
                0, 4, 3,
                4, 3, 7,
                0, 1, 5,
                0, 5, 4,
                2, 3, 6,
                3, 6, 7,
                4, 5, 7,
                5, 6, 7
        };

        ShortBuffer indexBuffer = BufferUtils.newShortBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();

        vbo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBufferData(Gdx.gl.GL_ARRAY_BUFFER, vertices.length * 8 * Float.SIZE / 8, vertexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, 0);

        ebo = Gdx.gl.glGenBuffer();
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);
        Gdx.gl.glBufferData(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, indices.length * Short.SIZE / 8, indexBuffer, GL20.GL_STATIC_DRAW);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    //@Override
    public void update(long delta, Vector3 translation, Vector3 scale) {
        this.translation.setToTranslation(translation);
        this.scale.setToScaling(scale);
        rotation.setToRotationRad(new Vector3(0.0f, 1.0f, 0.0f), 0.0f);

        world.idt();
        //world = translation.mul(scale).mul(rotation);
        world.set(this.translation);
        world.mul(this.scale);
        wvp.set(ZombieWorldGame.getGame().getPerspectiveCamera().projection);
        wvp.mul(ZombieWorldGame.getGame().getPerspectiveCamera().view);
        wvp.mul(world);
    }

    @Override
    public void render() {
        shader.begin();

        Gdx.gl.glBindBuffer(Gdx.gl.GL_ARRAY_BUFFER, vbo);
        Gdx.gl.glBindBuffer(Gdx.gl.GL_ELEMENT_ARRAY_BUFFER, ebo);

        texture.bind();

        shader.setVertexAttribute("inPosition", 3, Gdx.gl.GL_FLOAT, false, 32, 0);
        shader.setVertexAttribute("inNormal", 3, Gdx.gl.GL_FLOAT, false, 32, 12);
        shader.setVertexAttribute("inTexCoord", 2, Gdx.gl.GL_FLOAT, false, 32, 24);
        shader.enableVertexAttribute("inPosition");
        shader.enableVertexAttribute("inNormal");
        shader.enableVertexAttribute("inTexCoord");

        shader.setUniformMatrix4fv(shader.getUniformLocation("wvp"), wvp.getValues(), 0, 16);
        shader.setUniformi(shader.getUniformLocation("texture"), 0);

        Gdx.gl.glDrawElements(Gdx.gl.GL_TRIANGLES, indices.length, Gdx.gl.GL_UNSIGNED_SHORT, 0);

        shader.end();
    }

    @EventHandler
    public void onAssetsLoaded(AssetsLoadedEvent event) {
        zombie = ZombieWorldGame.getGame().loadModel(ModelAsset.ZOMBIE_1);
        cube = ZombieWorldGame.getGame().loadModel(ModelAsset.CUBE_1);
        zombie.transform.scl(0.5f);
    }

    @Override
    public void dispose() {

    }
}
//*/
