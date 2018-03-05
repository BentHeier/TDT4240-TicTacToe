package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Singleton.Singleton;

/**
 * Created by eiriksandberg on 22.01.2018.
 */

public class MenuState implements State {
    BitmapFont font = new BitmapFont();
    private Singleton singleton = Singleton.getInstance();
    private GameStateManager gsm;


    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            gsm.set(new PlayState(gsm));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            gsm.set(new PlayStateTask2(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "Press 1 or 2 on the keyboard for fun", 100, MyGdxGame.HEIGHT/2);
        font.draw(sb, "Singleton value = " + singleton.getSingletonValue(), MyGdxGame.WIDTH-150, MyGdxGame.HEIGHT-40);
        sb.end();
    }

    @Override
    public void dispose() {}
}