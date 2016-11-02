in vec3 normal;
in vec2 texCoord;

out vec4 colour;

uniform sampler2D ourTexture1;

void main()
{
    normal = normalize(normal);
    colour = texture(ourTexture1, TexCoord);
}