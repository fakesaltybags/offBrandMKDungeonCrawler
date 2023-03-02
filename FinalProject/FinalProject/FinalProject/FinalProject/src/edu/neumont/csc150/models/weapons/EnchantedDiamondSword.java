/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 8:36 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.weapons;
 */

package edu.neumont.csc150.models.weapons;

public class EnchantedDiamondSword implements Weapon{
    int rank = 9;

    @Override
    public int attack() {
        return 500;
    }

    @Override
    public int defend() {
        return 1200;
    }

    @Override
    public int specialAttack() {
        return 1500;
    }

    @Override
    public String getWeaponName() {
        return "Enchanted Diamond Sword";
    }
}
