package BL.Tiles;

import BL.MessageCallback;
import BL.Position;

public abstract class Tile {
    protected char tile;
    protected Position position;

    public Tile(int x, int y, char c){
        this.position=new Position(x,y);
        this.tile=c;
    }
    protected Tile(char tile){
        this.tile = tile;
    }
    protected void Tile(char tile){
        this.tile = tile;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void accept(Unit unit);

   // @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }


    @Override
    public String toString() {
        String s=""+tile;
        return s;
    }

    protected void swapPosition(Tile tile){
        Position pos=tile.getPosition();
        tile.initialize(this.getPosition());
        this.initialize(pos);
    }
}
