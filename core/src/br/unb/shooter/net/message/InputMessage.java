package br.unb.shooter.net.message;

import br.unb.shooter.util.Constants;

public class InputMessage extends Message {

    private Integer targetX;

    private Integer targetY;

    private Boolean leftActionButton;

    private Boolean rightActionButton;

    private Boolean moveUp;

    private Boolean moveDown;

    private Boolean moveRight;

    private Boolean moveLeft;

    public InputMessage(String message) {
        this.id = MessageEnum.INPUT.getId();
        translate(message);
    }

    @Override
    public void execute() {
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);
    }

}
