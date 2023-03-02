/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:19 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.spells;
 */
package edu.neumont.csc150.models.spells;

public interface Spell {
    int range();
    int damage();
    int magicPoint();
    String getSpellName();
}
