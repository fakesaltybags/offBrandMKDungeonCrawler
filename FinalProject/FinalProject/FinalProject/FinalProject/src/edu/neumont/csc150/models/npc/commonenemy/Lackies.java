/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:39 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public interface Lackies {
    public int dropGold();
    public int badGuyhealth();
    public int badGuyMaxHealth();
    public int badGuyAttack();
    public int badGuySpeed();
    String getName();


    public void setBadGuyHealth(int health);
    public void setMaxHealth(int maxHealth);
    public void setBadGuyAttack(int attack);
    public void setBadGuySpeed(int speed);
    public void setBadGuyDroppedGold(int gold);
}
