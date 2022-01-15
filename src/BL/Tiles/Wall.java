package BL.Tiles;

public class Wall extends Tile {
    public static final char WallTile = '#';
    public Wall(int x,int y){
        super(x,y,WallTile);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
