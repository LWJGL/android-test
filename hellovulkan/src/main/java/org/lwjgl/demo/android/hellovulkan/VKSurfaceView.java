package org.lwjgl.demo.android.hellovulkan;

import android.content.*;
import android.view.*;

import java.util.concurrent.atomic.*;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.android.ANativeWindow.*;

public class VKSurfaceView extends SurfaceView implements SurfaceHolder.Callback2 {

	private final AtomicBoolean detached = new AtomicBoolean(true);
	private final AtomicBoolean hasSurface = new AtomicBoolean(false);

	private final AtomicBoolean framebufferSizeChanged = new AtomicBoolean(false);
	private final AtomicInteger framebufferWidth = new AtomicInteger();
	private final AtomicInteger framebufferHeight = new AtomicInteger();

	private VKThread thread;

	public VKSurfaceView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		//System.err.println("onAttachedToWindow()");

		detached.set(false);
		thread = new VKThread();
		thread.start();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		//System.err.println("onDetachedFromWindow()");

		detached.set(true);
		try {
			thread.join();
			thread = null;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//System.err.println("surfaceCreated()");
		hasSurface.set(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		//System.err.println("surfaceChanged(" + width + ", " + height + ")");

		framebufferWidth.set(width);
		framebufferHeight.set(height);
		framebufferSizeChanged.set(true);
	}

	@Override
	public void surfaceRedrawNeeded(SurfaceHolder holder) {
		//System.err.println("surfaceRedrawNeeded()");
		//helloVulkan.demo_draw();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//System.err.println("surfaceDestroyed()");
		hasSurface.set(false);
	}

	private class VKThread extends Thread {

		VKThread() {
			super("VKThread");
		}

		private long nativeWindow;
		private HelloVulkan renderer;

		@Override
		public void run() {
			while (!detached.get()) {
				while (!hasSurface.get()) {
					sleep();
					if (detached.get())
						break;
				}

				if (renderer == null) {
					nativeWindow = ANativeWindow_fromSurface(getHolder().getSurface());
					if (!init(nativeWindow))
						continue;
				}

				if (framebufferSizeChanged.get())
					updateSurface();

				if ( !renderer.drawFrame() )
					cleanup();
			}

			cleanup();
		}

		private boolean init(long nativeWindow) {
			if (nativeWindow == NULL)
				return false;

			renderer = new HelloVulkan(nativeWindow);
			renderer.initSurface();
			return true;
		}

		private void sleep() {
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		private void updateSurface() {
			renderer.framebufferSizeChanged(
				framebufferWidth.get(),
				framebufferHeight.get()
			);
			framebufferSizeChanged.set(false);
		}

		private void cleanup() {
			if (renderer == null)
				return;

			renderer.destroy();
			renderer = null;
			ANativeWindow_release(nativeWindow);
		}
	}

}
