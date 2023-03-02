/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:34 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class SlingShot implements Weapon{
    int rank = 4;

    @Override
    public int attack() {
        return 45;
    }

    @Override
    public int defend() {
        return 68;
    }

    @Override
    public int specialAttack() {
        return 80;
    }

    @Override
    public String getWeaponName() {
        return "Sling Shot";
    }
}
