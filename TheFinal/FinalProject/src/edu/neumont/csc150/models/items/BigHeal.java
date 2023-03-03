/**
 * @author Pachi
 * @createdOn 3/2/2023 at 2:05 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

import java.util.Random;

public class BigHeal implements Item{
    public int bigHeal(){
        return new Random().nextInt(501)+ 1;
    }

    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getHealth() + bigHeal());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.getBadGuyHealth()+ bigHeal());
    }

    @Override
    public String getItemName() {
        return "Taco Bell Box(Big Heal)";
    }

    @Override
    public int costAmount() {
        return 100;
    }
}
