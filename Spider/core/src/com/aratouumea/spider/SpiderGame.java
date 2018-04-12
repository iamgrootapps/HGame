package com.aratouumea.spider;

import com.aratouumea.Direction;
import com.aratouumea.enemies.ant.AntObject;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpiderGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private SpriteBatch batchMenu;
    private SpiderObject spider;
    private AntObject antObject;
    private OrthographicCamera camera;
    private float timer = 0;
    private float jumpTimer =0;
    private Sound sound;
    private boolean soundFlag = false;

    private Texture spidertmp;
    private Texture antTmp;

    @Override
    public void create() {
        batch = new SpriteBatch();
        batchMenu = new SpriteBatch();
        spider = new SpiderObject();
        antObject= new AntObject();
        antObject.x=300;
        antObject.y=400;
        spider.x = 200;
        spider.y = 100;
        spidertmp = new Texture("heart.png");
        camera = new OrthographicCamera(1200, 700);
        sound = Gdx.audio.newSound(Gdx.files.internal("spiderChattering.ogg"));

    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(255, 255, 255, 255);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(spider.getTexture(), spider.x, spider.y);
        batch.draw(spidertmp, 400, 400);
        batch.draw(antObject.getTexture(), antObject.x, antObject.y);
        batch.end();
        batchMenu.begin();
        batchMenu.draw(spider.getLifeTexture(), 0, 0);
        batchMenu.end();

    }

    private void update() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        camera.position.set(spider.x + spider.getWidth() / 2, spider.y + spider.getHeight() / 2, 0);
        antObject.move();
        if (keyEvent()) {
            if (!soundFlag) {
                sound.play();
                sound.loop();
                soundFlag = true;
            }
        } else {
            sound.stop();
            soundFlag = false;
        }

    }

    private boolean keyEvent() {
        boolean result = false;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (jumpTimer>=4) {
                spider.setJump();
                jumpTimer=0;
            }
        }
        if (!spider.jump()) {
            
            if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.W)) {
                spider.x -= spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                spider.y += spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                changeTexture(Direction.NW);
                result = true;
            } else {

                if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                    spider.x += spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                    spider.y += spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                    result = true;
                    changeTexture(Direction.NE);
                } else {
                    if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
                        spider.x += spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                        spider.y -= spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                        result = true;
                        changeTexture(Direction.SE);
                    } else {

                        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
                            spider.x -= spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                            spider.y -= spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                            result = true;
                            changeTexture(Direction.SW);
                        } else {
                            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                                spider.x += spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                                changeTexture(Direction.E);
                                result = true;
                            }
                            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                                spider.y += spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                                changeTexture(Direction.N);
                                result = true;
                            }
                            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                                spider.x -= spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                                changeTexture(Direction.W);
                                result = true;
                            }
                            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                                spider.y -= spider.getDetails().getSpeed() * Gdx.graphics.getDeltaTime();
                                changeTexture(Direction.S);
                                result = true;
                            }
                        }
                    }
                }

            }
        }
        timer += 5 * Gdx.graphics.getDeltaTime();
        jumpTimer+= 5*Gdx.graphics.getDeltaTime();
        return result;
    }

    private void changeTexture(Direction direction) {
        spider.changeDirection(direction);
        if (timer > 1) {
            timer = 0;
            spider.incrementCounter();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        spider.dispose();
        sound.dispose();
    }
}
