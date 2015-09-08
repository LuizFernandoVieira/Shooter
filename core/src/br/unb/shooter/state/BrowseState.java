package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.ServerDiscoverer;
import br.unb.shooter.screen.BrowseScreen;

public class BrowseState implements IState {

    private BrowseScreen screen;

    private Thread thread;

    private ServerDiscoverer discoverer;

    @Override
    public void create(StateMachine machine) {
        screen = new BrowseScreen();
        screen.setMachine(machine);

        System.setProperty("java.net.preferIPv4Stack", "true");

        NetController.getInstance().createClient();

        discoverer = new ServerDiscoverer();

        thread = new Thread(discoverer);

        thread.start();
    }

    @Override
    public void update() {
        screen.update();
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
        screen.dispose();
        discoverer.setExecute(false);
    }

}
