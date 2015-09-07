package br.unb.shooter.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.entity.GameServer;
import br.unb.shooter.entity.Player;
import br.unb.shooter.state.LobbyClientState;

public class BrowseScreen extends Screen {

    private TextField textFieldName;

    private List<GameServer> listServers;

    /**
     * Constructor.
     */
    public BrowseScreen() {
        super();

        getStage().clear();

        Table table = new Table();
        table.setWidth(600);
        table.setHeight(600);
        getStage().addActor(table);

        listServers = new List<GameServer>(getSkin());

        table.add(listServers);

        table.row();

        textFieldName = new TextField("", getSkin());

        textFieldName.setMessageText("Nome");
        textFieldName.setMaxLength(10);

        table.add(textFieldName).width(150);

        table.row();

        TextButton buttonJoin = new TextButton("Join", getSkin());

        table.add(buttonJoin).width(130).height(30);

        buttonJoin.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Player player = new Player();
                player.setName(textFieldName.getText());
                GameController.getInstance().setPlayer(player);
                getMachine().changeState(new LobbyClientState());
                NetController.getInstance().setSelectedServerIp(listServers.getSelected().getIp());
            }

        });
    }

    /**
     * Draw screen.
     */
    public void draw() {
        super.draw();
    }

    /**
     * Updates screen.
     */
    public void update() {
        if (GameController.getInstance().getServers() != null) {
            GameServer[] serversArray = new GameServer[GameController.getInstance().getServers().size()];

            int index = 0;

            for (GameServer server : GameController.getInstance().getServers().values()) {
                serversArray[index] = server;

                index++;
            }

            listServers.setItems(serversArray);
        }
    }
}
