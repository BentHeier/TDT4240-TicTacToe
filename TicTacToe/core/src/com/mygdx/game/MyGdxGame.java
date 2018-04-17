package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.GameScreenManager;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.ScreenEnum;
import com.mygdx.game.screens.SettingScreen;

public class MyGdxGame extends Game {

	public static final String TITLE = "Tic Tac Toe UNLEASHED";

	//Game Variables
	public static final int WIDTH = 300;
	public static final int HEIGHT = 450;

	//Managers
	public GameScreenManager gsm;

	//Batches
	public SpriteBatch batch;

	private Texture img;
	
	@Override
	//create each Screen one time
	public void create () {
		batch = new SpriteBatch();
		GameScreenManager.getInstance().initialize(this);
		GameScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//super.update(Gdx.graphics.getDeltaTime());
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		img.dispose();
	}
}
