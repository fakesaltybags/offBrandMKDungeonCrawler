/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:43 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public class Tarktans implements Lackies{
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    public Tarktans(){
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
        return "Tarkatans";
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
