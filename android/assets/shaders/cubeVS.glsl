attribute vec3 inPosition;
attribute vec3 inNormal;
attribute vec2 inTexCoord;

varying vec2 texCoord;
varying vec3 normal;

uniform mat4 wvp;

void main()
{
    normal = inNormal;
    texCoord = inTexCoord;
    gl_Position = wvp * vec4(inPosition, 1.0);
}