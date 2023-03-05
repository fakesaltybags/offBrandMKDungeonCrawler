/**
 * @author Pachi
 * @createdOn 3/2/2023 at 1:58 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.views.GameUI;

import java.util.Random;

public class SmallHeal implements Item{
    public final int HEAL_AMOUNT = 20;

    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getHealth() + HEAL_AMOUNT);
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.getBadGuyHealth() + HEAL_AMOUNT);
    }

    @Override
    public String getItemName() {
        return "Pork-chop (Small Heal)";
    }

    @Override
    public int costAmount() {
        return 10;
    }
}
