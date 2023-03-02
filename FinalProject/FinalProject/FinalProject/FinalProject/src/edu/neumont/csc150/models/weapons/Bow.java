/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:29 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class Bow implements Weapon{
    int rank = 8;

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }

    @Override
    public int specialAttack() {
        return 0;
    }
}
