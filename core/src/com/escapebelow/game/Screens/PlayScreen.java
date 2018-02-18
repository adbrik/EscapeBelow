package com.escapebelow.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.escapebelow.game.EscapeBelow;
import com.escapebelow.game.Scene.Hud;
import com.escapebelow.game.Sprites.Character;

/**
 * Created by Arik on 2018-02-17.
 */

public class PlayScreen implements Screen {
    TextureAtlas atlas;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    private Character player;

    private EscapeBelow game;

    public PlayScreen (EscapeBelow game){
        atlas = new TextureAtlas("Panda.pack");

        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(EscapeBelow.V_WIDTH / EscapeBelow.PPM ,EscapeBelow.V_HEIGHT/	EscapeBelow.PPM,gameCam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map11.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ EscapeBelow.PPM);
        gameCam.position.set(400/EscapeBelow.PPM,1000/EscapeBelow.PPM, 0);

        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Gdx.input.setOnscreenKeyboardVisible(true);

        player = new Character(world, this);

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            object.setVisible(false);
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2)/EscapeBelow.PPM, (rect.getY() + rect.getHeight()/2)/EscapeBelow.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth() / 2)/EscapeBelow.PPM, (rect.getHeight() /2)/EscapeBelow.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched() && Gdx.input.getX() > 600 && Gdx.input.getY() > 360){
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isTouched() && Gdx.input.getX() < 600 && Gdx.input.getY() > 360){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isTouched() && Gdx.input.getY() < 360){
            player.b2body.applyLinearImpulse(new Vector2(0, 0.5f), player.b2body.getWorldCenter(), true);
        }
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 6,2);

        player.update(dt);

        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.position.y = player.b2body.getPosition().y;

        gameCam.update();
        renderer.setView(gameCam);
    }


    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        map.dispose();
        renderer.dispose();
        hud.dispose();
        b2dr.dispose();

    }
}
