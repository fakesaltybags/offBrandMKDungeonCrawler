/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:20 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */
package edu.neumont.csc150.models.spells;

public class FireBall implements Spell{
    @Override
    public int range() {
        return 80;
    }

    @Override
    public int damage() {
        return 40;
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
