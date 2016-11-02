layout (location = 0) in vec3 position;
layout (location = 1) in vec3 inNormal;
layout (location = 2) in vec2 inTexCoord;

out vec3 normal;
out vec2 texCoord;

uniform mat4 world;
uniform mat4 view;
uniform mat4 projection;

void main()
{
    gl_Position = projection * view * world * vec4(position, 1.0f);
    normal = (vec4(inNormal, 1.0f) * world).xyz;
    texCoord = vec2(inTexCoord.x, 1.0f - inTexCoord.y);
}