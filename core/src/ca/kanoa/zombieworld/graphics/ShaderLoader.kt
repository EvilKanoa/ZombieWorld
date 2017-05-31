package ca.kanoa.zombieworld.graphics

import com.badlogic.gdx.graphics.glutils.ShaderProgram

interface ShaderLoader {

    fun compile(vertexShader: String, fragmentShader: String): ShaderProgram

    fun getShader(name: String): String

}