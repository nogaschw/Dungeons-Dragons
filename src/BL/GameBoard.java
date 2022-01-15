package BL;

import BL.Tiles.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> board;

    public GameBoard(Tile[][] board1){
        board = Arrays.stream(board1).flatMap(Arrays::stream).collect(Collectors.toList());
    }
    public Tile getTile(Position p){
      return board.stream().filter((t)->t.getPosition().compareTo(p)==0).findFirst().get();//מחזיר את הטייל במקום p
    }

    @Override
    public String toString(){

        String str = board.stream().sorted(new TileComparator())
                .map(t -> t.getPosition().getX() == 0 ? "\n" + t.toString() : t.toString())
                .collect(Collectors.joining(""));
        return str;
        }

}
