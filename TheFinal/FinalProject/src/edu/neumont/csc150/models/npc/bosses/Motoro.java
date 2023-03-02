/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:19 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */

package edu.neumont.csc150.models.npc.bosses;

public class Motoro implements Boss {
    //floor 8 boss
    private int health;
    private int maxHealth;
    private int spellUsage;
    private int speacialAttackUsage;
    private int attack;
    private int goldDrop;
    private int speed;
    private int itemUsage;

    public Motoro(){
        setRank(rank);
        setBadGuyHealth(102);
        setBadGuyItemAmount(3);
        setBadGuySpellUses(3);
        setBadGuySpecialAttackUses(7);
        setBadGuyAttack(65);
        setBadGuyDroppedGold(320);
        setBadGuySpeed(15);
        setMaxHealth(112);
    }
    int rank = 8;

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
        return "Motoro";
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

    public void setRank(int rank){
        this.rank = rank;
    }
}
