package br.unb.shooter.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.state.BrowserState;
import br.unb.shooter.state.HostState;
import br.unb.shooter.state.MenuState;

public class MultiplayerScreen extends Screen {

    /**
     * Constructor.
     */
    public MultiplayerScreen() {
        Table table = new Table();
        table.setWidth(640);
        table.setHeight(480);
        getStage().addActor(table);

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonHost = new TextButton("Host Game", btnStyle);

        table.add(buttonHost).width(130);
        table.row();

        TextButton buttonBrowse = new TextButton("Browse Games", btnStyle);

        table.add(buttonBrowse).width(130);
        table.row();

        TextButton buttonBack = new TextButton("Back", btnStyle);

        table.add(buttonBack).width(130);

        buttonHost.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new HostState());
            }

        });

        buttonBrowse.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new BrowserState());
            }

        });

        buttonBack.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new MenuState());
            }

        });
    }
}
