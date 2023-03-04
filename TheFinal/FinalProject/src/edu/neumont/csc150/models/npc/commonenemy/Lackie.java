/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:39 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.commonenemy;
 */
package edu.neumont.csc150.models.npc.commonenemy;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;

public interface Lackie {
    int dropGold();
    int getBadGuyHealth();
    int getMaxHealth();
    int badGuyAttack();
    int getBadGuySpeed();
    boolean isStrengthUp();
    void setStrengthUp(boolean strengthUp);
    void setBadGuyHealth(int health) throws EnemyIsDeadException;
    void setBadGuyAttack(int attack);
    void setBadGuySpeed(int speed);
    void setBadGuyDroppedGold(int gold);
    boolean isDead();
    String getName();
}
