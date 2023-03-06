/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:14 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models;

import edu.neumont.csc150.models.spells.LightningStrike;
import edu.neumont.csc150.models.spells.Spell;

import java.util.ArrayList;

public class Shop {

    private ArrayList<Spell> spells;

    public Shop(int floor){
        spells = new ArrayList<>();
        if(floor == 1){
            spells.add(new LightningStrike());
            //something like this idk
            //TODO: finish this and implement it
        }
    }

    public ArrayList<Spell> getSpellsInShop() {
        return spells;
    }

    public void setItemsInShop(ArrayList<Spell> spells) {
        //TODO: use the difficulty to set different items in the shop
        this.spells = spells;
    }
}
