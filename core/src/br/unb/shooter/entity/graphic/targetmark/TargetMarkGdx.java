package br.unb.shooter.entity.graphic.targetmark;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class TargetMarkGdx {
    private Pixmap pixmap;

    public void initGraphics() {
        pixmap = new Pixmap(Gdx.files.internal("target.png"));
    }

    public Pixmap getPixmap() {
        return pixmap;
    }
}
