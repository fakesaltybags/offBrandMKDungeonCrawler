/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:15 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc;
 */

package edu.neumont.csc150.models.npc.bosses;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.exceptions.EnemyIsRevivedException;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.SmallHeal;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;
import java.util.Random;

public class Ermac implements Boss {
    //floor 3 boss
    private int badGuyHealth = 1;
    public final int MAX_HEALTH = 28;
    private ArrayList<Spell> spells;
    private int specialAttackUses;
    private int attack;
    private int goldDrop;
    private int speed;
    private boolean strengthUp;
    private ArrayList<Item> items;

    public Ermac() {
        setBadGuyHealth(20);
        setBadGuySpecialAttackUses(3);
        setBadGuyAttack(9);
        setBadGuyDroppedGold(20);
        setBadGuySpeed(14);
        items = new ArrayList<>();
        items.add(new SmallHeal());
        setBadGuyItems(items);
        spells = new ArrayList<>();
        spells.add(new FireBall());
        spells.add(new Heal());
        spells.add(new TornadoSpin());
        spells.add(new StrengthUp());
        setBadGuySpells(spells);
    }

    @Override
    public Spell badGuySpell(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex) {
        int spellIndex = new Random().nextInt(spells.size());
        Spell currentSpell = spells.get(spellIndex);
        switch(spellIndex){
            case 0, 2, 3:
                //fireball, tornado spin, strength up
                currentSpell.useOnPlayer(players.get(randomPlayerIndex));
                break;
            case 1:
                //heal
                try {
                    currentSpell.useOnEnemy(enemies.get(randomEnemyIndex));
                }catch (EnemyIsRevivedException e){
                    GameUI.displayEnemyIsRevived(e.getMessage());
                    return currentSpell;
                }
                break;
        }
        return currentSpell;
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
        if (spells.get(0) == null) {
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
        if (items.get(0) == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }
        this.items = items;
    }

    @Override
    public void setBadGuySpecialAttackUses(int specialAttack) {
        if (specialAttack <= 0) {
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
        return "Ermac";
    }
}
