package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import ca.kanoa.zombieworld.events.AssetsLoadedEvent;
import ca.kanoa.zombieworld.events.EventHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jonathan on 2017-05-11.
 */
public class GameObjects {
    private ArrayList<GameObject> gameObects;
    private ModelBatch modelBatch;
    private AssetManager assets;
    private boolean assetsLoaded;

    public GameObjects() {
        modelBatch = new ModelBatch();
        assets = new AssetManager();
        Arrays.stream(ModelAsset.values()).forEach(it -> assets.load(it.getFilename(), com.badlogic.gdx.graphics.g3d.Model.class));
        assetsLoaded = false;
    }

    public void update() {
        if (!assetsLoaded && assets.update()) {
            assetsLoaded = true;
            ZombieWorldGame.getGame().events.dispatchEvent(new AssetsLoadedEvent());
        }
    }

    public void render() {
        modelBatch.begin(ZombieWorldGame.getGame().getPerspectiveCamera());
        Arrays.stream(gameObects.toArray(new GameObject[0])).map(it -> it.modelInstance).forEach(it -> modelBatch.render(it));
        modelBatch.end();
    }

    public ModelInstance loadModel(ModelAsset model) {
        ModelInstance instance = new ModelInstance(assets.get(model.getFilename(), com.badlogic.gdx.graphics.g3d.Model.class));
        //modelInstances.add(instance);
        gameObects.add(new GameObject(instance));
        return instance;
    }

    @EventHandler
    public void onAssetsLoaded(AssetsLoadedEvent event) {
        ModelAsset[] assets = ModelAsset.values();
        for (int i = 0; i < assets.length; i++) {
            gameObects.get(i).modelInstance = loadModel(assets[i]);
        }
    }

    public void dispose() {
        modelBatch.dispose();
        //modelInstances.clear();
        assets.dispose();
    }
}
