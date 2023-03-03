/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:20 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */
package edu.neumont.csc150.models.spells;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;

public class FireBall implements Spell{
    public final int DAMAGE = 40;

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
        return 4;
    }

    @Override
    public String getSpellName() {
        return "FireBall";
    }
}
