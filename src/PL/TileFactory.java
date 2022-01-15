package PL;

import BL.MessageCallback;
import BL.Position;
import BL.Tiles.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileFactory {



    private List<Supplier<Player>> playersList;
    private Map<Character, Supplier<Enemy>> enemiesMap;
    private Player selected;

    public TileFactory(){
        playersList = initPlayers();
        enemiesMap = initEnemies();
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3,25, 3),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, 50,   4),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, 100,  5),
                  () -> new Monster('M', "The Mountain", 1000, 60, 25,  500, 6),
                   () -> new Monster('C', "Queen Cersei", 100, 10, 10,1000, 1),
                () -> new Traps('B', "Bonus Trap", 1, 1, 1, 250,  1, 10),
                () -> new Traps('Q', "Queen's Trap", 250, 50, 10, 100, 3, 10),

                () -> new Monster('z', "Wright", 600, 30, 15,100, 3),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, 250,  4),
                () -> new Monster('g', "Giant-Wright",1500, 100, 40,500,   5),
                () -> new Monster('w', "White Walker", 2000, 150, 50, 1000, 6),
                 () -> new Monster('K', "Night's King", 5000, 300, 150, 5000, 8),
                () -> new Traps('D', "Death Trap", 500, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getTile(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4, 3),
                () -> new Warrior("The Hound", 400, 20, 6, 5),
                () -> new Mage("Melisandre", 100, 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 40, 2, 20),
                () -> new Rogue("Bronn", 250, 35, 3, 50)
                //() -> new Hunter("Ygritte", 220, 30, 2, 6)
        );
    }

    public List<Player> listPlayers(){
        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }

    public Enemy produceEnemy(char tile, MessageCallback messageCallback,Position p) //
    {
        Supplier<Enemy> supplier=enemiesMap.get(tile);
        Enemy e=supplier.get();
        e.initialize(p,messageCallback);
        return e;
    }

    public Player producePlayer(int idx, Position p, MessageCallback messageCallback, Input input){
        if (selected==null)
        {
            selected= playersList.get(idx).get().initialize(p,messageCallback,input);//when we up level the player will stay the same
        }
        selected.setPosition(p);
        return selected;
    }

    public Empty produceEmpty(Position position){
        Empty empty=new Empty(position.getX(),position.getY());
        return empty;
    }

    public Wall produceWall(Position position){
        Wall wall=new Wall(position.getX(),position.getY());
        return wall;
    }

    public void PrintThePlayers()
    {
        MessageCallback msg=new CLI();
        for (int i=0;i<listPlayers().size();i++)
        {
            msg.send(i+1+". "+listPlayers().get(i).describe());
        }
    }
}
