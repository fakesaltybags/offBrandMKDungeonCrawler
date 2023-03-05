/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:31 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class ChainBall implements Weapon{
    @Override
    public int attack() {
        return 45;
    }

    @Override
    public int defend() {
        return 30;
    }

    @Override
    public int specialAttack() {
        return 65;
    }

    @Override
    public String getWeaponName() {
        return "Chain and ball";
    }
}
