/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:47 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

public class IceSpike implements Spell{
    @Override
    public int range() {
        return 50;
    }

    @Override
    public int damage() {
        return 50;
    }

    @Override
    public int magicPoint() {
        return 7;
    }

    @Override
    public String getSpellName() {
        return "Ice Spike";
    }
}
