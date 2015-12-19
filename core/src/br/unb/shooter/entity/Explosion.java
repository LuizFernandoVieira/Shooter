package br.unb.shooter.entity;

public class Explosion extends Entity {

    public Explosion() {
    }

    public Explosion(Integer id, Float x, Float y) {
        super(x, y);
        this.id = id;
    }
}
