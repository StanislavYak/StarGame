package ru.geekbrains.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.geekbrains.Star2DGame;
import ru.geekbrains.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f/4f;
		config.height = 500;
		config.width = (int) (config.height * aspect);
		config.resizable = false;
		new LwjglApplication(new Star2DGame(), config);
	}
}
