/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:14 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models;

import edu.neumont.csc150.models.items.*;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.models.weapons.*;

import java.util.ArrayList;

public class Chest {
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private ArrayList<Spell> spells;

    /**
     * Creates a new chest with items inside it
     * @param chestNo
     */
    public Chest(int chestNo){
        items = new ArrayList<>();
        weapons = new ArrayList<>();
        spells = new ArrayList<>();
        switch(chestNo){
            case 1:
                setupChestOneOne();
                break;
            case 2:
                setupChestTwoOne();
                break;
        }
    }

    private void setupChestTwoOne() {
        items.add(new ThrowingKnife());
        items.add(new ShockStick());
    }

    private void setupChestOneOne() {
        items.add(new SmallHeal());
        items.add(new SmallHeal());
        items.add(new Grenade());
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
