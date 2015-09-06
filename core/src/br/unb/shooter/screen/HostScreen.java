package br.unb.shooter.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.state.LobbyServerState;

public class HostScreen extends Screen {

    /**
     * Constructor.
     */
    public HostScreen() {
        getStage().clear();

        Table table = new Table();
        table.setBounds(0, 0, 640, 480);
        getStage().addActor(table);

        TextFieldStyle style = new TextFieldStyle(getSkin().getFont("default-font"), Color.WHITE,
                getSkin().getDrawable("cursor"), getSkin().getDrawable("selection"),
                getSkin().getDrawable("textfield"));

        TextField textFieldName = new TextField("", style);

        textFieldName.setMessageText("Nome");
        textFieldName.setMaxLength(10);

        table.add(textFieldName).width(150);

        table.row();

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonStart = new TextButton("Start Server", btnStyle);

        table.add(buttonStart).width(130);

        buttonStart.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new LobbyServerState());
            }

        });
    }

    public void draw() {
        super.draw();
    }

    public void update() {
    }

}
