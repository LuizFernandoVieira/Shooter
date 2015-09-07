package br.unb.shooter.screen;

import java.util.Collection;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.state.GameState;

public class LobbyScreen extends Screen {

    private List<Player> listPlayers;

    private Label labelServerName;

    /**
     * Constructor.
     */
    public LobbyScreen(Boolean isServer) {
        super();

        Table table = new Table();
        table.setWidth(600);
        table.setHeight(600);
        getStage().addActor(table);

        labelServerName = new Label(GameController.getInstance().getServerName(), getSkin(), "default-font",
                Color.WHITE);

        table.add(labelServerName);

        table.row();

        listPlayers = new List<Player>(getSkin());

        table.add(listPlayers).width(500);

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

        table.add(buttonPlay).width(130).height(30);

        table.row();
    }

    /**
     * Update screen.
     */
    public void update() {
        if (GameController.getInstance().getPlayersMap() != null) {
            Collection<Player> players = GameController.getInstance().getPlayersMap().values();
            Player[] playersArray = new Player[players.size()];
            int index = 0;
            for (Player player : players) {
                playersArray[index] = player;
                index++;
            }
            listPlayers.setItems(playersArray);
        }

        labelServerName.setText(GameController.getInstance().getServerName());
    }

}
