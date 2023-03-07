/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:05 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class MPRecovery implements Spell{
    public final int RECOVER_AMOUNT = 10;

    @Override
    public void useOnEnemy(Lackie enemy) {
        throw new IllegalArgumentException("This item cannot be used on enemies :(");
    }

    @Override
    public void useOnPlayer(Player player) {
        player.setMagic(player.getMagic() + RECOVER_AMOUNT);
    }

    @Override
    public int magicPoint() {
        return 1;
    }

    @Override
    public String getSpellName() {
        return "MP Recover";
    }

    @Override
    public int spellCost() {
        return 5;
    }
}
