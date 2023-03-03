/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:07 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.NPC;
 */
package edu.neumont.csc150.models.npc.bosses;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.spells.Spell;

import java.util.ArrayList;

public interface Boss extends Lackie {


    int badGuySpell();

    int badGuyItem();

    int getSpecialAttackUses();

    void setBadGuySpells(ArrayList<Spell> spells);

    void setBadGuyItems(ArrayList<Item> items);

    void setBadGuySpecialAttackUses(int specialAttack);

    int specialAttack();

}
