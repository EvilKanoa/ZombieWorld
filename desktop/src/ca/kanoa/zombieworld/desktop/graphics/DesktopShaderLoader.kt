package ca.kanoa.zombieworld.desktop.graphics

import ca.kanoa.zombieworld.graphics.ShaderLoader
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShaderProgram

import java.io.File

class DesktopShaderLoader : ShaderLoader {

    override fun compile(vertexShader: String, fragmentShader: String): ShaderProgram {
        val vertexData = "#version 120\n${Gdx.files.internal("shaders" + File.separator + vertexShader).readString()}\n"
        val fragmentData = "#version 120\n${Gdx.files.internal("shaders" + File.separator + fragmentShader).readString()}\n"
        val program = ShaderProgram(vertexData, fragmentData)
        if (program.getLog().length > 0) {
            Gdx.app.debug("AndroidShaderLoader", "Shader Log (VS: ${vertexShader}, FS: ${fragmentShader}): ${program.getLog()}")
        }
        return program
    }

    override fun getShader(name: String): String = Gdx.files.internal("shaders/" + name).readString()
}
