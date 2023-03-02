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
    private int speed;
    private int availableSpecialAttacks;
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private Weapon selectedWeapon;
    private ArrayList<Spell> spells;
    public final int MAX_SPECIAL_ATTACKS = 5;

    public Player() {
        items = new ArrayList<>();
        weapons = new ArrayList<>();
        weapons.add(new Hand());
        selectedWeapon = weapons.get(0);
        spells = new ArrayList<>();
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
        if(maxHP < 10){
            throw new IllegalArgumentException("Max HP cannot be lower than 10");
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
}
