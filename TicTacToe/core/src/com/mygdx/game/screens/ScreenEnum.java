package com.mygdx.game.screens;

/**
 * Created by Nicolai on 17.04.2018.
 */

public enum ScreenEnum {
    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new MenuScreen();
        }
    },
    PLAY {
        public AbstractScreen getScreen(Object... params) {
            return new PlayScreen(3,3,3);
        }
    },
    SETTINGS {
        public AbstractScreen getScreen(Object... params) {
            return new SettingScreen();
        }
    };
    public abstract AbstractScreen getScreen(Object... params);
}
