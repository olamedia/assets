#version 120

varying vec2 texCoord;
varying vec4 frontColor;

uniform sampler2D mesh_ActiveTexture;

const vec4 texEnvColor = vec4(0.0);

const vec4 zerov4 = vec4(0.0);
const vec4 onev4 = vec4(1.0);

void main (void)
{
	vec4 texColor = texture2D(mesh_ActiveTexture, texCoord.st);
    if (texColor.a < 0.1) discard;
    
    vec4 color;
    // mix(x, y, a) == x * ( 1 - a ) + y * a
    // color.rgb = mix(frontColor.rgb, texEnvColor.rgb, texColor.rgb);
    color.rgb = mix(texEnvColor.rgb, frontColor.rgb, texColor.rgb);
    color.a = frontColor.a * texColor.a;
    gl_FragColor = clamp(color, zerov4, onev4);
}