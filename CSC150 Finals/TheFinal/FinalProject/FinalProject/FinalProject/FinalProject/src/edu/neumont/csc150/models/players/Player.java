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
import java.util.ArrayList;

public class Player {
    private int maxHP;
    private int health;
    private int magic;
    private int maxMagic;
    private int speed;
    private int availableSpecialAttacks;
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private Weapon selectedWeapon;
    private ArrayList<Spell> spells;
    public final int MAX_SPECIAL_ATTACKS = 5;
    public final int MIN_MAX_HP = 10;
    public final int MIN_MAX_MAGIC = 5;

    public Player() {
        items = new ArrayList<>();
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

    public int getAvailableSpecialAttacks() {
        return availableSpecialAttacks;
    }

    public void setAvailableSpecialAttacks(int availableSpecialAttacks) {
        if(availableSpecialAttacks < 0 || availableSpecialAttacks > 5){
            throw new IllegalArgumentException("Special attacks done can only be between 0 and 5");
        }
        this.availableSpecialAttacks = availableSpecialAttacks;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public void setMaxMagic(int maxMagic) {
        if(maxMagic < 5){
            throw new IllegalArgumentException("Max magic cannot be lower than " + MIN_MAX_MAGIC);
        }
        this.maxMagic = maxMagic;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        if(maxHP < MIN_MAX_HP){
            throw new IllegalArgumentException("Max HP cannot be lower than " + MIN_MAX_HP);
        }
        this.maxHP = maxHP;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < getMaxHP()){
            this.health = getMaxHP();
            return;
        }
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int attack(){
        return selectedWeapon.attack();
    }

    public void heal(int healAmount){
        if(healAmount < 1){
            throw new IllegalArgumentException("Heal amount cannot be lower than 1");
        }
        setHealth(getHealth() + healAmount);
    }

    public void mpRecover(int mpAmount){
        if(mpAmount < 1){
            throw new IllegalArgumentException("Mp recover amount cannot be lower than 1");
        }
        setMagic(getMagic() + mpAmount);
    }

    public String getInventory(){
        String returnString = "";
        for (Weapon weapon :
                weapons) {
            returnString += weapon.getWeaponName() + "\n";
        }
        for (Item item :
                items) {
            returnString += item.getItemName() + "\n";
        }
        for (Spell spell :
                spells) {
            returnString += spell.getSpellName() + "\n";
        }
        return returnString;
    }

    public int getAmountOfAttacks(){
        if(selectedWeapon instanceof Hand){
            return 20;
        }
        if (selectedWeapon instanceof Stick){
            return 7;
        }
        if (selectedWeapon instanceof Mallot){
            return 10;
        }
        if (selectedWeapon instanceof SlingShot){
            return 5;
        }
        if(selectedWeapon instanceof ChainBall){
            return 8;
        }
        if (selectedWeapon instanceof Spear){
            return 3;
        }
        if (selectedWeapon instanceof BroadSword){
            return 6;
        }
        if (selectedWeapon instanceof Bow){
            return 5;
        }
        if (selectedWeapon instanceof EnchantedDiamondSword){
            return 3;
        }
        if (selectedWeapon instanceof Glock){
            return 1;
        }

        //TODO: whenever your player class wants to load do this
        return 1;
    }
}
