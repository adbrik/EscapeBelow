package com.escapebelow.game.Scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.escapebelow.game.EscapeBelow;

/**
 * Created by Arik on 2018-02-17.
 */

public class Hud implements Disposable{
    public int lives;
    public Stage stage;
    public Viewport viewport;

    Label livesLabel;
    Label livesStrLabel;

    public Hud(SpriteBatch sb){
        lives = 5;

        viewport = new FitViewport(EscapeBelow.V_WIDTH,EscapeBelow.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        livesStrLabel = new Label(String.format("LIVES"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label(String.format("%01d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(livesStrLabel).expandX().padTop(10);
        table.row();
        table.add(livesLabel).expandX().padTop(10);

        stage.addActor(table);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
