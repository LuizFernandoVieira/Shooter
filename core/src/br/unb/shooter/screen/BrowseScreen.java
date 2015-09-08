package br.unb.shooter.screen;

import java.net.InetAddress;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.state.LobbyClientState;

public class BrowseScreen extends Screen {

    private TextField textFieldName;

    private List<String> listServers;

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

        listServers = new List<String>(getSkin());

        table.add(listServers).width(500);

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
                NetController.getInstance().setSelectedServerIp(listServers.getSelected());
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
        if (NetController.getInstance().getIps() != null) {
            String[] serversArray = new String[NetController.getInstance().getIps().size()];

            int index = 0;

            for (InetAddress ip : NetController.getInstance().getIps().values()) {
                serversArray[index] = ip.getHostAddress();

                index++;
            }

            listServers.setItems(serversArray);
        }
    }
}
