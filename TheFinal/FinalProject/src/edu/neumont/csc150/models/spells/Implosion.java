/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:06 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class Implosion implements Spell{
    //Best spell in the game make a low chance to have
    public final int DAMAGE = 1000000;

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
        return 15;
    }

    @Override
    public String getSpellName() {
        return "Implosion";
    }

    @Override
    public int spellCost() {
        return 500;
    }
}
