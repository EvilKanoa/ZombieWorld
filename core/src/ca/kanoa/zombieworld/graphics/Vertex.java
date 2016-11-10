package ca.kanoa.zombieworld.graphics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Jonathan on 2016-11-01.
 */
public class Vertex {
    Vector3 pos;
    Vector3 normal;
    Vector2 texCoord;

    Vertex(Vector3 pos, Vector3 normal, Vector2 texCoord) {
        this.pos = pos;
        this.normal = normal;
        this.texCoord = texCoord;
    }

    Vertex(float x, float y, float z, float nx, float ny, float nz, float tx, float ty) {
        this.pos = new Vector3(x, y, z);
        this.normal = new Vector3(nx, ny, nz);
        this.texCoord = new Vector2(tx, ty);
    }

    public float [] getFloats() {
        return new float[]{pos.x, pos.y, pos.z, normal.x, normal.y, normal.z, texCoord.x, texCoord.y};
    }
}
