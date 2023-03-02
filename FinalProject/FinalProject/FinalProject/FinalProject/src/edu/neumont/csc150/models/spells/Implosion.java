/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:06 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;

public class Implosion implements Spell{
    //Best spell in the game make a low chance to have

    @Override
    public int range() {
        return 100;
    }

    @Override
    public int damage() {
        return 1000000;
    }

    @Override
    public int magicPoint() {
        return 15;
    }

    @Override
    public String getSpellName() {
        return "Implosion";
    }
}
