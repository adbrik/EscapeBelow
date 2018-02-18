package com.escapebelow.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.escapebelow.game.Screens.*;
import com.badlogic.gdx.Game.*;

public class EscapeBelow extends Game {
	public SpriteBatch batch;
    public static int V_WIDTH = 600;
    public static int V_HEIGHT = 350;
    public static float PPM = 100;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainScreen(this));
	}

    public void setPlayScreen(){
        setScreen(new PlayScreen(this));
    }

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
