/**
 * @author Pachi
 * @createdOn 3/2/2023 at 2:00 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

import java.util.Random;

public class MediumHeal implements Item{
    public final int HEAL_AMOUNT = 100;

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
        return "Phoenix Tear (Medium Heal)";
    }

    @Override
    public int costAmount() {
        return 50;
    }
}
