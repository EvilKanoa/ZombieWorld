attribute vec3 inPosition;
attribute vec2 inTexCoord;

varying vec2 texCoord;

uniform mat4 projWorld;

void main()
{
    texCoord = inTexCoord;
    gl_Position = projWorld * vec4(inPosition, 1.0);
}