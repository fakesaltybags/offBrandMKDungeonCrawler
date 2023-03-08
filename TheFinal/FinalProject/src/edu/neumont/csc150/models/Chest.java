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

    /**
     * Creates a new chest with items inside it
     * @param chestNo the number of chest
     */
    public Chest(int chestNo){
        items = new ArrayList<>();
        switch(chestNo){
            case 1:
                setupChest11();
                break;
            case 2:
                setupChest21();
                break;
            case 3:
                setupChest12();
                break;
            case 4:
                setupChest22();
                break;
        }
    }

    private void setupChest22() {
        items.add(new SmallHeal());
        items.add(new ShockStick());
    }

    private void setupChest12() {
        items.add(new MpPotion());
        items.add(new ThrowingKnife());
    }

    private void setupChest21() {
        items.add(new ThrowingKnife());
        items.add(new ShockStick());
    }

    private void setupChest11() {
        items.add(new SmallHeal());
        items.add(new SmallHeal());
        items.add(new Grenade());
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
