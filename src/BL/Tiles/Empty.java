package BL.Tiles;

public class Empty extends Tile {
    public static final char EmptyTile = '.';
    public Empty(int x,int y){
        super(x,y,EmptyTile);
    }
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
