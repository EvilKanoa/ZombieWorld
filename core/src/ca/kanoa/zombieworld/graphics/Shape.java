package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Jonathan on 2016-11-10.
 */
public class Shape extends Render2D {
    Vector3 colour;

    Shape(float vertices[], short indices[]) {
        super();
        shader = ZombieWorldGame.Companion.getGame().getShaderLoader().compile("spriteVS.glsl", "spriteFS.glsl");

        setVertices(vertices);
        setIndices(indices);
    }

    @Override
    void setShaderVariables() {
        shader.setVertexAttribute("inPosition", 3, GL20.GL_FLOAT, false, 12, 0);
        shader.enableVertexAttribute("inPosition");

        shader.setUniform3fv(shader.getUniformLocation("colour"), new float[] {colour.x, colour.y, colour.z}, 0, 3);
    }
}
