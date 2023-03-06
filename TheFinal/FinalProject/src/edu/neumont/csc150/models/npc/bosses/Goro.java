/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:20 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */

package edu.neumont.csc150.models.npc.bosses;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.exceptions.EnemyIsRevivedException;
import edu.neumont.csc150.models.items.BigHeal;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.FireBall;
import edu.neumont.csc150.models.spells.SpeedUp;
import edu.neumont.csc150.models.spells.Spell;
import edu.neumont.csc150.models.spells.StrengthUp;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;
import java.util.Random;

public class Goro implements Boss {
    //floor 9 boss
    public final int MAX_HEALTH = 190;
    private int badGuyHealth = 1;
    private ArrayList<Spell> spells;
    private int specialAttackUses;
    private int attack;
    private int goldDrop;
    private int speed;
    private boolean strengthUp;
    private ArrayList<Item> items;

    public Goro(){
        setBadGuyHealth(170);
        setBadGuySpecialAttackUses(6);
        setBadGuyAttack(80);
        setBadGuyDroppedGold(900);
        setBadGuySpeed(14);
        items = new ArrayList<>();
        items.add(new BigHeal());
        setBadGuyItems(items);
        spells = new ArrayList<>();
        spells.add(new FireBall());
        spells.add(new SpeedUp());
        spells.add(new StrengthUp());
        setBadGuySpells(spells);
    }

    @Override
    public Spell badGuySpell(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex) {
        int spellIndex = new Random().nextInt(spells.size());
        Spell currentSpell = spells.get(spellIndex);
        switch(spellIndex){
            case 0:
            currentSpell.useOnPlayer(players.get(randomPlayerIndex));
            break;
            case 1, 2:
            currentSpell.useOnEnemy(enemies.get(randomEnemyIndex));
            break;
        }
        return currentSpell;
    }

    @Override
    public Item badGuyItem(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex) {
        Item currentItem = items.get(0);
        try {
            currentItem.useOnEnemy(enemies.get(randomEnemyIndex));
        }catch (EnemyIsRevivedException e){
            GameUI.displayEnemyIsRevived(e.getMessage());
            return currentItem;
        }
        return currentItem;
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
    public ArrayList<Spell> getBadGuySpells() {
        return spells;
    }

    @Override
    public void setBadGuyItems(ArrayList<Item> items) {
        if(items.get(0) == null){
            throw new IllegalArgumentException("Items cannot be null");
        }
        this.items = items;
    }

    @Override
    public ArrayList<Item> getBadGuyItems() {
        return items;
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
    public int specialAttack(ArrayList<Player> players, int randomPlayerIndex) {
        //TODO: figure out how much the special attack will do + call GameUI.DoSpecialAttack() or something like that
        return randomPlayerIndex;
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
        if(getBadGuyHealth() == 0){
            badGuyHealth = health;
            throw new EnemyIsRevivedException("---- A ENEMY HAS BEEN REVIVED ----");
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
    public String getName() {
        return "Goro";
    }
}
