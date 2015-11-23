package br.unb.shooter.gamepad;

import com.badlogic.gdx.controllers.PovDirection;

public class XBox360Pad {
    /*
     * if (controller.getName().toLowerCase().contains("xbox") &&
     * controller.getName().contains("360"))
     */

    // public static final String ID = "XBOX 360 For Mac (Controller)";
    public static final int BUTTON_X = 14;
    public static final int BUTTON_Y = 15;
    public static final int BUTTON_A = 12;
    public static final int BUTTON_B = 13;
    public static final int BUTTON_BACK = 5;
    public static final int BUTTON_START = 4;
    public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
    public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
    public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
    public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;
    public static final int BUTTON_LB = 8;
    public static final int BUTTON_L3 = 6;
    public static final int BUTTON_RB = 9;
    public static final int BUTTON_R3 = 7;
    public static final int AXIS_LEFT_X = 2; // -1 left | +1 right
    public static final int AXIS_LEFT_Y = 3; // -1 up | +1 down
    public static final int AXIS_LEFT_TRIGGER = 4; // 0 to 1f
    public static final int AXIS_RIGHT_X = 4; // -1 left | +1 right
    public static final int AXIS_RIGHT_Y = 5; // -1 up | +1 down
    public static final int AXIS_RIGHT_TRIGGER = 4; // 0 to -1f
}