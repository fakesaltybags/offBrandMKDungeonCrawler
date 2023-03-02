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

public class ThrowingKnife implements Item{
    public int knifeThrown(){
        return new Random().nextInt(26)+ 1;
    }

    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getHealth() - knifeThrown());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.badGuyHealth() - knifeThrown());
    }

    @Override
    public String getItemName() {
        return "Naruto's Kunai";
    }

    @Override
    public int costAmount() {
        return 5;
    }
}
