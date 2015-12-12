package br.unb.shooter.controller;

public class DebugController {

    private static DebugController instance;

    private Boolean active = false;

    public static DebugController getInstance() {
        if (instance == null) {
            instance = new DebugController();
        }

        return instance;
    }

    public static void setInstance(DebugController instance) {
        DebugController.instance = instance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void changeActive() {
        active = !active;
    }

}
