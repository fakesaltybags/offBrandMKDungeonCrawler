/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:13 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models.players;

import edu.neumont.csc150.models.items.*;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.models.weapons.*;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;

public class Player {
    private int maxHP;
    private int health = 1;
    private int magic;
    private int maxMagic;
    private int speed;
    private boolean strengthUp = false;
    private int availableSpecialAttacks;
    private int gold;
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private Weapon selectedWeapon;
    private ArrayList<Spell> spells;
    public final int MAX_SPECIAL_ATTACKS = 5;
    public final int MIN_MAX_HP = 25;
    public final int MIN_MAX_MAGIC = 5;

    public Player() {
        items = new ArrayList<>();
        items.add(new SmallHeal());
        weapons = new ArrayList<>();
        weapons.add(new Hand());
        selectedWeapon = weapons.get(0);
        spells = new ArrayList<>();
        setMaxHP(MIN_MAX_HP);
        setHealth(getMaxHP());
        setMaxMagic(MIN_MAX_MAGIC);
        setMagic(getMaxMagic());
        setAvailableSpecialAttacks(MAX_SPECIAL_ATTACKS);
    }

    //region get/set
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        if(gold < 0){
            throw new IllegalArgumentException("amount of gold cannot be lower than zero");
        }
        this.gold = gold;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("Items has to be initialized");
        }
        this.items = items;
    }

    public boolean isStrengthUp() {
        return strengthUp;
    }

    public void setStrengthUp(boolean strengthUp) {
        this.strengthUp = strengthUp;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        if (weapons == null) {
            throw new IllegalArgumentException("Weapons has to be initialized");
        }
        this.weapons = weapons;
    }

    public Weapon getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(Weapon selectedWeapon) {
        if (selectedWeapon == null) {
            throw new IllegalArgumentException("Selected weapon has to be initialized");
        }
        this.selectedWeapon = selectedWeapon;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        if (spells == null) {
            throw new IllegalArgumentException("Spells has to be initialized");
        }
        this.spells = spells;
    }

    public int getAvailableSpecialAttacks() {
        return availableSpecialAttacks;
    }

    public void setAvailableSpecialAttacks(int availableSpecialAttacks) {
        if (availableSpecialAttacks < 0 || availableSpecialAttacks > 5) {
            throw new IllegalArgumentException("Special attacks done can only be between 0 and 5");
        }
        this.availableSpecialAttacks = availableSpecialAttacks;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public void setMaxMagic(int maxMagic) {
        if (maxMagic < 5) {
            throw new IllegalArgumentException("Max magic cannot be lower than " + MIN_MAX_MAGIC);
        }
        this.maxMagic = maxMagic;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        if(magic > getMaxMagic()){
            this.magic = getMaxMagic();
            return;
        }
        this.magic = magic;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        if (maxHP < MIN_MAX_HP) {
            throw new IllegalArgumentException("Max HP cannot be lower than " + MIN_MAX_HP);
        }
        this.maxHP = maxHP;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health > getMaxHP()) {
            this.health = getMaxHP();
            return;
        }
        if(health <= 0){
            this.health = 0;
            return;
        }
        if(getHealth() == 0){
            GameUI.displayPlayerRevived();
        }
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    //endregion

    public boolean isDead(){
        return getHealth() <= 0;
    }

    public int attack() {
        if(isStrengthUp()){
            int attackAmount = selectedWeapon.attack() * 2;
            setStrengthUp(false);
            return attackAmount;
        }
        return selectedWeapon.attack();
    }

    public void heal(int healAmount) {
        if (healAmount < 1) {
            throw new IllegalArgumentException("Heal amount cannot be lower than 1");
        }
        setHealth(getHealth() + healAmount);
    }

    public void mpRecover(int mpAmount) {
        if (mpAmount < 1) {
            throw new IllegalArgumentException("Mp recover amount cannot be lower than 1");
        }
        setMagic(getMagic() + mpAmount);
    }

    public String getInventory() {
        String returnString = "";
        returnString += "*WEAPONS*\n";
        for (Weapon weapon :
                weapons) {
            returnString += weapon.getWeaponName() + "\n";
        }
        returnString += "\n*ITEMS*\n";
        for (Item item :
                items) {
            returnString += item.getItemName() + "\n";
        }
        returnString += "\n*SPELLS*\n";
        for (Spell spell :
                spells) {
            returnString += spell.getSpellName() + "\n";
        }
        return returnString;
    }

    public int getAmountOfAttacks() {
        if (selectedWeapon instanceof Hand) {
            return 20;
        }
        if (selectedWeapon instanceof Stick) {
            return 7;
        }
        if (selectedWeapon instanceof Mallot) {
            return 10;
        }
        if (selectedWeapon instanceof SlingShot) {
            return 5;
        }
        if (selectedWeapon instanceof ChainBall) {
            return 8;
        }
        if (selectedWeapon instanceof Spear) {
            return 3;
        }
        if (selectedWeapon instanceof BroadSword) {
            return 6;
        }
        if (selectedWeapon instanceof Bow) {
            return 5;
        }
        if (selectedWeapon instanceof EnchantedDiamondSword) {
            return 3;
        }
        if (selectedWeapon instanceof Glock) {
            return 1;
        }
        return 1;
    }
}
