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
    public int smallHeal(){
        return new Random().nextInt(21) + 1;
    }

    @Override
    public void useOnPLayer(Player player) {
        int healAmount = smallHeal();
        player.setHealth(player.getHealth() + healAmount);
        GameUI.displayPlayerHeal(healAmount, player);
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.getBadGuyHealth() + smallHeal());
    }

    @Override
    public String getItemName() {
        return "Porkchop(Small Heal)";
    }

    @Override
    public int costAmount() {
        return 10;
    }
}
