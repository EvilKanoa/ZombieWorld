package ca.kanoa.zombieworld.desktop

import ca.kanoa.zombieworld.Config
import ca.kanoa.zombieworld.desktop.graphics.DesktopShaderLoader
import ca.kanoa.zombieworld.desktop.input.KeyboardController
import ca.kanoa.zombieworld.desktop.input.MouseController
import ca.kanoa.zombieworld.graphics.ShaderLoader
import ca.kanoa.zombieworld.input.BaseController
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ca.kanoa.zombieworld.ZombieWorldGame

fun main(arg: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.title = Config.TITLE

    val controller = KeyboardController()
    val shaderLoader = DesktopShaderLoader()
    LwjglApplication(ZombieWorldGame(controller, shaderLoader), config)
}
