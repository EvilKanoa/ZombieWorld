package ca.kanoa.zombieworld

import ca.kanoa.zombieworld.events.AssetsLoadedEvent
import ca.kanoa.zombieworld.events.EventListener
import ca.kanoa.zombieworld.events.EventManager
import ca.kanoa.zombieworld.events.EventRegistrationExeception
import ca.kanoa.zombieworld.files.Settings
import ca.kanoa.zombieworld.graphics.*
import ca.kanoa.zombieworld.graphics.ModelAsset
import ca.kanoa.zombieworld.graphics.PerspectiveCamera
import ca.kanoa.zombieworld.input.BaseController
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.PerformanceCounter

import java.util.Arrays

class ZombieWorldGame(var controller: BaseController, var shaderLoader: ShaderLoader) : OrganizedApplicationAdapter() {

    internal var batch: SpriteBatch? = null
    //Texture img;

    lateinit internal var pizza: GameObject
    lateinit internal var texturedSprite: TexturedSprite

    var events = EventManager()
    lateinit var world: GameWorld
    private var lastUpdate: Long = 0
    private var delta: Long = 0

    private var perspectiveCamera: PerspectiveCamera? = null
    var orthographicCamera: OrthographicCamera? = null
        private set
    private var modelBatch: ModelBatch? = null
    private var modelInstances: Array<ModelInstance>? = null
    private var assets: AssetManager? = null
    private var assetsLoaded: Boolean = false

    override fun create() {
        game = this
        Settings.loadFile(Config.SETTINGS_FILE)
        Gdx.input.inputProcessor = controller

        world = GameWorld()

        modelBatch = ModelBatch()
        modelInstances = Array<ModelInstance>()
        assets = AssetManager()
        perspectiveCamera = PerspectiveCamera()
        orthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())


        //batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");

        pizza = GameObject()
        texturedSprite = TexturedSprite("badlogic.jpg")

        for (modelAsset in ModelAsset.values()) {
            assets!!.load(modelAsset.filename, Model::class.java)
        }
        assetsLoaded = false

        lastUpdate = System.currentTimeMillis()
    }

    override fun updateGame() {
        // calculate time since last update (delta)
        delta = System.currentTimeMillis() - lastUpdate
        lastUpdate = System.currentTimeMillis()

        if (!assetsLoaded && assets!!.update()) {
            assetsLoaded = true
            events.dispatchEvent(AssetsLoadedEvent())
        }

        //sprite.update();
        pizza.update(delta, Vector3(0.0f, 0.0f, 0.0f), Vector3(1.0f, 1.0f, 1.0f))
        texturedSprite.update()

        perspectiveCamera!!.update()
        orthographicCamera!!.update()

        controller.update(delta)
        world.update(delta)
    }

    override fun renderGame() {
        Gdx.gl.glClearColor(1f, 1f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        modelBatch!!.begin(perspectiveCamera!!.perspectiveCamera)
        modelBatch!!.render(modelInstances!!)
        modelBatch!!.end()

        pizza.render()
        //texturedSprite.render();

        controller.render()
        world.render()
    }

    override fun dispose() {
        pizza.dispose()
        modelBatch!!.dispose()
        modelInstances!!.clear()
        assets!!.dispose()
    }

    fun registerEventListener(listener: EventListener) {
        try {
            events.register(listener)
        } catch (eventRegistrationExeception: EventRegistrationExeception) {
            eventRegistrationExeception.printStackTrace()
        }

    }

    fun loadModel(model: ModelAsset): ModelInstance {
        val instance = ModelInstance(assets!!.get(model.filename, com.badlogic.gdx.graphics.g3d.Model::class.java))
        modelInstances!!.add(instance)
        return instance
    }

    fun getPerspectiveCamera(): com.badlogic.gdx.graphics.PerspectiveCamera {
        return perspectiveCamera!!.perspectiveCamera
    }

    companion object {
        var game: ZombieWorldGame? = null
            private set
    }
}