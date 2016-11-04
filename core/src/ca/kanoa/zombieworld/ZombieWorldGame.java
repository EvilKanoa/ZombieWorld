package ca.kanoa.zombieworld;

import ca.kanoa.zombieworld.events.EventListener;
import ca.kanoa.zombieworld.events.EventManager;
import ca.kanoa.zombieworld.events.EventRegistrationExeception;
import ca.kanoa.zombieworld.files.Settings;
import ca.kanoa.zombieworld.graphics.GameObject;
import ca.kanoa.zombieworld.graphics.Sprite;
import ca.kanoa.zombieworld.input.BaseController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ZombieWorldGame extends OrganizedApplicationAdapter {

	SpriteBatch batch;
	//Texture img;

    Sprite sprite;
    GameObject pizza;

    public final EventManager events = new EventManager();
    public final BaseController controller;
    public GameWorld world;

    private static ZombieWorldGame _instance;
    private long lastUpdate, delta;

    private OrthographicCamera orthographicCamera;
    private PerspectiveCamera perspectiveCamera;

    public ZombieWorldGame(BaseController controller) {
        this.controller = controller;
    }

	@Override
	public void create () {
        _instance = this;
        Settings.loadFile(Config.SETTINGS_FILE);
        Gdx.input.setInputProcessor(controller);

        world = new GameWorld();

        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera = new PerspectiveCamera(70.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.far = 1000.0f;
        perspectiveCamera.update();

		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

        sprite = new Sprite();
        pizza = new GameObject();

        lastUpdate = System.currentTimeMillis();
	}

    @Override
    public void updateGame() {
        // calculate time since last update (delta)
        delta = System.currentTimeMillis() - lastUpdate;
        lastUpdate = System.currentTimeMillis();

        orthographicCamera.update(true);
        perspectiveCamera.update(true);

        sprite.update();
        pizza.update(delta);

        perspectiveCamera.position.add(controller.getMovementDirection().x * 0.1f, 0, controller.getMovementDirection().y * 0.1f);
        //perspectiveCamera.direction.add(new Vector3(controller.getShootDirection().scl(0.1f), 0));
        pizza.rotation.setToRotation(0.0f, 1.0f, 0.0f, controller.getShootDirection().angle());

        controller.update(delta);
        world.update(delta);
    }

    @Override
    public void renderGame() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*
        batch.begin();
        batch.draw(img, world.getPlayer().getPosition().x - img.getWidth() / 4,
                world.getPlayer().getPosition().y - img.getHeight() / 4, 0f, 0f, img.getWidth(), img.getHeight(),
                1f, 1f, controller.getShootDirection().angle(), img.getWidth() / 2, img.getHeight() / 2, img.getWidth(), img.getHeight(), false, false);
        batch.end();
        */
        sprite.render();
        pizza.render();

        controller.render();
        world.render();
    }

    @Override
	public void dispose () {
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
