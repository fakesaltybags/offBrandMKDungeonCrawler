/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:47 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class TornadoSpin implements Spell{
    public final int DAMAGE = 20;

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
        return 5;
    }

    @Override
    public String getSpellName() {
        return "Tornado Spin";
    }

    @Override
    public int spellCost() {
        return 17;
    }
}
