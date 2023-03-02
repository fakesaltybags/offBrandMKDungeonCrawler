/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:17 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */
package edu.neumont.csc150.models.weapons;

public class Hand implements Weapon{
    int rank = 1;
    @Override
    public int attack() {
        return 5;
    }

    @Override
    public int defend() {
        return 20;
    }

    @Override
    public int specialAttack() {
        return 30;
    }

    @Override
    public String getWeaponName() {
        return "Fists";
    }
}
