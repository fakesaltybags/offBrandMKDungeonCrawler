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
        return 80;
    }

    @Override
    public int defend() {
        return 70;
    }

    @Override
    public int specialAttack() {
        return 150;
    }

    @Override
    public String getWeaponName() {
        return "Bow and Arrow";
    }
}
