/**
 * @author Pachi
 * @createdOn 3/2/2023 at 2:07 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

import java.util.Random;

public class ShockStick implements Item{
    public int shock(){
        return new Random().nextInt(51)+ 1;
    }

    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getHealth() - shock());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.badGuyHealth() - shock());
    }

    @Override
    public String getItemName() {
        return "Denki Kaminari";
    }

    @Override
    public int costAmount() {
        return 30;
    }
}
