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
        return 90;
    }

    @Override
    public int defend() {
        return 65;
    }

    @Override
    public int specialAttack() {
        return 120;
    }

    @Override
    public String getWeaponName() {
        return "Broad Sword";
    }
}
