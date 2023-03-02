/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:05 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

public class LightningStrike implements Spell{
    @Override
    public int range() {
        return 100;
    }

    @Override
    public int damage() {
        return 25;
    }

    @Override
    public int magicPoint() {
        return 3;
    }

    @Override
    public String getSpellName() {
        return "Lightning Strike";
    }
}