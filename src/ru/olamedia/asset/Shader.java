package ru.olamedia.asset;

import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLContext;

import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;
import com.jogamp.opengl.util.glsl.ShaderState;

public class Shader {
	private ShaderState state;

	public ShaderState getState() {
		return state;
	}

	public void compile() {
		final GL2ES2 gl = GLContext.getCurrentGL().getGL2ES2();
		state = new ShaderState();
		state.setVerbose(true);
		final ShaderCode vp0 = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, this.getClass(), "shader", "shader/bin",
				"block", true);
		final ShaderCode fp0 = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(), "shader",
				"shader/bin", "block", true);
		final ShaderProgram sp0 = new ShaderProgram();
		sp0.add(gl, vp0, System.err);
		sp0.add(gl, fp0, System.err);
		state.attachShaderProgram(gl, sp0, true);
	}

	public void enable() {
		final GL2ES2 gl = GLContext.getCurrentGL().getGL2ES2();
		state.useProgram(gl, true);
		gl.getContext().attachObject(Shader.class.getName(), state);
	}

	public void disable() {
		final GL2ES2 gl = GLContext.getCurrentGL().getGL2ES2();
		state.useProgram(gl, false);
	}

	public static ShaderState getCurrentShaderState() {
		final GLContext ctx = GLContext.getCurrent();
		if (null != ctx) {
			return (ShaderState) ctx.getAttachedObject(Shader.class.getName());
		} else {
			return null;
		}
	}
}
