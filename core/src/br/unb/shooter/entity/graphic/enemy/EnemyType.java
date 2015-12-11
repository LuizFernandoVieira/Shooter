package br.unb.shooter.entity.graphic.enemy;

public enum EnemyType {
    NORMAL(1, "normal"), //
    SHOOTER(2, "shooter");

    private Integer id;

    private String name;

    private EnemyType(Integer id, String name) {
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
