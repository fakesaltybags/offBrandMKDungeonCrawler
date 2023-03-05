/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:33 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class Spear implements Weapon{
    @Override
    public int attack() {
        return 80;
    }

    @Override
    public int defend() {
        return 130;
    }

    @Override
    public int specialAttack() {
        return 160;
    }

    @Override
    public String getWeaponName() {
        return "Spear";
    }
}
