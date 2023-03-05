/**
 * @author Pachi
 * @createdOn 3/5/2023 at 2:34 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.models.spells;
 */
package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class NoSpell implements Spell{
    @Override
    public void useOnEnemy(Lackie enemy) {
    }

    @Override
    public void useOnPlayer(Player player) {

    }

    @Override
    public int magicPoint() {
        return 0;
    }

    @Override
    public String getSpellName() {
        return "Splash";
    }
}
