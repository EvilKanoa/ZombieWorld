package ca.kanoa.zombieworld;

import ca.kanoa.zombieworld.events.AssetsLoadedEvent;
import ca.kanoa.zombieworld.events.EventListener;
import ca.kanoa.zombieworld.events.EventManager;
import ca.kanoa.zombieworld.events.EventRegistrationExeception;
import ca.kanoa.zombieworld.files.Settings;
import ca.kanoa.zombieworld.graphics.*;
import ca.kanoa.zombieworld.graphics.ModelAsset;
import ca.kanoa.zombieworld.graphics.PerspectiveCamera;
import ca.kanoa.zombieworld.input.BaseController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.PerformanceCounter;

import java.util.Arrays;

public class ZombieWorldGame extends OrganizedApplicationAdapter {

    SpriteBatch batch;
    //Texture img;

    GameObject pizza;
    TexturedSprite texturedSprite;

    public EventManager events = new EventManager();
    public BaseController controller;
    public GameWorld world;
    public ShaderLoader shaderLoader;

    private static ZombieWorldGame _instance;
    private long lastUpdate, delta;

    private PerspectiveCamera perspectiveCamera;
    private OrthographicCamera orthographicCamera;
    private ModelBatch modelBatch;
    private Array<ModelInstance> modelInstances;
    private AssetManager assets;
    private boolean assetsLoaded;

    public ZombieWorldGame(BaseController controller, ShaderLoader shaderLoader) {
        this.controller = controller;
        this.shaderLoader = shaderLoader;
    }

    @Override
    public void create() {
        _instance = this;
        Settings.loadFile(Config.SETTINGS_FILE);
        Gdx.input.setInputProcessor(controller);

        world = new GameWorld();

        modelBatch = new ModelBatch();
        modelInstances = new Array<ModelInstance>();
        assets = new AssetManager();
        perspectiveCamera = new PerspectiveCamera();
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        //batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");

        pizza = new GameObject();
        texturedSprite = new TexturedSprite("badlogic.jpg");

        Arrays.stream(ModelAsset.values()).forEach(asset -> assets.load(asset.getFilename(), com.badlogic.gdx.graphics.g3d.Model.class));
        assetsLoaded = false;

        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void updateGame() {
        // calculate time since last update (delta)
        delta = System.currentTimeMillis() - lastUpdate;
        lastUpdate = System.currentTimeMillis();

        if (!assetsLoaded && assets.update()) {
            assetsLoaded = true;
            events.dispatchEvent(new AssetsLoadedEvent());
        }

        //sprite.update();
        pizza.update(delta, new Vector3(0.0f, 0.0f, 0.0f), new Vector3(1.0f, 1.0f, 1.0f));
        texturedSprite.update();

        perspectiveCamera.update();
        orthographicCamera.update();

        controller.update(delta);
        world.update(delta);
    }

    @Override
    public void renderGame() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(perspectiveCamera.perspectiveCamera);
        modelBatch.render(modelInstances);
        modelBatch.end();

        pizza.render();
        //texturedSprite.render();

        controller.render();
        world.render();
    }

    @Override
    public void dispose() {
        pizza.dispose();
        modelBatch.dispose();
        modelInstances.clear();
        assets.dispose();
    }

    public void registerEventListener(EventListener listener) {
        try {
            events.register(listener);
        } catch (EventRegistrationExeception eventRegistrationExeception) {
            eventRegistrationExeception.printStackTrace();
        }
    }

    public ModelInstance loadModel(ModelAsset model) {
        ModelInstance instance = new ModelInstance(assets.get(model.getFilename(), com.badlogic.gdx.graphics.g3d.Model.class));
        modelInstances.add(instance);
        return instance;
    }

    public static ZombieWorldGame getGame() {
        return _instance;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public com.badlogic.gdx.graphics.PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera.perspectiveCamera;
    }
}