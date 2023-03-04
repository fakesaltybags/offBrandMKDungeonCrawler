/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:21 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */

package edu.neumont.csc150.models.npc.secretbosses;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.SmallHeal;
import edu.neumont.csc150.models.items.ThrowingKnife;
import edu.neumont.csc150.models.items.Tomahawk;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.Heal;
import edu.neumont.csc150.models.spells.SpeedUp;
import edu.neumont.csc150.models.spells.Spell;
import edu.neumont.csc150.models.spells.StrengthUp;

import java.util.ArrayList;

public class Illager implements SecretBoss {

    //secret boss floor 4
    public final int MAX_HEALTH = 38;
    private int badGuyHealth;
    private int attack;
    private int speed;
    private int goldDrop;
    private int specialAttackUses;
    private boolean strengthUp;
    private ArrayList<Item> items;
    private ArrayList<Spell> spells;


    public Illager(){
        setBadGuyAttack(20);
        setBadGuyHealth(30);
        setBadGuyDroppedGold(29);
        setBadGuySpeed(12);
        setBadGuySpecialAttackUses(3);
        items = new ArrayList<>();
        items.add(new SmallHeal());
        items.add(new ThrowingKnife());
        items.add(new Tomahawk());
        setBadGuyItems(items);
        spells = new ArrayList<>();
        spells.add(new Heal());
        spells.add(new SpeedUp());
        spells.add(new StrengthUp());
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
        return "Illager";
    }
}
