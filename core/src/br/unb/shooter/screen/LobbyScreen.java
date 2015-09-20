package br.unb.shooter.screen;

import java.util.Collection;
import java.util.Iterator;

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
import br.unb.shooter.state.StateEventEnum;

public class LobbyScreen extends Screen {

    private List<Player> listPlayers;

    private Label labelServerName;

    private Boolean isServer;

    /**
     * Constructor.
     */
    public LobbyScreen(Boolean isServer) {
        super();

        this.isServer = isServer;
    }

    @Override
    public void create() {
        Table table = new Table();
        table.setWidth(600);
        table.setHeight(600);
        getStage().addActor(table);

        labelServerName = new Label(GameController.getInstance().getServerName(), getSkin(), "default-font",
                Color.WHITE);

        table.add(labelServerName);

        table.row();

        listPlayers = new List<Player>(getSkin());

        listPlayers.setItems(new Player(), new Player(), new Player(), new Player());

        listPlayers.setSelectedIndex(-1);

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
                pressStartButton();
            }

        });

        table.add(buttonPlay).width(130).height(30);

        table.row();
    }

    /**
     * Updates the screen.
     */
    @Override
    public void update() {
        if (GameController.getInstance().getPlayersMap() != null) {
            Collection<Player> players = GameController.getInstance().getPlayersMap().values();
            Iterator<Player> iterator = players.iterator();
            Player[] playersArray = new Player[4];
            for (int index = 0; index < 4; index++) {
                if (iterator.hasNext()) {
                    playersArray[index] = iterator.next();
                } else {
                    playersArray[index] = new Player();
                }
            }
            listPlayers.setItems(playersArray);
        }

        labelServerName.setText(GameController.getInstance().getServerName());
    }

    private void pressStartButton() {
        getMachine().handle(StateEventEnum.BUTTON_START);
    }

}
