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
    public int mediumHeal(){
        return new Random().nextInt(101)+ 1;
    }

    @Override
    public void useOnPLayer(Player player) {
        player.setHealth(player.getHealth() + mediumHeal());
    }

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.badGuyHealth() + mediumHeal());
    }

    @Override
    public String getItemName() {
        return "Pheniox Tear(Medium Heal)";
    }

    @Override
    public int costAmount() {
        return 50;
    }
}
