package br.unb.shooter.screen;

import java.util.Collection;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.state.GameState;
import br.unb.shooter.util.Constants;

public class LobbyScreen extends Screen {

    private TextArea textAreaPlayers;

    private Label labelServerName;

    /**
     * Constructor.
     */
    public LobbyScreen(Boolean isServer) {
        Table table = new Table();
        table.setWidth(640);
        table.setHeight(480);
        getStage().addActor(table);

        labelServerName = new Label(GameController.getInstance().getServerName(), getSkin(), "default-font",
                Color.WHITE);

        table.add(labelServerName);

        table.row();

        TextFieldStyle style = new TextFieldStyle(getSkin().getFont("default-font"), Color.WHITE,
                getSkin().getDrawable("cursor"), getSkin().getDrawable("selection"),
                getSkin().getDrawable("textfield"));

        textAreaPlayers = new TextArea("", style);
        textAreaPlayers.setPrefRows(4);
        textAreaPlayers.setDisabled(true);

        table.add(textAreaPlayers).width(500).colspan(2);

        table.row();

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonPlay = new TextButton("Start", btnStyle);

        buttonPlay.setDisabled(!isServer);

        buttonPlay.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new GameState());
            }

        });

        table.add(buttonPlay).width(130);

        table.row();
    }

    /**
     * Update screen.
     */
    public void update() {
        String text = "";

        if (GameController.getInstance().getPlayersMap() != null) {
            Collection<Player> players = GameController.getInstance().getPlayersMap().values();

            for (Player player : players) {
                text += (player.getName() + Constants.NEW_LINE);
            }

            textAreaPlayers.setText(text);
        }

        labelServerName.setText(GameController.getInstance().getServerName());
    }

}
