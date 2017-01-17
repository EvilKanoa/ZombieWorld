package ca.kanoa.zombieworld.desktop.graphics;

import ca.kanoa.zombieworld.graphics.ShaderLoader;
import ca.kanoa.zombieworld.utilities.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.io.File;

public class DesktopShaderLoader implements ShaderLoader {

    @Override
    public ShaderProgram compile(String vertexShader, String fragmentShader) {
        String vertexData = String.format("#version 120\n%s\n", Gdx.files.internal("shaders" + File.separator +
                vertexShader).readString());
        String fragmentData = String.format("#version 120\n%s\n", Gdx.files.internal("shaders" + File.separator +
                fragmentShader).readString());
        ShaderProgram program = new ShaderProgram(vertexData, fragmentData);
        if (program.getLog().length() > 0)
            Logger.log(String.format("Shader Log (VS: %s, FS: %s): %s", vertexShader, fragmentShader, program.getLog()));
        return program;
    }

    @Override
    public String getShader(String name) {
        return Gdx.files.internal("shaders/" + name).readString();
    }
}
