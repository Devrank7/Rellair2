#version 330 core

uniform sampler2D sampler;

in vec2 texCoords;

out vec4 fragColor;

void main() {
    // Радиус размытия
    float radius = 5.0;

    // Коэффициенты для гауссовского размытия
    float weight[5] = float[](0.227027, 0.1945946, 0.1216216, 0.054054, 0.016216);

    vec3 result = texture(sampler, texCoords).xyz * weight[0];

    for (int i = 1; i < 5; i++) {
        result += texture(sampler, texCoords + vec2(radius * i, 0.0)).xyz * weight[i];
        result += texture(sampler, texCoords - vec2(radius * i, 0.0)).xyz * weight[i];
    }

    fragColor = vec4(result, 1.0);
}
