/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:22 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */

package edu.neumont.csc150.models.npc.secretbosses;

public class Onaga implements SecretBoss {
    //secret boss floor 10
    private int badGuyHealth;
    private int maxHealth;
    private int attack;
    private int speed;
    private int goldDrop;

    public Onaga(){
        setBadGuyAttack(120);
        setBadGuyHealth(340);
        setMaxHealth(500);
        setBadGuyDroppedGold(900);
        setBadGuySpeed(10);
        setBadGuyItemAmount(13);
        setBadGuySpecialAttackUses(17);
        setBadGuySpellUses(24);
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
