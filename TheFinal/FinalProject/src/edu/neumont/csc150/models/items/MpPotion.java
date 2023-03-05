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

public class MpPotion implements Item{
    public final int RECOVER_AMOUNT = 15;

    @Override
    public void useOnPLayer(Player player) {
        player.setMagic(player.getMagic() + RECOVER_AMOUNT);
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        throw new IllegalArgumentException("Not possible :(");
    }

    @Override
    public String getItemName() {
        return "Dr. Mario Pill (MP Potion)";
    }

    @Override
    public int costAmount() {
        return 25;
    }
}
