/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:48 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

//ask pachi later
public class Heal implements Spell {
    public final int HEAL_AMOUNT = 50;

    @Override
    public void useOnEnemy(Lackie enemy) {
        enemy.setBadGuyHealth(enemy.badGuyHealth() + HEAL_AMOUNT);
    }

    @Override
    public void useOnPlayer(Player player) {
        player.setHealth(player.getHealth() + HEAL_AMOUNT);
    }

    @Override
    public int magicPoint() {
        return 2;
    }

    @Override
    public String getSpellName() {
        return "Heal";
    }
}
