/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aratouumea.enemies.ant;

import com.aratouumea.AnimationContainer;
import com.aratouumea.Direction;
import com.aratouumea.TexturesPaths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

/**
 *
 * @author Aratouumea
 */
public class AntObject extends Rectangle {

    private AnimationContainer animationContainer;
    private Direction direction;
    private Integer speed = 400;
    private Integer counter = 0;
    private Integer moveCounter=0;
    private boolean xOrY=false;
    private boolean minusOrPlus=false;

    public AntObject() {
        direction = Direction.N;
        initializeAnimation();
        this.width = animationContainer.getTextureAtDirection(Direction.N).getWidth();
        this.height = animationContainer.getTextureAtDirection(Direction.N).getHeight();
    }

    private void initializeAnimation() {
        animationContainer = new AnimationContainer();
        animationContainer.setTextureAtDirection(Direction.S, new Texture(TexturesPaths.ANT_S));
        animationContainer.setTextureAtDirection(Direction.E, new Texture(TexturesPaths.ANT_E));
        animationContainer.setTextureAtDirection(Direction.N, new Texture(TexturesPaths.ANT_N));
        animationContainer.setTextureAtDirection(Direction.W, new Texture(TexturesPaths.ANT_W));
    }

    public Texture getTexture() {
        return animationContainer.getTextureAtDirection(direction);
    }

    public void move() {
        counter++;
        
        
        if (counter == 10) {
            moveCounter++;
            Random generator = new Random();
            if(moveCounter==4){
                xOrY= generator.nextBoolean();
                minusOrPlus=generator.nextBoolean();
                moveCounter=0;
            }
            if (xOrY) {
                moveX(minusOrPlus);
            } else {
                moveY(minusOrPlus);
            }
            counter = 0;
        }
    }

    private void moveX(boolean value) {
        if (value) {
            this.x += speed * Gdx.graphics.getDeltaTime();
            direction=Direction.E;
        } else {
            this.x -= speed * Gdx.graphics.getDeltaTime();
            direction=Direction.W;
        }
    }

    private void moveY(boolean value) {
        if (value) {
            this.y += speed * Gdx.graphics.getDeltaTime();
            direction= Direction.N;
        } else {
            direction=Direction.S;
            this.y -= speed * Gdx.graphics.getDeltaTime();
        }
    }

    public void attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
