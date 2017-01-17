varying vec2 texCoord;
varying vec3 normal;

uniform sampler2D texture;

void main()
{
    gl_FragColor = texture2D(texture, vec2(texCoord.x, 1.0 - texCoord.y));
}