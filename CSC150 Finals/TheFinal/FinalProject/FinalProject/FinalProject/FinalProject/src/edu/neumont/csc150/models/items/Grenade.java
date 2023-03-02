/**
 * @author Pachi
 * @createdOn 3/2/2023 at 2:06 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

import java.util.Random;

public class Grenade implements Item{
    public int exsplosin(){
        return new Random().nextInt(101)+ 1;
    }

    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getHealth() - exsplosin());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.badGuyHealth() - exsplosin());
    }

    @Override
    public String getItemName() {
        return "Impact Gernade";
    }

    @Override
    public int costAmount() {
        return 25;
    }
}
