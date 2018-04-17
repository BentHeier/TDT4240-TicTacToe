package com.mygdx.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Singleton.Singleton;
import com.mygdx.game.domain.InputHandler;
import com.mygdx.game.sprites.Tile;
import com.mygdx.game.domain.TileState;

import java.awt.TextComponent;
import java.util.ArrayList;

/**
 * Created by KasperKBerg on 17.04.2018.
 */

public class TemporaryTilePowerup extends InputHandler implements Powerup{

    private Singleton singleton = Singleton.getInstance();
    private Texture texture;


    public TemporaryTilePowerup(){this.texture = new Texture("temporaryTile.png");    }


    public void setTemporaryTile(Tile t){
        for (Tile tile : singleton.getTiles()){
            if (tile.getX() == t.getX() && tile.getY() == t.getY()){
                boolean canPlaceTempTile = false;
                for (TileState tileState : singleton.getBoardState()) {
                    if (tileState.getTile().getX() == t.getX() && tileState.getTile().getY() == t.getY()){
                        canPlaceTempTile = true;
                    }
                }
                if (canPlaceTempTile){
                    ArrayList<TileState> newTileState = singleton.getBoardState();
                    newTileState.add(new TileState(t, -2));
                    singleton.setBoardState(newTileState);
                } else{
                    System.out.println("Cannot place tempTile there. Not in use");
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        if (touchDown()){
            singleton.setpowerupSelected(this);
            System.out.println("TemporaryTile selected");
        }
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
