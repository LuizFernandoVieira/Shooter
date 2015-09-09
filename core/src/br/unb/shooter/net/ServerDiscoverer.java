package br.unb.shooter.net;

import br.unb.shooter.controller.NetController;

public class ServerDiscoverer implements Runnable {

    private Boolean execute;

    /**
     * Constructor.
     */
    public ServerDiscoverer() {
        NetController.getInstance().createClient();

        execute = true;
    }

    @Override
    public void run() {
        while (execute) {
            NetController.getInstance().discoverHosts();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public Boolean getExecute() {
        return execute;
    }

    public void setExecute(Boolean execute) {
        this.execute = execute;
    }

}
