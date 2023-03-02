/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:43 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public class Taraktans implements Lackie {
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    public Taraktans(){
        setBadGuyAttack(7);
        setBadGuyHealth(10);
        setMaxHealth(12);
        setBadGuySpeed(12);
        setBadGuyDroppedGold(3);
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
        return null;
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
