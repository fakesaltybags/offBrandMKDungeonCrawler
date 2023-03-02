/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:05 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

public class MPRecovery implements Spell{
    @Override
    public int range() {
        return 0;
    }

    @Override
    public int damage() {
        return 0;
    }

    @Override
    public int magicPoint() {
        return 1;
    }

    @Override
    public String getSpellName() {
        return "MP Recover";
    }
}
