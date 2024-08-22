#version 120

uniform sampler2D sampler0;
uniform float intensity;

void main() {
    vec2 uv = gl_TexCoord[0].st;
    vec4 sum = vec4(0.0);

    // Гауссово размытие
    sum += texture2D(sampler0, vec2(uv.x - 4.0 * intensity, uv.y)) * 0.05;
    sum += texture2D(sampler0, vec2(uv.x - 3.0 * intensity, uv.y)) * 0.09;
    sum += texture2D(sampler0, vec2(uv.x - 2.0 * intensity, uv.y)) * 0.12;
    sum += texture2D(sampler0, vec2(uv.x - intensity, uv.y)) * 0.15;
    sum += texture2D(sampler0, uv) * 0.16;
    sum += texture2D(sampler0, vec2(uv.x + intensity, uv.y)) * 0.15;
    sum += texture2D(sampler0, vec2(uv.x + 2.0 * intensity, uv.y)) * 0.12;
    sum += texture2D(sampler0, vec2(uv.x + 3.0 * intensity, uv.y)) * 0.09;
    sum += texture2D(sampler0, vec2(uv.x + 4.0 * intensity, uv.y)) * 0.05;

    gl_FragColor = sum;
}
