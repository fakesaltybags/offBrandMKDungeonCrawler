/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:28 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class Mallot implements Weapon{
    @Override
    public int attack() {
        return 30;
    }

    @Override
    public int defend() {
        return 50;
    }

    @Override
    public int specialAttack() {
        return 70;
    }

    @Override
    public String getWeaponName() {
        return "Mallot";
    }
}
