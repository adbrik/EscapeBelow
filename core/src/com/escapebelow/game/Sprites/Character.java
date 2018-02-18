package com.escapebelow.game.Sprites;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.escapebelow.game.EscapeBelow;
import com.escapebelow.game.Screens.PlayScreen;

/**
 * Created by Arik on 2018-02-18.
 */

public class Character extends Sprite {
    public enum State {JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion characterStand;
    private Animation <TextureRegion>marioRun;
    private Animation marioJump;
    private float stateTimer;
    private boolean runningRight;


    public  Character(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("Panda"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for ( int i = 1; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), i* 33,1,32,32));

        marioRun = new Animation<TextureRegion>((0.2f),frames);
        frames.clear();

        defineCharacter();
        characterStand = new TextureRegion(getTexture(),1,1, 32,32);
        setBounds(1,1,32 / EscapeBelow.PPM, 32 / EscapeBelow.PPM);
        setRegion(characterStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame (float dt){
        currentState = getState();

        TextureRegion region1;
        switch (currentState) {
            case RUNNING:
                region1 = marioRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region1 = characterStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region1.isFlipX()){
            region1.flip(true, false);
            runningRight = false;
        }

        //if mario is running right and the texture isnt facing right... flip it.
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region1.isFlipX()){
            region1.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer +dt : 0;
        previousState = currentState;
        return  region1;
    }

    public State getState(){
        if (b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else
            return State.STANDING;
    }

    public void defineCharacter(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(400/EscapeBelow.PPM,1050/EscapeBelow.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/EscapeBelow.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
