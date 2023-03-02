/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:34 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */
package edu.neumont.csc150.models.npc.secretbosses;

public class ZombiePigman implements SecretBoss{
    //secret boss floor 2
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    public ZombiePigman(){
        setBadGuyAttack(12);
        setBadGuyHealth(20);
        setMaxHealth(25);
        setBadGuyDroppedGold(6);
        setBadGuySpeed(12);
        setBadGuyItemAmount(4);
        setBadGuySpecialAttackUses(2);
        setBadGuySpellUses(4);
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
        return "Zombie Pigman";
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
    public int badGuySpell() {
        return 0;
    }

    @Override
    public int badGuyItem() {
        return 0;
    }

    @Override
    public int speacialAttack() {
        return 0;
    }

    @Override
    public void setBadGuySpellUses(int spell) {

    }

    @Override
    public void setBadGuyItemAmount(int item) {

    }

    @Override
    public void setBadGuySpecialAttackUses(int specialAttack) {

    }

    @Override
    public int dropRareItem() {
        return 0;
    }

    @Override
    public int dropHealthUpgrade() {
        return 0;
    }

    @Override
    public int dropMpUpgrade() {
        return 0;
    }
}
