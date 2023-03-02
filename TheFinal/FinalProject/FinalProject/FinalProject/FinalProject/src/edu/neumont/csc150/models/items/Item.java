/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:03 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.items;
 */
package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public interface Item {
    void useOnPLayer(Player player);
    void useOnEnemy(Lackie enemy);
    String getItemName();
    int costAmount();
}
