/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:44 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public class Spider implements Lackies{
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    public Spider(){
        setBadGuyAttack(3);
        setBadGuyHealth(7);
        setMaxHealth(10);
        setBadGuySpeed(7);
        setBadGuyDroppedGold(2);
    }
    @Override
    public int dropGold() {
        return goldDrop;
    }

    @Override
    public int badGuyhealth() {
        return badGuyHealth;
    }

    @Override
    public int badGuyMaxHealth() {
        return maxHealth;
    }

    @Override
    public int badGuyAttack() {
        return attack;
    }

    @Override
    public int badGuySpeed() {
        return speed;
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
}
