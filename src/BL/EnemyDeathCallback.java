package BL;

import BL.Tiles.Enemy;

public interface EnemyDeathCallback {
    void call(Enemy e);//הבורד יקבל את ההודעה שצריף להסיר את הenemy
}
