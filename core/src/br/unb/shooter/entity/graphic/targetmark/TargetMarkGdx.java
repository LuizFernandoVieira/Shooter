package br.unb.shooter.entity.graphic.targetmark;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.TargetMark;

public class TargetMarkGdx {
    private Texture texture;

    public void initGraphics() {
        texture = new Texture(Gdx.files.internal("target.png"));
    }

    public void draw(SpriteBatch batch, TargetMark targetMark) {
        batch.draw(texture, targetMark.getX(), targetMark.getY(), targetMark.getWidth(), targetMark.getHeight());
    }

    public Texture getTexture() {
        return texture;
    }

    public Pixmap getPixmap() {
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        return texture.getTextureData().consumePixmap();
    }
}
