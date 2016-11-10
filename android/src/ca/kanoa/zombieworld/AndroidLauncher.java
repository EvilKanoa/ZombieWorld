package ca.kanoa.zombieworld;

import android.os.Bundle;

import ca.kanoa.zombieworld.graphics.AndroidShaderLoader;
import ca.kanoa.zombieworld.graphics.ShaderLoader;
import ca.kanoa.zombieworld.input.AndroidController;
import ca.kanoa.zombieworld.input.BaseController;
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
        config.hideStatusBar = true;

        BaseController controller = new AndroidController();
        ShaderLoader shaderLoader = new AndroidShaderLoader();
        initialize(new ZombieWorldGame(controller, shaderLoader), config);
	}
}
