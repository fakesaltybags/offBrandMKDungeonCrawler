/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:36 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.secretbosses;
 */
package edu.neumont.csc150.models.npc.secretbosses;

public class QuanChi implements SecretBoss{
    // secret boss floor 5
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    public QuanChi(){
        setBadGuyAttack(38);
        setBadGuyHealth(70);
        setMaxHealth(85);
        setBadGuyDroppedGold(23);
        setBadGuySpeed(14);
        setBadGuyItemAmount(6);
        setBadGuySpecialAttackUses(4);
        setBadGuySpellUses(12);
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
        return "Quan Chi";
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
