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
    private long lastUpdate, delta;
	
	@Override
	public void create () {
        eventManager = new EventManager();
        controller = new InactiveController();

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
    }

    @Override
    public void renderGame() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 10, 10);
        batch.end();

        controller.render();
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

}
