package br.unb.shooter.util;

public class Constants {
    public static final String SPACE = " ";
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final int CAMERA_WIDTH = 600;
    public static final int CAMERA_HEIGHT = 600;
    public static final String NEW_LINE = "\n";

    public static final Float PLAYER_VELOCITY = 3f;
    public static final Float PLAYER_WALKING_WIDTH = 42f;
    public static final Float PLAYER_WALKING_HEIGHT = 66f;
    public static final Float PLAYER_IDLE_WIDTH = 42f;
    public static final Float PLAYER_IDLE_HEIGHT = 64f;
    public static final Float PLAYER_OFFSET_X = PLAYER_IDLE_WIDTH / 2;
    public static final Float PLAYER_OFFSET_Y = PLAYER_IDLE_HEIGHT / 2;

    public static final float WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT = 15f;
    public static final float WEAPON_OFFSET_FROM_PLAYER_X_FACING_LEFT = 27f;
    public static final float WEAPON_OFFSET_FROM_PLAYER_Y = 9f;

    public static final float WEAPON_WIDTH = 39f;
    public static final float WEAPON_HEIGHT = 28f;

    public static final float TARGET_MARK_WIDTH = 32f;
    public static final float TARGET_MARK_HEIGHT = 32f;

    public static final float SHOT_WIDTH = 12f;
    public static final float SHOT_HEIGHT = 12f;
    public static final float SHOT_VELOCITY = 8f;

    public static final Boolean CONTROLLER = false;

    public static String convertBoolean(Boolean bool) {
        return bool ? "1" : "0";
    }
}
