package ca.kanoa.zombieworld;

import ca.kanoa.zombieworld.events.EventListener;
import ca.kanoa.zombieworld.events.EventManager;
import ca.kanoa.zombieworld.events.EventRegistrationExeception;
import ca.kanoa.zombieworld.input.BaseController;
import ca.kanoa.zombieworld.input.InactiveController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ZombieWorldGame extends OrganizedApplicationAdapter {

	SpriteBatch batch;
	Texture img;

    private EventManager eventManager;
    private BaseController controller;
    private GameWorld world;
    private static ZombieWorldGame _instance;
    private long lastUpdate, delta;

    public ZombieWorldGame(BaseController controller) {
        this.controller = controller;
    }

	@Override
	public void create () {
        _instance = this;
        eventManager = new EventManager();
        world = new GameWorld();

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

        lastUpdate = System.currentTimeMillis();
	}

    @Override
    public void updateGame() {
        // calculate time since last update (delta)
        delta = System.currentTimeMillis() - lastUpdate;
        lastUpdate = System.currentTimeMillis();

        controller.update(delta);
        world.update(delta);

        System.out.println("dir: " + controller.getShootDirection().angle());
    }

    @Override
    public void renderGame() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, world.getPlayer().getPosition().x - img.getWidth() / 4,
                world.getPlayer().getPosition().y - img.getHeight() / 4, img.getWidth() / 2, img.getHeight() / 2);
        batch.end();

        controller.render();
        world.render();
    }

    @Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

    public void registerEventListener(EventListener listener) {
        try {
            eventManager.register(listener);
        } catch (EventRegistrationExeception eventRegistrationExeception) {
            eventRegistrationExeception.printStackTrace();
        }
    }

    public static ZombieWorldGame getGame() {
        return _instance;
    }

    public GameWorld getGameWorld() {
        return this.world;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public BaseController getController() {
        return this.controller;
    }

}
