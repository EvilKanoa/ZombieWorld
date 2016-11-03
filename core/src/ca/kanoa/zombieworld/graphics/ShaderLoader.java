package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.utilities.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderLoader {

    public static ShaderProgram compile(String vertexShader, String fragmentShader) {
        String vertexData = Gdx.files.internal("shaders/" + vertexShader).readString();
        String fragmentData = Gdx.files.internal("shaders/" + fragmentShader).readString();
        ShaderProgram program = new ShaderProgram(vertexData, fragmentData);
        Logger.log("Shader log: " + program.getLog());
        return program;
    }

    public static String getShader(String name) {
        return Gdx.files.internal("shaders/" + name).readString();
    }

}
