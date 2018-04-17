package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Singleton.Singleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.managers.GameScreenManager;

import static com.mygdx.game.screens.ScreenEnum.SETTINGS;

/**
 * Created by eiriksandberg on 22.01.2018.
 */

public class MenuScreen extends AbstractScreen {

    private Skin skin;
    private TextButton playButton;
    private TextButton settingsButton;
    private TextButton powerUpButton;
    private TextButton highscoreButton;
    private TextButton exitButton;
    private Singleton singleton = Singleton.getInstance();
    //private MyGdxGame game;


    public MenuScreen(){
        super();
        createSkin();
        initializeButtons();
    }

    @Override
    public void buildStage() {
        addActor(playButton);
        addActor(settingsButton);
        addActor(powerUpButton);
        addActor(highscoreButton);
        addActor(exitButton);
        handleInput();

    }

    private void handleInput() {
        playButton.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer,int button){
                        System.out.println("play clicked");
                        GameScreenManager.getInstance().showScreen(ScreenEnum.PLAY);
                        return false;
                    }
                }
        );
        settingsButton.addListener(
                        new InputListener() {
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                                System.out.println("settingg clicked");
                                GameScreenManager.getInstance().showScreen(ScreenEnum.SETTINGS);
                                return false;
                            }
                        }
                );
        if (playButton.isPressed()) {
            singleton.resetSingleton();
            System.out.println(" play klikk");
            GameScreenManager.getInstance().showScreen(ScreenEnum.PLAY, 0);
            dispose();
        }
        if (exitButton.isPressed()) {
            Gdx.app.exit();
        }
        if (settingsButton.isPressed() ) {
            System.out.println(" setting klikk");
            GameScreenManager.getInstance().showScreen(ScreenEnum.SETTINGS, 0);

        }
    }

    //    Create skin for buttons
    private void createSkin() {
        // Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        // Create a texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 15, Pixmap.Format.RGB888);
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

    private void initializeButtons() {
        playButton = new TextButton("Play now", skin);
        playButton.setPosition((Gdx.graphics.getWidth() - playButton.getWidth()) / 2, ((Gdx.graphics.getHeight() + 4 * playButton.getHeight()) / 2));

        settingsButton = new TextButton("Settings", skin);
        settingsButton.setPosition((Gdx.graphics.getWidth() - settingsButton.getWidth()) / 2, ((Gdx.graphics.getHeight() + playButton.getHeight()) / 2));

        powerUpButton = new TextButton("Power ups", skin);
        powerUpButton.setPosition((Gdx.graphics.getWidth() - powerUpButton.getWidth()) / 2, ((Gdx.graphics.getHeight() - 2 * powerUpButton.getHeight()) / 2));

        highscoreButton = new TextButton("Highscore", skin);
        highscoreButton.setPosition((Gdx.graphics.getWidth() - highscoreButton.getWidth()) / 2, ((Gdx.graphics.getHeight() - 5 * highscoreButton.getHeight()) / 2));

        exitButton = new TextButton("Exit", skin);
        exitButton.setPosition((Gdx.graphics.getWidth() - exitButton.getWidth()) / 2, (Gdx.graphics.getHeight() / 2) - 4 * exitButton.getHeight());
    }


}




