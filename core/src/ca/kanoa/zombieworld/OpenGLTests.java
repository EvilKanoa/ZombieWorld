package ca.kanoa.zombieworld;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;

import static com.badlogic.gdx.Gdx.gl;

public class OpenGLTests extends OrganizedApplicationAdapter {

    String vertexShaderSource = "#version 120\n" +
            "attribute vec3 position;\n" +
            "void main() {\n" +
            "    gl_Position = vec4(position.x, position.y, position.z, 1.0);\n" +
            "}\n";
    String fragmentShaderSource = "#version 120\n" +
            "void main() {\n" +
            "    gl_FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);\n" +
            "}\n";

    float vertices[] = {
            0.5f,  0.3f, 0.0f,  // Top Right
            0.5f, -0.7f, 0.0f,  // Bottom Right
            -0.5f, -0.7f, 0.0f,  // Bottom Left
            -0.5f,  0.3f, 0.0f,   // Top Left
            0.0f,  0.9f, 0.0f // roof
    };
    int indices[] = {  // Note that we start from 0!
            0, 1, 3,   // First Triangle
            1, 2, 3,    // Second Triangle
            0, 3, 4
    };

    Matrix4 model, view, projection;

    int vbo, ebo, vertexShader, fragmentShader, shaderProgram;

    @Override
    public void create() {
        ebo = gl.glGenBuffer();
        gl.glBindBuffer(gl.GL_ELEMENT_ARRAY_BUFFER, ebo);
        gl.glBufferData(gl.GL_ELEMENT_ARRAY_BUFFER, indices.length * Integer.SIZE / 8, BufferUtils.newIntBuffer(indices.length).put(indices).flip(), gl.GL_STATIC_DRAW);
        gl.glBindBuffer(gl.GL_ELEMENT_ARRAY_BUFFER, 0);

        vbo = gl.glGenBuffer();
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, vbo);
        gl.glBufferData(gl.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8, BufferUtils.newFloatBuffer(vertices.length).put(vertices).flip(), gl.GL_STATIC_DRAW);
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, 0);

        vertexShader = gl.glCreateShader(gl.GL_VERTEX_SHADER);
        gl.glShaderSource(vertexShader, vertexShaderSource);
        gl.glCompileShader(vertexShader);

        System.out.println("VSLog: " + gl.glGetShaderInfoLog(vertexShader));

        fragmentShader = gl.glCreateShader(gl.GL_FRAGMENT_SHADER);
        gl.glShaderSource(fragmentShader, fragmentShaderSource);
        gl.glCompileShader(fragmentShader);

        System.out.println("FSLog: " + gl.glGetShaderInfoLog(fragmentShader));

        shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, vertexShader);
        gl.glAttachShader(shaderProgram, fragmentShader);
        gl.glLinkProgram(shaderProgram);

        System.out.println("SPLink: " + gl.glGetProgramInfoLog(shaderProgram));

        gl.glDeleteShader(vertexShader);
        gl.glDeleteShader(fragmentShader);
    }

    @Override
    public void updateGame() {

    }

    @Override
    public void renderGame() {
        gl.glClearColor(0f,0.5f,0.5f,0f);
        gl.glClear(gl.GL_COLOR_BUFFER_BIT);

        gl.glUseProgram(shaderProgram);
        gl.glBindBuffer(gl.GL_ELEMENT_ARRAY_BUFFER, ebo);
        gl.glBindBuffer(gl.GL_ARRAY_BUFFER, vbo);
        gl.glVertexAttribPointer(0, 3, gl.GL_FLOAT, false, 3 * Float.SIZE / 8, 0);
        gl.glEnableVertexAttribArray(0);

        //gl.glDrawArrays(gl.GL_TRIANGLES, 0, 3);
        gl.glDrawElements(gl.GL_TRIANGLES, indices.length, gl.GL_UNSIGNED_INT, 0);

    }

    @Override
    public void dispose() {

    }
}
