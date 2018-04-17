package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Singleton.Singleton;


/**
 * Created by eiriksandberg on 09.04.2018.
 */

public class Mark {
    private Vector3 position;
    private Texture mark;
    private Singleton singleton = Singleton.getInstance();

    public Mark(Tile tile, int type){
        this.position = new Vector3(tile.getPosition().x, tile.getPosition().y, 1);
        if (type == 0) {
            this.mark = new Texture("xplayer.png");
        } else if (type == 1) {
            this.mark = new Texture("circleplayer.png");
        }
        else if (type == -1){
            this.mark = new Texture("white.png");
        }
        else if (type == -2){
            tile.setTemporaryTile();
            this.mark = tile.getTexture();
        }
        else {
            System.out.println("Something is wrong. Class: Mark");
        }

    }

    public void update(float dt){
    }


    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return mark;
    }

}
