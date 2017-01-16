package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Jonathan on 2016-11-10.
 */
public class TexturedSprite extends Render2D {
    Texture texture;

    TexturedSprite(String texName, float vertices[], short indices[]) {
        shader = ZombieWorldGame.getGame().shaderLoader.compile("texturedSpriteVS.glsl", "texturedSpriteFS.glsl");
        this.texture = new Texture(texName);

        setVertices(vertices);
        setIndices(indices);
    }

    @Override
    void setShaderVariables() {
        shader.setVertexAttribute("inPosition", 3, GL20.GL_FLOAT, false, 20, 0);
        shader.setVertexAttribute("inTexCoord", 2, GL20.GL_FLOAT, false, 20, 12);
        shader.enableVertexAttribute("inPosition");
        shader.enableVertexAttribute("inTexCoord");

        shader.setUniformi(shader.getUniformLocation("texture"), 0);

        texture.bind();
    }
}
