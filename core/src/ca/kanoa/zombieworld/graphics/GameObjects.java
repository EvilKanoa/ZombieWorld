package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Jonathan on 2017-05-07.
 */
public class GameObjects {
    private ModelBatch modelBatch;
    private Array<ModelInstance> modelInstances;
    private AssetManager assets;
    private boolean assetLoaded;

    public GameObjects() {
        modelBatch = new ModelBatch();
        modelInstances = new Array<ModelInstance>();
        assets = new AssetManager();
    }

    public void loadModel(String filename) {
        assets.load(filename, Model.class);
        assetLoaded = false;

        ModelInstance modelInstance = new ModelInstance(assets.get(filename, Model.class));

        modelInstances.add(modelInstance);
    }

    public void remove() {

    }

    public void update() {

    }

    public void render() {
        modelBatch.begin(ZombieWorldGame.getGame().getPerspectiveCamera());
        modelBatch.render(modelInstances);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        modelInstances.clear();
    }
}
