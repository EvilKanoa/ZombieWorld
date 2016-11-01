package ca.kanoa.zombieworld;

import android.os.Bundle;

import ca.kanoa.zombieworld.input.AndroidController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ca.kanoa.zombieworld.ZombieWorldGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

        AndroidController controller = new AndroidController();
        Gdx.input.setInputProcessor(controller);
        initialize(new ZombieWorldGame(controller), config);
	}
}
