#version 300 es

uniform mat4 u_MVP;
uniform mat3 u_NORMAL;

uniform vec3 u_LIGHT;

layout(location = 0) in  vec3 in_Position;
layout(location = 1) in  vec3 in_Normal;

out float v_Shade;

void main() {
    vec3 normal = normalize(u_NORMAL * in_Normal);
	v_Shade = max(dot(normal, u_LIGHT), 0.0);
	gl_Position = u_MVP * vec4(in_Position, 1.0);
}
