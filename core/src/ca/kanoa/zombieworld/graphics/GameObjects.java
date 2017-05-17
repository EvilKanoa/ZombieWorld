package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import ca.kanoa.zombieworld.events.AssetsLoadedEvent;
import ca.kanoa.zombieworld.events.EventHandler;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

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

        for (ModelAsset modelAsset : ModelAsset.values())
            assets.load(modelAsset.getFilename(), com.badlogic.gdx.graphics.g3d.Model.class);
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
        modelBatch.render(modelInstances);
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
        for (ModelAsset modelAsset: ModelAsset.values()) {
            modelInstance = loadModel(modelAsset);
        }
    }

    public void dispose() {
        modelBatch.dispose();
        //modelInstances.clear();
        assets.dispose();
    }
}
