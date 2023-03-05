/**
 * @author Pachi
 * @createdOn 3/2/2023 at 2:07 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class MaxMpPotion implements Item{


    @Override
    public void useOnPLayer(Player player) {
        player.setMagic(player.getMagic());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        throw new IllegalArgumentException("Not possible :(");
    }

    @Override
    public String getItemName() {
        return "Spell Book (Max MP Recover)";
    }

    @Override
    public int costAmount() {
        return 50;
    }
}
