/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:36 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.npc.secretbosses;
 */
package edu.neumont.csc150.models.npc.secretbosses;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.exceptions.EnemyIsRevivedException;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.SmallHeal;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.models.weapons.Spear;
import edu.neumont.csc150.models.weapons.Weapon;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;
import java.util.Random;

public class QuanChi implements SecretBoss{
    // secret boss floor 5
    public final int MAX_HEALTH = 85;
    private int badGuyHealth = 1;
    private int attack;
    private int speed;
    private int goldDrop;
    private int specialAttackUses;
    private boolean strengthUp;
    private ArrayList<Item> items;
    private ArrayList<Spell> spells;

    public QuanChi(){
        setBadGuyAttack(38);
        setBadGuyHealth(70);
        setBadGuyDroppedGold(23);
        setBadGuySpeed(14);
        setBadGuySpecialAttackUses(4);
        items = new ArrayList<>();
        items.add(new SmallHeal());
        setBadGuyItems(items);
        spells = new ArrayList<>();
        spells.add(new FireBall());
        spells.add(new Heal());
        spells.add(new IceSpike());
        spells.add(new LightningStrike());
        spells.add(new TornadoSpin());
        spells.add(new WaterStrike());
        setBadGuySpells(spells);
    }

    @Override
    public Spell badGuySpell(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex) {
        int spellIndex = new Random().nextInt(spells.size());
        Spell currentSpell = spells.get(spellIndex);
        switch (spellIndex){
            case 0, 2, 3, 4, 5:
                currentSpell.useOnPlayer(players.get(randomPlayerIndex));
                break;
            case 1:
                try{
                    currentSpell.useOnEnemy(enemies.get(randomEnemyIndex));
                } catch(EnemyIsRevivedException e){
                    GameUI.displayEnemyIsRevived(e.getMessage());
                    return currentSpell;
                }
                break;
        }
        return currentSpell;
    }

    @Override
    public Item badGuyItem(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex) {
        Item currentItem = items.get(0);
        try{
            currentItem.useOnEnemy(enemies.get(randomEnemyIndex));
        } catch (EnemyIsRevivedException e){
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
        int damage = 55;
        Player selectedPlayer = players.get(randomPlayerIndex);
        selectedPlayer.setHealth(selectedPlayer.getHealth() - damage);
        return damage;
    }

    @Override
    public Weapon dropWeapon() {
        return new Spear();
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
    public Item dropRareItem() {
        return null;
    }

    @Override
    public int dropHealthUpgrade() {
        return 45;
    }

    @Override
    public int dropMpUpgrade() {
        return 15;
    }
    @Override
    public String getName() {
        return "Quan Chi";
    }
}
