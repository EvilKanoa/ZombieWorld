package ca.kanoa.zombieworld.desktop.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys

import java.io.File
import java.util.HashMap

class KeyBindings {

    private val bindings: HashMap<String, Int> = HashMap<String, Int>()

    constructor(configFile: File) {
        //TODO: Implement customizable keybindings
    }

    constructor() {
        // static constant keybindings until config files are implemented
        bindings.put("up", Keys.W)
        bindings.put("down", Keys.S)
        bindings.put("left", Keys.A)
        bindings.put("right", Keys.D)
        bindings.put("aim_up", Keys.UP)
        bindings.put("aim_down", Keys.DOWN)
        bindings.put("aim_left", Keys.LEFT)
        bindings.put("aim_right", Keys.RIGHT)
    }

    fun getBinding(command: String): Int = bindings[command] ?: -1;

    fun keyDown(keyCommand: String): Boolean = Gdx.input.isKeyPressed(getBinding(keyCommand))

    fun keyJustDown(keyCommand: String): Boolean = Gdx.input.isKeyJustPressed(getBinding(keyCommand))

}
