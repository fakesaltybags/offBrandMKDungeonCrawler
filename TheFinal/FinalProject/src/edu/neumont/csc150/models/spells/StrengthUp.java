/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:49 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class StrengthUp implements Spell {

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setStrengthUp(true);
    }

    @Override
    public void useOnPlayer(Player player) {
        player.setStrengthUp(true);
    }

    @Override
    public int magicPoint() {
        return 4;
    }

    @Override
    public String getSpellName() {
        return "Strength Up";
    }
}
