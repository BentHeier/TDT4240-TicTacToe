package com.mygdx.game.domain;

/**
 * Created by eiriksandberg on 09.04.2018.
 */

public class Board {
    private int rows;
    private int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
