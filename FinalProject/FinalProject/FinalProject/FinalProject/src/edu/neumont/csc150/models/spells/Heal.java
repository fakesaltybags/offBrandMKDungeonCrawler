/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:48 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */

package edu.neumont.csc150.models.spells;
//ask pachi later
public class Heal implements Spell{
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
        return 2;
    }

    @Override
    public String getSpellName() {
        return "Heal";
    }
}
