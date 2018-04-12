/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aratouumea.spider;

import com.aratouumea.AnimationContainer;
import com.aratouumea.Direction;
import com.aratouumea.TexturesPaths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Aratouumea
 */
public class SpiderObject extends Rectangle {

    private final int jumpCounterValue = 10;
    private Texture spiderRestTexture;
    private int counter;
    private AnimationContainer leftSpider;
    private AnimationContainer rightSpider;
    private AnimationContainer lifeSpider;
    private SpiderDetails details;
    private Direction actualDirection;
    private int jumpCounter;
    private boolean isJumping;

    public SpiderObject() {
        details = new SpiderDetails(1000, 200, 100, 1200);
        actualDirection = Direction.S;
        spiderRestTexture = new Texture("spider1.jpg");
        initializeTextures();
        this.width = spiderRestTexture.getWidth();
        this.height = spiderRestTexture.getHeight();
        counter = 0;
    }

    private void initializeTextures() {
        ArrayList<Texture> leftTextures = new ArrayList<Texture>() {
            {
                add(new Texture(TexturesPaths.SPIDER_LEFT_S));
                add(new Texture(TexturesPaths.SPIDER_LEFT_SE));
                add(new Texture(TexturesPaths.SPIDER_LEFT_E));
                add(new Texture(TexturesPaths.SPIDER_LEFT_NE));
                add(new Texture(TexturesPaths.SPIDER_LEFT_N));
                add(new Texture(TexturesPaths.SPIDER_LEFT_NW));
                add(new Texture(TexturesPaths.SPIDER_LEFT_W));
                add(new Texture(TexturesPaths.SPIDER_LEFT_SW));
            }
        };
        leftSpider = new AnimationContainer(leftTextures);
        ArrayList<Texture> rightTextures = new ArrayList<Texture>() {
            {
                add(new Texture(TexturesPaths.SPIDER_RIGHT_S));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_SE));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_E));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_NE));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_N));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_NW));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_W));
                add(new Texture(TexturesPaths.SPIDER_RIGHT_SW));
            }
        };
        rightSpider = new AnimationContainer((rightTextures));

        ArrayList<Texture> lifeTextures = new ArrayList<Texture>() {
            {
                add(new Texture(TexturesPaths.SPIDER_LIFE_1));
                add(new Texture(TexturesPaths.SPIDER_LIFE_2));
                add(new Texture(TexturesPaths.SPIDER_LIFE_3));
                add(new Texture(TexturesPaths.SPIDER_LIFE_4));
                add(new Texture(TexturesPaths.SPIDER_LIFE_5));
                add(new Texture(TexturesPaths.SPIDER_LIFE_6));
                add(new Texture(TexturesPaths.SPIDER_LIFE_7));
                add(new Texture(TexturesPaths.SPIDER_LIFE_8));
            }
        };
        lifeSpider = new AnimationContainer(lifeTextures);
    }

    public Texture getTexture() {
        if (counter == 0) {
            return leftSpider.getTextureAtDirection(actualDirection);
        }
        return rightSpider.getTextureAtDirection(actualDirection);
    }

    public void changeDirection(Direction direction) {
        actualDirection = direction;
    }

    public void incrementCounter() {
        if (counter == 0) {
            counter++;
        } else {
            counter--;
        }
    }

    public void dispose() {
        spiderRestTexture.dispose();
        leftSpider.dispose();
        rightSpider.dispose();
    }

    public SpiderDetails getDetails() {
        return details;
    }

    public Texture getLifeTexture() {
        return lifeSpider.getTextureAtPosition(details.getLifePosition());
    }

    public boolean jump() {

        if (isJumping) {
            if (jumpCounter > 0) {
                if (actualDirection.equals(Direction.E)) {
                    this.x += this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                }
                if (actualDirection.equals(Direction.W)) {
                    this.x -= this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                }
                if (actualDirection.equals(Direction.S)) {
                    this.y -= this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                }
                if (actualDirection.equals(Direction.N)) {
                    this.y += this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                }
            if(actualDirection.equals(Direction.SE)){
                this.x += this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                this.y -= this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
            }
            if(actualDirection.equals(Direction.SW)){
                this.x -= this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                this.y -= this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
            }
            if(actualDirection.equals(Direction.NE)){
                this.x += this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                this.y += this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
            }
            if(actualDirection.equals(Direction.NW)){
                this.x -= this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
                this.y += this.getDetails().getJumpRange() * Gdx.graphics.getDeltaTime();
            }
                jumpCounter--;
                return true;
            } else {
                isJumping = false;
            }
        }
         return false;
    }

    public boolean isJumping() {

        return isJumping;
    }

    public void setJump() {
        jumpCounter = jumpCounterValue;
        isJumping = true;
    }

}
