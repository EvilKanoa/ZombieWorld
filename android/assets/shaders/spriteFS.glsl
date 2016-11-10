#version 100

varying vec2 texCoord;

uniform sampler2D texture;

void main()
{
    gl_FragColor = texture2D(texture, vec2(texCoord.x, 1.0f - texCoord.y));;
}