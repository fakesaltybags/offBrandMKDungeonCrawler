/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:19 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */
package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public interface Spell {
    void useOnEnemy(Lackie enemy);
    void useOnPlayer(Player player);
    int magicPoint();
    String getSpellName();
}
