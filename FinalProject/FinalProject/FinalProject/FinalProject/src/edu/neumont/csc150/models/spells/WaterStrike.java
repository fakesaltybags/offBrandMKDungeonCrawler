/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:05 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

public class WaterStrike implements Spell{
    @Override
    public int range() {
        return 30;
    }

    @Override
    public int damage() {
        return 70;
    }

    @Override
    public int magicPoint() {
        return 7;
    }

    @Override
    public String getSpellName() {
        return "Water Strike";
    }
}
