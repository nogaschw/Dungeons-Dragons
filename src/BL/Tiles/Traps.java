package BL.Tiles;

import BL.GameBoard;

public class Traps extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer ticksCount;
    private boolean Visible;
    private final char tile;
    public Traps(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(tile, name, healthCapacity, attack, defense, experienceValue);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        this.ticksCount=0;
        Visible=true;
        this.tile=tile;
    }
    public void getAction(Player p, GameBoard board){
        if (((this.position).range(p.getPosition()))<2) {
            this.interact(p);
        }
        setVisible(visibilityTime<ticksCount);
        if (!Visible)
            Tile('.');
        else
            Tile(tile);
        if ((visibilityTime+invisibilityTime)==ticksCount)
            this.ticksCount=0;
        else
            this.ticksCount=ticksCount+1;
    }

    private void setVisible(boolean visible){
        this.Visible=visible;
    }

    @Override
    public void onDeath() {
        super.OnDeath();
    }
    public String describe() {
        return String.format("%s\t\tExperience Value: %s", super.describe(), getExperience());
    }
}