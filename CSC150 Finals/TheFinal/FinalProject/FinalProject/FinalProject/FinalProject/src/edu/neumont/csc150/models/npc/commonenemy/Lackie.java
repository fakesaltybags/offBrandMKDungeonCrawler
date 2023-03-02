/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:39 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

public interface Lackie {
    int dropGold();
    int badGuyHealth();
    int badGuyMaxHealth();
    int badGuyAttack();
    int badGuySpeed();
    String getName();


    void setBadGuyHealth(int health);
    void setMaxHealth(int maxHealth);
    void setBadGuyAttack(int attack);
    void setBadGuySpeed(int speed);
    void setBadGuyDroppedGold(int gold);
    boolean isDead();
}
