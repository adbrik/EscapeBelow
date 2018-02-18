package com.escapebelow.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.escapebelow.game.EscapeBelow;

/**
 * Created by Arik on 2018-02-18.
 */

public class Character extends Sprite {
    public World world;
    public Body b2body;

    public  Character(World world){
        this.world = world;
        defineMario();
    }

    public void defineMario(){
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
