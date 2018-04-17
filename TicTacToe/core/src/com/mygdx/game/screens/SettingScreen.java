package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.managers.GameScreenManager;

/**
 * Created by Nicolai on 16.04.2018.
 */

public class SettingScreen extends AbstractScreen{

    private TextButton threeButton;
    private TextButton fiveButton;
    private TextButton sevenButton;
    private TextButton backButton;
    private Skin skin;

    public SettingScreen(){
        super();
        createSkin(); // Create skin for buttons
        initializeButtons();
        //Add buttons to stage


    }

    @Override
    public void buildStage() {
        addActor(threeButton);
        addActor(fiveButton);
        addActor(sevenButton);
        addActor(backButton);
        handleInput();

    }


    public void handleInput(){
        backButton.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                        System.out.println("back clicked");
                        GameScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                        return false;
                    }
                }
        );
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

    private void initializeButtons(){
        threeButton = new TextButton("3x3 Grid", skin);
        threeButton.setPosition((Gdx.graphics.getWidth() - threeButton.getWidth())/2, ((Gdx.graphics.getHeight() + 4 * threeButton.getHeight())/2));

        fiveButton = new TextButton("5x5 Grid", skin);
        fiveButton.setPosition((Gdx.graphics.getWidth() - fiveButton.getWidth())/2, ((Gdx.graphics.getHeight() + fiveButton.getHeight())/2));

        sevenButton = new TextButton("7x7 Grid", skin);
        sevenButton.setPosition((Gdx.graphics.getWidth() - sevenButton.getWidth())/2, ((Gdx.graphics.getHeight() - 2 * sevenButton.getHeight())/2));

        backButton = new TextButton("Back", skin);
        sevenButton.setPosition((Gdx.graphics.getWidth() - backButton.getWidth())/2, ((Gdx.graphics.getHeight() - 4 * backButton.getHeight())/2));
    }

    private void createSkin(){
        // Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        // Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/15, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

}
