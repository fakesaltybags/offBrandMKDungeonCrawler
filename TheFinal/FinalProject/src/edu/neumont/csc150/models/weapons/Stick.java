/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:25 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class Stick implements Weapon{
    @Override
    public int attack() {
        return 20;
    }

    @Override
    public int defend() {
        return 30;
    }

    @Override
    public int specialAttack() {
        return 50;
    }

    @Override
    public String getWeaponName() {
        return "Stick";
    }
}
