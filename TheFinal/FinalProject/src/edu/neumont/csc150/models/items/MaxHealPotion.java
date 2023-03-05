/**
 * @author Pachi
 * @createdOn 3/2/2023 at 2:06 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class MaxHealPotion implements Item{
    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getMaxHP());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.getBadGuyHealth());
    }

    @Override
    public String getItemName() {
        return "Chug Jug (Max Heal)";
    }

    @Override
    public int costAmount() {
        return 150;
    }
}
