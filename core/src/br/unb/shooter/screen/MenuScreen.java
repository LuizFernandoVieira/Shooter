package br.unb.shooter.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.state.BrowserState;
import br.unb.shooter.state.HostState;

public class MenuScreen extends Screen {

    public MenuScreen() {
        Table table = new Table();
        table.setWidth(640);
        table.setHeight(400);
        getStage().addActor(table);

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonClient = new TextButton("Browse Games", btnStyle);
        buttonClient.setHeight(20);
        TextButton buttonServer = new TextButton("Host", btnStyle);
        buttonServer.setHeight(20);

        table.add(buttonClient).width(130).spaceRight(100);
        table.add(buttonServer).width(130).spaceLeft(100);

        buttonClient.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new BrowserState());
            }

        });

        buttonServer.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new HostState());
            }

        });
    }

    public void draw() {
        super.draw();
    }

}
