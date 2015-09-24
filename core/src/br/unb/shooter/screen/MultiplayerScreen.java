package br.unb.shooter.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.state.BrowseState;
import br.unb.shooter.state.HostState;
import br.unb.shooter.state.MenuState;
import br.unb.shooter.util.Constants;

public class MultiplayerScreen extends Screen {

    /**
     * Constructor.
     */
    public MultiplayerScreen() {
        super();
    }

    @Override
    public void create() {
        Table table = new Table();
        table.setWidth(Constants.CAMERA_WIDTH);
        table.setHeight(Constants.CAMERA_HEIGHT);
        getStage().addActor(table);

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonHost = new TextButton("Host Game", btnStyle);

        table.add(buttonHost).width(130).height(30);
        table.row();

        TextButton buttonBrowse = new TextButton("Browse Games", btnStyle);

        table.add(buttonBrowse).width(130).height(30);
        table.row();

        TextButton buttonBack = new TextButton("Back", btnStyle);

        table.add(buttonBack).width(130).height(30);

        buttonHost.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new HostState());
            }

        });

        buttonBrowse.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new BrowseState());
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
