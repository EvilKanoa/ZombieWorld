package ca.kanoa.zombieworld;

import ca.kanoa.zombieworld.events.EventListener;
import ca.kanoa.zombieworld.events.EventManager;
import ca.kanoa.zombieworld.events.EventRegistrationExeception;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ZombieWorldGame extends OrganizedApplicationAdapter {

	SpriteBatch batch;
	Texture img;
    private EventManager eventManager;
	
	@Override
	public void create () {
        eventManager = new EventManager();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

    @Override
    public void updateGame() {
        
    }

    @Override
    public void renderGame() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 10, 10);
        batch.end();
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
