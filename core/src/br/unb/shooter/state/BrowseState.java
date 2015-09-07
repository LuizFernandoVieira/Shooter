package br.unb.shooter.state;

import com.badlogic.gdx.utils.TimeUtils;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.BrowseScreen;

public class BrowseState implements IState {

    private BrowseScreen screen;

    private long updateTime;

    @Override
    public void create(StateMachine machine) {
        screen = new BrowseScreen();
        screen.setMachine(machine);

        System.setProperty("java.net.preferIPv4Stack", "true");

        NetController.getInstance().createClient();

        updateTime = TimeUtils.millis();
    }

    @Override
    public void update() {
        if (TimeUtils.timeSinceMillis(updateTime) > 1000L) {
            NetController.getInstance().discoverHosts();
            NetController.getInstance().askServerNames();
            updateTime = TimeUtils.millis();
        }
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }

}
