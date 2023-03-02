/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:20 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */

package edu.neumont.csc150.models.npc.bosses;

public class Goro implements Bosses {
    //floor 9 boss
    private int health;
    private int maxHealth;
    private int spellUsage;
    private int speacialAttackUsage;
    private int attack;
    private int goldDrop;
    private int speed;
    private int itemUsage;

    public Goro(){
        setRank(rank);
        setBadGuyHealth(170);
        setBadGuyItemAmount(8);
        setBadGuySpellUses(8);
        setBadGuySpecialAttackUses(6);
        setBadGuyAttack(80);
        setBadGuyDroppedGold(900);
        setBadGuySpeed(14);
        setMaxHealth(190);
    }
    int rank = 9;

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
        return "Goro";
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

    public void setRank(int rank){
        this.rank = rank;
    }
}
