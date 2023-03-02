/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:07 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.NPC;
 */
package edu.neumont.csc150.models.npc.bosses;

import edu.neumont.csc150.models.npc.commonenemy.Lackies;

public interface Bosses extends Lackies {



    public int badGuySpell();
    public int badGuyItem();
    public int speacialAttack();

    public void setBadGuySpellUses(int spell);
    public void setBadGuyItemAmount(int item);
    public void setBadGuySpecialAttackUses(int specialAttack);

}
