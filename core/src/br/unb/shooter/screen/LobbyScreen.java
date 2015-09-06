package br.unb.shooter.screen;

import java.util.Collection;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class LobbyScreen extends Screen {

    private TextArea textAreaPlayers;

    private String text;

    /**
     * Constructor.
     */
    public LobbyScreen() {
        Table table = new Table();
        table.setWidth(640);
        table.setHeight(480);
        getStage().addActor(table);

        Label labelServerName = new Label(GameController.getInstance().getServerName(), getSkin(), "default-font",
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

        text = "";

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        btnStyle.fontColor = Color.WHITE;

        TextButton buttonPlay = new TextButton("Start", btnStyle);

        table.add(buttonPlay).width(130);

        table.row();
    }

    /**
     * Update screen.
     */
    public void update() {
        text = "";

        Collection<Player> players = GameController.getInstance().getPlayersMap().values();

        for (Player player : players) {
            text += (player.getName() + Constants.NEW_LINE);
        }

        textAreaPlayers.setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
