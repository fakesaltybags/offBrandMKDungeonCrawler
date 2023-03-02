/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:14 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models;

import edu.neumont.csc150.models.items.Item;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> itemsInShop;

    public ArrayList<Item> getItemsInShop() {
        return itemsInShop;
    }

    public void setItemsInShop(ArrayList<Item> itemsInShop, int difficulty) {
        //TODO: use the difficulty to set different items in the shop
        this.itemsInShop = itemsInShop;
    }
}
