/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:34 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */
package edu.neumont.csc150.models.npc.secretbosses;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.Spell;

import java.util.ArrayList;

public class HsuHao implements SecretBoss{
    //secret boss floor 1
    public final int MAX_HEALTH = 1;
    private int badGuyHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    private int specialAttackUses;
    private boolean strengthUp;
    private ArrayList<Item> items;
    private ArrayList<Spell> spells;

    public HsuHao(){
        setBadGuyAttack(1);
        setBadGuyHealth(1);
        setBadGuyDroppedGold(10);
        setBadGuySpeed(9);
        setBadGuySpecialAttackUses(1);
        items = new ArrayList<>();
        setBadGuyItems(items);
        spells = new ArrayList<>();
        setBadGuySpells(spells);
    }

    @Override
    public void badGuySpell(ArrayList<Player> players, boolean isMultiplayer) {
        //TODO: make this method use a random spell and update the UI with what happened
    }

    @Override
    public boolean badGuyItem(ArrayList<Player> players, boolean isMultiplayer) {
        return false;
        //TODO: FIX THIS
    }

    @Override
    public int getSpecialAttackUses() {
        return specialAttackUses;
    }

    @Override
    public void setBadGuySpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    @Override
    public void setBadGuyItems(ArrayList<Item> items) {
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
    public void specialAttack(ArrayList<Player> players, boolean isMultiplayer) {
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
    public int getMaxHealth() {
        return MAX_HEALTH;
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
    public void setBadGuyHealth(int health) throws EnemyIsDeadException {
        if (health > MAX_HEALTH) {
            badGuyHealth = MAX_HEALTH;
            return;
        }
        if (health <= 0) {
            if(getBadGuyHealth() == 0){
                throw new EnemyIsDeadException("The enemy you tried to hit is already dead");
            }
            badGuyHealth = 0;
            return;
        }
        badGuyHealth = health;
    }

    @Override
    public void setBadGuyAttack(int attack) {
        if (attack <= 0) {
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
        return "Hsu Hao";
    }
}
