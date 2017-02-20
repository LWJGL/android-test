package org.lwjgl.demo.android.hellovulkan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {

	private SurfaceView vkView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		vkView = new VKSurfaceView(this);
		setContentView(vkView);
	}
}
