package ca.kanoa.zombieworld.graphics;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public interface ShaderLoader {

    ShaderProgram compile(String vertexShader, String fragmentShader) ;

    String getShader(String name);

}
