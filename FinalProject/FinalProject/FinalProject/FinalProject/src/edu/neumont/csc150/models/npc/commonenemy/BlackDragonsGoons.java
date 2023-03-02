/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:45 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public class BlackDragonsGoons implements Lackies{
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;

    public BlackDragonsGoons(){
        setBadGuyAttack(8);
        setBadGuyHealth(25);
        setMaxHealth(27);
        setBadGuySpeed(9);
        setBadGuyDroppedGold(10);
    }

    @Override
    public int dropGold() {
        return 0;
    }

    @Override
    public int badGuyhealth() {
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
        return "Black Dragon Goons";
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
