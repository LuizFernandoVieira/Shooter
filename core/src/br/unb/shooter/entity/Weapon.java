package br.unb.shooter.entity;

public abstract class Weapon extends Entity {

    private Entity owner;

    /**
     * Constructor.
     */
    public Weapon() {
        owner = null;
        setX(0f);
        setY(0f);
    }

    public abstract void update();

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

}
