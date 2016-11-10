#version 100

uniform vec4 color;
uniform vec2 position;
uniform vec2 center;
uniform float radius;

void main()
{
    if (pow(center.x - position.x, 2) + pow(center.y - position.y, 2) <= radius * radius)
    {
        gl_FragColor = glColor;
    }
    else
    {
        discard;
    }
}
