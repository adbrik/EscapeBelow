package com.escapebelow.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.escapebelow.game.EscapeBelow;
import com.escapebelow.game.Screens.*;



/**
 * Created by Arik on 2018-02-17.
 */

public class MainScreen implements Screen {

    EscapeBelow game;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private SpriteBatch spriteBatch;

    public  MainScreen (EscapeBelow game){
        this.game = game;
        backgroundTexture = new Texture("Title.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
    }

    @Override
    public void show() {

    }
    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            game.setPlayScreen();
        }
    }

    public void update(float dt){
        handleInput(dt);
    }
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        backgroundSprite.draw(game.batch);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
