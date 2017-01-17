package ca.kanoa.zombieworld;

import ca.kanoa.zombieworld.events.EventListener;
import ca.kanoa.zombieworld.events.EventManager;
import ca.kanoa.zombieworld.events.EventRegistrationExeception;
import ca.kanoa.zombieworld.files.Settings;
import ca.kanoa.zombieworld.graphics.GameObject;
import ca.kanoa.zombieworld.graphics.Shape;
import ca.kanoa.zombieworld.graphics.TexturedSprite;
import ca.kanoa.zombieworld.input.BaseController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import ca.kanoa.zombieworld.graphics.ShaderLoader;

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

    private OrthographicCamera orthographicCamera;
    private PerspectiveCamera perspectiveCamera;

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

        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera = new PerspectiveCamera(60.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.near = 0.1f;
        perspectiveCamera.far = 10000.0f;
        perspectiveCamera.direction.set(0.0f, 0.0f, 1.0f);
        perspectiveCamera.update(true);
        //perspectiveCamera.direction.set(0.0f, -1.0f, 0.0f);
        //perspectiveCamera.up.set(0.0f, 0.0f, 1.0f);


        //batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");

        pizza = new GameObject();
        texturedSprite = new TexturedSprite("badlogic.jpg");

        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void updateGame() {
        // calculate time since last update (delta)
        delta = System.currentTimeMillis() - lastUpdate;
        lastUpdate = System.currentTimeMillis();

        //sprite.update();
        pizza.update(delta, new Vector3(0.0f, 0.0f, 0.0f), new Vector3(1.0f, 1.0f, 1.0f));
        texturedSprite.update();

        perspectiveCamera.position.add(controller.getMovementDirection().x, 0, controller.getMovementDirection().y);

        perspectiveCamera.rotate(perspectiveCamera.up.cpy().crs(perspectiveCamera.direction.cpy()).nor(), controller.getShootDirection().y);
        perspectiveCamera.normalizeUp();

        if (perspectiveCamera.direction.y > 0.95f) perspectiveCamera.direction.y = 0.95f;
        if (perspectiveCamera.direction.y < -0.95f) perspectiveCamera.direction.y = -0.95f;

        perspectiveCamera.rotate(new Vector3(new Vector3(0.0f, 1.0f, 0.0f)), controller.getShootDirection().x);
        perspectiveCamera.normalizeUp();
        perspectiveCamera.direction.nor();

        orthographicCamera.update();
        perspectiveCamera.update();

        controller.update(delta);
        world.update(delta);
    }

    @Override
    public void renderGame() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pizza.render();
        texturedSprite.render();

        controller.render();
        world.render();
    }

    @Override
    public void dispose() {
        //batch.dispose();
        //img.dispose();
    }

    public void registerEventListener(EventListener listener) {
        try {
            events.register(listener);
        } catch (EventRegistrationExeception eventRegistrationExeception) {
            eventRegistrationExeception.printStackTrace();
        }
    }

    public static ZombieWorldGame getGame() {
        return _instance;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public PerspectiveCamera getPerspectiveCamera() {
        return perspectiveCamera;
    }
}