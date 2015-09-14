package br.unb.shooter.state;

import br.unb.shooter.screen.StartScreen;

public class StartState extends State {

    public StartState() {
        setScreen(new StartScreen());
    }

}
