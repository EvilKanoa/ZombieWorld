#version 120

uniform vec2 position;

void main()
{
    gl_Position = vec4(position.x, 0, position.y, 1.0);
}
