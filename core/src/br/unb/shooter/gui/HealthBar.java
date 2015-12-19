package br.unb.shooter.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

import br.unb.shooter.entity.Enemy;

public class HealthBar extends Actor {
    
    private ShapeRenderer shape;
    static private boolean projectionMatrixSet;

    public HealthBar() {
        shape = new ShapeRenderer();
        projectionMatrixSet = false;
    }
    
    public void draw(Batch batch, Enemy enemy) {
        batch.end();
        
        if(!projectionMatrixSet){
            shape.setProjectionMatrix(batch.getProjectionMatrix());
        }
        
        shape.begin(ShapeType.Filled);
        shape.setColor(Color.BLACK);
        shape.rect(enemy.getX()+5,enemy.getY()+60,25,5);
        
        if(enemy.getHealth() > 0){
            shape.setColor(Color.RED);
            shape.rect(enemy.getX()+5, enemy.getY()+60, (int) (25*enemy.getHealth())/100, 5);
        }

        shape.end();
        
        batch.begin();
    }
    
}
