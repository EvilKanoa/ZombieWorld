package ca.kanoa.zombieworld.utilities

import com.badlogic.gdx.Gdx

object Size {

    fun getWidthCenti(centimeters: Float): Float = Gdx.graphics.ppcX * centimeters

    fun getHeightCenti(centimeters: Float): Float = Gdx.graphics.ppcY * centimeters

    fun convertY(y: Float): Float = Gdx.graphics.height - y

    val screenWidth: Float
        get() = Gdx.graphics.width.toFloat()

    val screenHeight: Float
        get() = Gdx.graphics.height.toFloat()

}
