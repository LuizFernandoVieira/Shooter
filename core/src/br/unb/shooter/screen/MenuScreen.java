package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import br.unb.shooter.state.GameState;
import br.unb.shooter.state.MultiplayerState;

public class MenuScreen extends Screen {

    private Table table;

    /**
     * Constructor.
     */
    public MenuScreen() {
        super();

        table = new Table();
        table.setWidth(600);
        table.setHeight(600);

        getStage().addActor(table);

        TextButton buttonPlay = new TextButton("Singleplayer", getSkin());

        table.add(buttonPlay).width(130).height(30);
        table.row();

        TextButton buttonMultiplayer = new TextButton("Multiplayer", getSkin());

        table.add(buttonMultiplayer).width(130).height(30);
        table.row();

        TextButton buttonQuit = new TextButton("Quit", getSkin());

        table.add(buttonQuit).width(130).height(30);

        buttonPlay.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new GameState());
            }

        });

        buttonMultiplayer.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getMachine().changeState(new MultiplayerState());
            }

        });

        buttonQuit.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }

        });
    }

    /**
     * Draw screen.
     */
    public void draw() {
        super.draw();
    }

}
