/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:49 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class SpeedUp implements Spell{
    public final int AMOUNT_UP = 5;

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuySpeed(enemy.badGuySpeed() + AMOUNT_UP);
    }

    @Override
    public void useOnPlayer(Player player) {
        player.setSpeed(player.getSpeed() + AMOUNT_UP);
    }

    @Override
    public int magicPoint() {
        return 3;
    }

    @Override
    public String getSpellName() {
        return "Speed Up";
    }
}
