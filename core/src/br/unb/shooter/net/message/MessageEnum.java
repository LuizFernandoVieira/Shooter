package br.unb.shooter.net.message;

public enum MessageEnum {
    INPUT("input", "Input"), //
    UPDATE("update", "Update"), //
    TALK("talk", "Talk"); //

    private String id;

    private String name;

    private MessageEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
