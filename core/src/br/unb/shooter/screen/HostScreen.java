package br.unb.shooter.screen;

import java.io.IOException;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import br.unb.shooter.net.ServerListener;

public class HostScreen extends Screen {

    private TextArea textAreaConsole;

    private String text;

    public HostScreen() {
        getStage().clear();

        text = "";

        Table table = new Table();
        table.setBounds(0, 0, 640, 300);
        getStage().addActor(table);

        TextFieldStyle style = new TextFieldStyle(getSkin().getFont("default-font"), Color.WHITE,
                getSkin().getDrawable("cursor"), getSkin().getDrawable("selection"),
                getSkin().getDrawable("textfield"));

        textAreaConsole = new TextArea("", style);
        textAreaConsole.setPrefRows(8);
        textAreaConsole.setDisabled(true);

        table.add(textAreaConsole).width(500);

        Server server = new Server();

        try {
            server.bind(5000);
            server.start();

            Listener listener = new ServerListener();

            ((ServerListener) listener).setScreen(this);

            server.addListener(listener);
        } catch (IOException e) {
            return;
        }
    }

    public void draw() {
        super.draw();
    }

    public void update() {
        textAreaConsole.setText(text);
    }

    public void concat(String text) {
        this.text = this.text.concat(text);
    }
}
