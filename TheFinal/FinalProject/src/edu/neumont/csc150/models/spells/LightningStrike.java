/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:05 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class LightningStrike implements Spell{
    public final int DAMAGE = 25;


    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.getBadGuyHealth() - DAMAGE);
    }

    @Override
    public void useOnPlayer(Player player) {
        player.setHealth(player.getHealth() - DAMAGE);
    }

    @Override
    public int magicPoint() {
        return 3;
    }

    @Override
    public String getSpellName() {
        return "Lightning Strike";
    }

    @Override
    public int spellCost() {
        return 10;
    }
}
