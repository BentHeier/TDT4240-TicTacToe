package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Singleton.Singleton;
import com.mygdx.game.domain.Board;
import com.mygdx.game.domain.Player;
import com.mygdx.game.domain.TileState;
import com.mygdx.game.managers.GameScreenManager;
import com.mygdx.game.powerups.ExpandBoardPowerup;
import com.mygdx.game.domain.GameLogic;
import com.mygdx.game.sprites.Mark;
import com.mygdx.game.powerups.Powerup;
import com.mygdx.game.sprites.Tile;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

import static com.mygdx.game.screens.ScreenEnum.MAIN_MENU;

/**
 * Created by eiriksandberg on 22.01.2018.
 */

public class PlayScreen extends AbstractScreen {

    private Singleton singleton = Singleton.getInstance();
    private Skin skin;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Table table = new Table();
    private char brett[][];
    private GameLogic gameLogic;
    private String midBoard;
    private Board matrix;
    private int amountToWin;
    private TextButton plButton;


    public PlayScreen(int rows, int cols, int amountToWin) {
        super();
        initializeButtons();
        createSkin();
        this.amountToWin = amountToWin;
        matrix = new Board(rows, cols);
        singleton.setBoard(matrix);
        singleton.setTiles(matrix.generateBoard(singleton.getBoard()));
        singleton.setBoardTiles(matrix.setBoardTiles());
        gameLogic = new GameLogic(new char[rows][cols], amountToWin);

        // Mock players with powerup
        ArrayList<Powerup> powerups = new ArrayList<Powerup>();
        powerups.add(new ExpandBoardPowerup());
        players.add(new Player(0, powerups));
        players.add(new Player(1, powerups));

    }

        /*for (Powerup pu : players.get(singleton.getPlayerState()).getPowerups()){
            pu.update(dt);
        }*/

            // Powerups
       /* Player activePlayer = players.get(singleton.getPlayerState());
        if (activePlayer.havePowerupsAvailable()){
            renderPowerups(activePlayer.getPowerups(), sb);
        }*/


/*    public void update (float delta) {
        for (Tile t : singleton.getTiles()) {
            t.update(delta);
        }
        if (gameLogic.hasWinner()) {
            System.out.println("Vinneren er spiller " + gameLogic.getWinner());
            System.out.println(gameLogic.printBoard());
            GameScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            dispose();
        } else if (!gameLogic.hasWinner() && gameLogic.getWinner() == 'D') {
            System.out.println("UAVGJORT");
            GameScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            GameScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
            dispose();
        }
    }*/

    @Override
    public void buildStage() {
        addActor(plButton);
        plButton.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                        System.out.println("play clicked");
                        GameScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
                        return false;
                    }
                }
        );
    }

    private void initializeButtons() {
        System.out.println(skin);
        plButton = new TextButton("Play now", skin);
        plButton.setPosition((Gdx.graphics.getWidth() - plButton.getWidth()) / 2, ((Gdx.graphics.getHeight() + 4 * plButton.getHeight()) / 2));
    }

    public void createSkin() {
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



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    public void renderPowerups(ArrayList<Powerup> powerups, SpriteBatch sb) {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        float i = 50;
        for (Powerup pu : powerups) {
            Sprite s = new Sprite(pu.getTexture());
            s.setPosition(i, 50); // Fix this to appear in own menu
            pu.setPosition(new Vector3(i, 50f, 0f));
            s.setSize(50, 50);
            pu.setHeight(50);
            pu.setWidth(50);
            s.draw(sb);
            i += 50;
        }
    }

    public void renderMarks(SpriteBatch sb) {
        for (TileState ts : singleton.getBoardState()) {
            Tile tile = ts.getTile();
            Mark m = new Mark(tile, ts.getState());
            if (ts.getState() == 1) {
                gameLogic.Move(tile.getX(), tile.getY(), 'O');
            } else {
                gameLogic.Move(tile.getX(), tile.getY(), 'X');
            }
            Sprite s = new Sprite(m.getTexture());
            s.setPosition(tile.getPosition().x, tile.getPosition().y);
            s.setSize(tile.getWidth(), tile.getHeight());
            s.draw(sb);
        }
    }
}
