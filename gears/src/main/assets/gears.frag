#version 300 es

precision mediump float;

uniform vec4 u_COLOR;

in float v_Shade;

layout(location = 0) out vec4 out_Color;

void main() {
	out_Color = vec4(u_COLOR.xyz * v_Shade, u_COLOR.w);
}
