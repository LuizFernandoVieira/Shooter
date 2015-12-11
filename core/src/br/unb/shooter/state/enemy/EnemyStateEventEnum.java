package br.unb.shooter.state.enemy;

public enum EnemyStateEventEnum {
    IDLE(1, "key_esc"), //
    WALKING(2, "timer_end"); //

    private Integer id;

    private String name;

    private EnemyStateEventEnum(Integer id, String name) {
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
