package ca.kanoa.zombieworld.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderLoader {

    public static ShaderProgram compile(String vertexShader, String fragmentShader) {
        String vertexData = Gdx.files.internal("shaders/" + vertexShader).readString();
        String fragmentData = Gdx.files.internal("shaders/" + fragmentShader).readString();
        return new ShaderProgram(vertexShader, fragmentShader);
    }

    public static String getShader(String name) {
        return Gdx.files.internal("shaders/" + name).readString();
    }

}
