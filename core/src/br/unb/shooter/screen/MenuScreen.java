package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.state.GameState;
import br.unb.shooter.state.MultiplayerState;

public class MenuScreen extends Screen {

    /**
     * Constructor.
     */
    public MenuScreen() {
        Table table = new Table();
        table.setWidth(640);
        table.setHeight(480);
        getStage().addActor(table);

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonPlay = new TextButton("Singleplayer", btnStyle);

        table.add(buttonPlay).width(130);
        table.row();

        TextButton buttonMultiplayer = new TextButton("Multiplayer", btnStyle);

        table.add(buttonMultiplayer).width(130);
        table.row();

        TextButton buttonQuit = new TextButton("Quit", btnStyle);

        table.add(buttonQuit).width(130);

        buttonPlay.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new GameState());
            }

        });

        buttonMultiplayer.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new MultiplayerState());
            }

        });

        buttonQuit.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }

        });
    }

    public void draw() {
        super.draw();
    }

}
