package br.unb.shooter.entity;

public class Map extends Entity {

    private Float startX;

    private Float startY;

    private Float cols;

    private Float rows;

    private Tile tile;

    public Map() {
        startX = 0f;
        startY = 0f;
        positionX = 0f;
        positionY = 0f;
        cols = 50f;
        rows = 50f;
        tile = new Tile();
        width = tile.width * cols;
        height = tile.height * rows;
    }

    public Float getStartX() {
        return startX;
    }

    public void setStartX(Float startX) {
        this.startX = startX;
    }

    public Float getStartY() {
        return startY;
    }

    public void setStartY(Float startY) {
        this.startY = startY;
    }

    public Float getCols() {
        return cols;
    }

    public void setCols(Float cols) {
        this.cols = cols;
    }

    public Float getRows() {
        return rows;
    }

    public void setRows(Float rows) {
        this.rows = rows;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

}
