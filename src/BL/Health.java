package BL;

public class Health {
    protected int healthPool;
    protected int healthAmount;
    public Health(int healthPool,int healthAmount){
        this.healthPool=healthPool;
        this.healthAmount=healthAmount;
    }
    public int getHealthAmount(){
        return healthAmount;
    }
    public int getHealthPool(){
        return healthPool;
    }
    public void setHealthPool(int healthPool){
        this.healthPool=healthPool;
    }
    public void setHealthAmount(int healthAmount){
        this.healthAmount=healthAmount;
    }
    public void reduceAmount(int damageDone) {
        setHealthAmount(getHealthAmount()-damageDone);
    }

    @Override
    public String toString() {
        return healthAmount +"/" +healthPool;
    }
}
