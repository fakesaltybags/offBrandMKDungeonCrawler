/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:32 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class BroadSword implements Weapon{
    int rank = 7;

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
