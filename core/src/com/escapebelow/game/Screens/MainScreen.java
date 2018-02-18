package com.escapebelow.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.escapebelow.game.EscapeBelow;
import com.escapebelow.game.Screens.*;




/**
 * Created by Arik on 2018-02-17.
 */

public class MainScreen implements Screen {

    EscapeBelow game;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    OrthographicCamera camera;
    Viewport viewport;


    public  MainScreen (EscapeBelow game) {
        this.game = game;
        backgroundTexture = new Texture("Title.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        camera = new OrthographicCamera();
        viewport = new FillViewport(800, 400, camera);

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
        //game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundSprite,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        //you can move it to whatever position you want here
        camera.update();
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
