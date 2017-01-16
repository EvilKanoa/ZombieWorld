attribute vec3 inPosition;

uniform mat4 projWorld;

void main()
{
    gl_Position = projWorld * vec4(inPosition, 1.0);
}
