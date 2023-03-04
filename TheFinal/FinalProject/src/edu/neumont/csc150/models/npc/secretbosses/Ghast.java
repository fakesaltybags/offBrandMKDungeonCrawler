/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:36 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.secretbosses;
 */
package edu.neumont.csc150.models.npc.secretbosses;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.MediumHeal;
import edu.neumont.csc150.models.items.SmallHeal;
import edu.neumont.csc150.models.spells.FireBall;
import edu.neumont.csc150.models.spells.SpeedUp;
import edu.neumont.csc150.models.spells.Spell;

import java.util.ArrayList;

public class Ghast implements SecretBoss{
    //secret boss floor 6
    public final int MAX_HEALTH = 45;
    private int badGuyHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    private int specialAttackUses;
    private boolean strengthUp;
    private ArrayList<Item> items;
    private ArrayList<Spell> spells;

    public Ghast(){
        setBadGuyAttack(50);
        setBadGuyHealth(30);
        setBadGuyDroppedGold(98);
        setBadGuySpeed(8);
        setBadGuySpecialAttackUses(5);
        items = new ArrayList<>();
        items.add(new MediumHeal());
        items.add(new SmallHeal());
        setBadGuyItems(items);
        spells = new ArrayList<>();
        spells.add(new FireBall());
        spells.add(new SpeedUp());
        setBadGuySpells(spells);
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
    public int getSpecialAttackUses() {
        return specialAttackUses;
    }

    @Override
    public void setBadGuySpells(ArrayList<Spell> spells) {
        if(spells.get(0) == null){
            throw new IllegalArgumentException("Spells cannot be null");
        }
        this.spells = spells;
    }

    @Override
    public void setBadGuyItems(ArrayList<Item> items) {
        if(items.get(0) == null){
            throw new IllegalArgumentException("Items cannot be null");
        }
        this.items = items;
    }

    @Override
    public void setBadGuySpecialAttackUses(int specialAttack) {
        if(specialAttack <= 0){
            specialAttackUses = 0;
            return;
        }
        specialAttackUses = specialAttack;
    }

    @Override
    public int specialAttack() {
        return 0;
        //TODO: figure out how much the special attack will do + call GameUI.DoSpecialAttack() or something like that
    }

    @Override
    public int dropGold() {
        return goldDrop;
    }

    @Override
    public int getBadGuyHealth() {
        return badGuyHealth;
    }

    @Override
    public int badGuyAttack() {
        if(isStrengthUp()){
            setStrengthUp(false);
            return attack * 2;
        }
        return attack;
    }

    @Override
    public int getBadGuySpeed() {
        return speed;
    }

    @Override
    public boolean isStrengthUp() {
        return strengthUp;
    }

    @Override
    public void setStrengthUp(boolean strengthUp) {
        this.strengthUp = strengthUp;
    }

    @Override
    public void setBadGuyHealth(int health) {
        if (health > MAX_HEALTH) {
            badGuyHealth = MAX_HEALTH;
            return;
        }
        if (health <= 0) {
            badGuyHealth = 0;
            return;
        }
        badGuyHealth = health;
    }

    @Override
    public void setBadGuyAttack(int attack) {
        if (attack >= 0) {
            throw new IllegalArgumentException("Attack cannot be lower than 1");
        }
        this.attack = attack;
    }

    @Override
    public void setBadGuySpeed(int speed) {
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed cannot be lower than 1");
        }
        this.speed = speed;
    }

    @Override
    public void setBadGuyDroppedGold(int gold) {
        if (gold <= 0) {
            throw new IllegalArgumentException("Gold amount cannot be lower than 1");
        }
        goldDrop = gold;
    }

    @Override
    public boolean isDead() {
        return getBadGuyHealth() <= 0;
    }

    @Override
    public Item dropRareItem() {
        return null;
        //TODO: FIX THIS
    }

    @Override
    public int dropHealthUpgrade() {
        return 0;
        //TODO: FIX THIS
    }

    @Override
    public int dropMpUpgrade() {
        return 0;
        //TODO: FIX THIS
    }
    @Override
    public String getName() {
        return "Ghast";
    }
}
