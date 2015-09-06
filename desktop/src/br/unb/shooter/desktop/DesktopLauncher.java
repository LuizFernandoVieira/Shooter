package br.unb.shooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.unb.shooter.Shooter;

public class DesktopLauncher {
    /**
     * Main method.
     * 
     * @param arg arguments
     */
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;
        config.height = 600;
        new LwjglApplication(new Shooter(), config);
    }
}
