package com.mr.ggc;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GGC";
		cfg.width = 640;
		cfg.height = 480;
		
		new LwjglApplication(new GGC(), cfg);
	}
}
