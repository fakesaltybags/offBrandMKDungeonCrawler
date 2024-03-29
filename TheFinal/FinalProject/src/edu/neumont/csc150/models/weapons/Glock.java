/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:36 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class Glock implements Weapon{
    @Override
    public int attack() {
        return 1000;
    }

    @Override
    public int defend() {
        return 5000;
    }

    @Override
    public int specialAttack() {
        return 5000;
    }

    @Override
    public String getWeaponName() {
        return "Glock";
    }
}
