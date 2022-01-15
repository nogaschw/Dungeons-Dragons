package BL;

public class Position {
    private int x;
    private int y;
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }
    public  int getY(){
        return  y;
    }

    public int range(Position pos){
        double deltax=x-pos.x;
        double deltay=y-pos.y;
        return (int) Math.sqrt((deltax*deltax)+(deltay*deltay));
    }
    public int compareTo(Position p){
       if (y != p.y)
           return y - p.y;
       return x - p.x;
    }

    public Position translate(int changeX, int changeY)
    {
        return new Position(getX()+changeX,getY()+changeY);
    }
}
