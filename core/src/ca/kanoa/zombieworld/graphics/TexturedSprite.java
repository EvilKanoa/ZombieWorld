package ca.kanoa.zombieworld.graphics;

import ca.kanoa.zombieworld.ZombieWorldGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class TexturedSprite extends Render2D {
    Texture texture;

    public TexturedSprite(String texName) {
        super();
        shader = ZombieWorldGame.getGame().shaderLoader.compile("texturedSpriteVS.glsl", "texturedSpriteFS.glsl");
        this.texture = new Texture(texName);

        float vertices[] = new float[]
                {-100.0f, -100.0f, 0.0f, 0.0f, 0.0f,
                 -100.0f, 100.0f, 0.0f, 0.0f, 1.0f,
                 100.0f, 100.0f, 0.0f, 1.0f, 1.0f,
                 100.0f, -100.0f, 0.0f, 1.0f, 0.0f};

        short indices[] = new short[] {0, 3, 2, 0, 2, 1};

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
