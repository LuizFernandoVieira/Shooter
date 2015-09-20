package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.ServerDiscoverer;
import br.unb.shooter.screen.BrowseScreen;

public class BrowseState extends State {

    private Thread thread;

    private ServerDiscoverer discoverer;

    @Override
    public void create(StateMachine machine) {
        setMachine(machine);
        setScreen(new BrowseScreen());
        getScreen().setMachine(machine);
        getScreen().create();

        System.setProperty("java.net.preferIPv4Stack", "true");

        NetController.getInstance().createClient();

        discoverer = new ServerDiscoverer();

        thread = new Thread(discoverer);

        thread.start();
    }

    @Override
    public void dispose() {
        discoverer.setExecute(false);
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_JOIN)) {
            getMachine().changeState(new LobbyClientState());
        }
    }

}
