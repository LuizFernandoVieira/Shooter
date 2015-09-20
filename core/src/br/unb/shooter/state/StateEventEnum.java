package br.unb.shooter.state;

public enum StateEventEnum {
    KEY_ESC(1, "key_esc"), //
    TIMER_END(2, "timer_end"), //
    STATE_END(3, "state_end"), //
    BUTTON_SINGLEPLAYER(4, "button_singleplayer"), //
    BUTTON_MULTIPLAYER(5, "button_multiplayer"), //
    BUTTON_CUTSCENE(6, "button_cutscene"), //
    BUTTON_CREDITS(7, "button_credits"), //
    BUTTON_SETTINGS(8, "button_settings"), //
    BUTTON_QUIT(9, "button_quit"), //
    BUTTON_SELECT(10, "button_select"), //
    BUTTON_BACK(11, "button_back"), //
    BUTTON_HOST(12, "button_host"), //
    BUTTON_BROWSE(13, "button_browse"), //
    BUTTON_START(14, "button_start"), //
    BUTTON_JOIN(15, "button_join"), //
    START_GAME(16, "start_game"); //

    private Integer id;

    private String name;

    private StateEventEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
