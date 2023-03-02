/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:46 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public class Zombie implements Lackie {
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    public Zombie(){
        setBadGuyAttack(6);
        setBadGuyHealth(8);
        setMaxHealth(10);
        setBadGuySpeed(3);
        setBadGuyDroppedGold(4);
    }
    @Override
    public int dropGold() {
        return 0;
    }

    @Override
    public int badGuyHealth() {
        return 0;
    }

    @Override
    public int badGuyMaxHealth() {
        return 0;
    }

    @Override
    public int badGuyAttack() {
        return 0;
    }

    @Override
    public int badGuySpeed() {
        return 0;
    }

    @Override
    public String getName() {
        return "Zombie";
    }

    @Override
    public void setBadGuyHealth(int health) {

    }

    @Override
    public void setMaxHealth(int maxHealth) {

    }

    @Override
    public void setBadGuyAttack(int attack) {

    }

    @Override
    public void setBadGuySpeed(int speed) {

    }

    @Override
    public void setBadGuyDroppedGold(int gold) {

    }

    @Override
    public boolean isDead() {
        return false;
    }
}
