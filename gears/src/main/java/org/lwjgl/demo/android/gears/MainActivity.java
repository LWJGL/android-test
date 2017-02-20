package org.lwjgl.demo.android.gears;

import android.opengl.*;
import android.os.*;
import android.support.v7.app.*;

import org.lwjgl.egl.EGL;
import org.lwjgl.egl.*;
import org.lwjgl.opengles.*;
import org.lwjgl.system.*;

import java.lang.reflect.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.*;

import static org.lwjgl.egl.EGL10.*;
import static org.lwjgl.opengles.GLES20.*;
import static org.lwjgl.system.APIUtil.*;

public class MainActivity extends AppCompatActivity {

	private Gears gears;

	private GLSurfaceView glView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		glView = new GLSurfaceView(this);
		glView.setEGLContextClientVersion(2);
		glView.setRenderer(new GLSurfaceView.Renderer() {
			@Override
			public void onSurfaceCreated(GL10 gl, EGLConfig config) {
				GLES.createCapabilities();

				/*APIUtil.APIVersion version = apiParseVersion(eglQueryString(eglGetCurrentDisplay(), EGL_VERSION));
				EGLCapabilities caps = EGL.createDisplayCapabilities(eglGetCurrentDisplay(), version.major, version.minor);

				System.out.println(caps);
				Field[] fields = caps.getClass().getDeclaredFields();
				for (Field field : fields) {
					try {
						if (field.getType() != Boolean.TYPE || !field.getBoolean(caps))
							continue;
						System.out.println(field.getName());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}*/

				gears = new Gears(MainActivity.this.getResources());
			}

			@Override
			public void onSurfaceChanged(GL10 gl, int width, int height) {
				gears.reshape(width, height);
			}

			@Override
			public void onDrawFrame(GL10 gl) {
				float sin = (float) Math.sin(System.currentTimeMillis() / 1000.0);

				glClearColor(0.2f, 0.4f, sin * sin, 1.0f);
				glClear(GL_COLOR_BUFFER_BIT);

				gears.draw();
				gears.idle();
			}
		});

		setContentView(glView);
	}

}
