package br.unb.shooter.screen;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Iterator;

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
import br.unb.shooter.util.Constants;

public class BrowseScreen extends Screen {

    private TextField textFieldName;

    private List<String> listServers;

    /**
     * Constructor.
     */
    public BrowseScreen() {
        super();
    }

    @Override
    public void create() {
        getStage().clear();

        Table table = new Table();
        table.setWidth(Constants.CAMERA_WIDTH);
        table.setHeight(Constants.CAMERA_HEIGHT);
        getStage().addActor(table);

        listServers = new List<String>(getSkin());

        listServers.setItems("", "", "", "");

        listServers.setSelectedIndex(-1);

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
     * Updates screen.
     */
    @Override
    public void update() {
        if (NetController.getInstance().getIps() != null) {
            String[] serversArray = new String[4];

            Collection<InetAddress> collection = NetController.getInstance().getIps().values();

            Iterator<InetAddress> iterator = collection.iterator();

            for (int index = 0; index < 4; index++) {
                if (iterator.hasNext()) {
                    serversArray[index] = iterator.next().getHostAddress();
                } else {
                    serversArray[index] = "";
                }
            }

            listServers.setItems(serversArray);
        }
    }
}
