/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:07 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.NPC;
 */
package edu.neumont.csc150.models.npc.bosses;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;

public interface Boss extends Lackie {


    int badGuySpell();

    int badGuyItem();

    int speacialAttack();

    void setBadGuySpellUses(int spell);

    void setBadGuyItemAmount(int item);

    void setBadGuySpecialAttackUses(int specialAttack);

}
