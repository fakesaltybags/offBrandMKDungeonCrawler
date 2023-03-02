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
    public int mpRecover(){
        return new Random().nextInt(14)+ 1;
    }

    @Override
    public void useOnPLayer(Player player) {

    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        throw new IllegalArgumentException("Not possible :(");
    }

    @Override
    public String getItemName() {
        return "Dr. Mario pill(MP Potion)";
    }

    @Override
    public int costAmount() {
        return 25;
    }
}
