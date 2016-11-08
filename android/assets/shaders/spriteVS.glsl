#version 100

attribute vec3 position;

uniform mat4 projWorld;

void main()
{
    gl_Position = projWorld * vec4(position, 1.0);
}