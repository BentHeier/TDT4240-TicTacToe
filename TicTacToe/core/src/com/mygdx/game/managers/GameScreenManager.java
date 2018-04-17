package com.mygdx.game.managers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.AbstractScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.ScreenEnum;
import com.mygdx.game.screens.SettingScreen;

import java.util.HashMap;

/**
 * Created by Nicolai on 17.04.2018.
 */

public class GameScreenManager {

    private static GameScreenManager instance;
    private MyGdxGame game;

    private GameScreenManager(){
        super();
    }

    public static GameScreenManager getInstance(){
        if (instance == null){
            instance = new GameScreenManager();
        }
        return instance;
    }

    public void initialize(MyGdxGame game){
        this.game = game;
    }

    public void showScreen(ScreenEnum screenEnum , Object... params) {

        Screen currentScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen(params);

        newScreen.buildStage();
        game.setScreen(newScreen);

        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
