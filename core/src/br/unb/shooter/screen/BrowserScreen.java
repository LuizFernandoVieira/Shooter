package br.unb.shooter.screen;

import java.io.IOException;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.kryonet.Client;

import br.unb.shooter.net.ClientListener;

public class BrowserScreen extends Screen {
    private TextField textFieldChat;

    private TextButton buttonSend;

    private TextArea textAreaConsole;

    private String text;

    /**
     * Constructor.
     */
    public BrowserScreen() {
        getStage().clear();

        text = "";

        Table table = new Table();
        table.setBounds(0, 0, 640, 300);
        getStage().addActor(table);

        TextFieldStyle style = new TextFieldStyle(getSkin().getFont("default-font"), Color.WHITE,
                getSkin().getDrawable("cursor"), getSkin().getDrawable("selection"),
                getSkin().getDrawable("textfield"));

        textFieldChat = new TextField("", style);

        textFieldChat.setMessageText("Mensagem");
        textFieldChat.setMaxLength(50);

        table.add(textFieldChat).left().width(450).height(20);

        TextButtonStyle btnStyle = new TextButtonStyle(getSkin().getDrawable("default-round"),
                getSkin().getDrawable("default-round-down"), getSkin().getDrawable("default-round-down"),
                getSkin().getFont("default-font"));

        buttonSend = new TextButton("Enviar", btnStyle);

        buttonSend.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }

        });

        table.add(buttonSend).width(50).height(20);

        table.row();

        textAreaConsole = new TextArea("", style);
        textAreaConsole.setPrefRows(8);
        textAreaConsole.setDisabled(true);

        table.add(textAreaConsole).width(500);

        Client client = new Client();

        client.start();

        try {
            client.connect(5000, "127.0.0.1", 5000);
        } catch (IOException e) {
            // TODO: handle exception properly
            System.out.println("Erro");
        }

        ClientListener listener = new ClientListener();

        client.addListener(listener);

        client.sendTCP("CN Bruno");
    }

    public void draw() {
        super.draw();
    }

    public void update() {
        textAreaConsole.setText(text);
    }
}
