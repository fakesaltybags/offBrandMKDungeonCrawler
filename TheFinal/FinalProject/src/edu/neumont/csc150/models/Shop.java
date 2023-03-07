/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:14 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models;

import edu.neumont.csc150.exceptions.NotEnoughMoneyException;
import edu.neumont.csc150.exceptions.PlayerHasSpellException;
import edu.neumont.csc150.models.items.*;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Spell> spells;
    private ArrayList<Item> items;

    public Shop(int floor) {
        spells = new ArrayList<>();
        spells.add(new FireBall());
        spells.add(new Heal());
        spells.add(new IceSpike());
        spells.add(new Implosion());
        spells.add(new LightningStrike());
        spells.add(new MPRecovery());
        spells.add(new SpeedUp());
        spells.add(new StrengthUp());
        spells.add(new TornadoSpin());
        spells.add(new WaterStrike());
        spells.add(new NoSpell());
        items = new ArrayList<>();
        items.add(new BigHeal());
        items.add(new Grenade());
        items.add(new MaxHealPotion());
        items.add(new MaxMpPotion());
        items.add(new MediumHeal());
        items.add(new MpPotion());
        items.add(new ShockStick());
        items.add(new SmallHeal());
        items.add(new ThrowingKnife());
        items.add(new Tomahawk());
    }

    public ArrayList<Spell> getSpellsInShop() {
        return spells;
    }

    public ArrayList<Item> getItemsInShop() {
        return items;
    }

    /**
     * Gives the player a spell if they don't already have it and they have enough money to buy it
     *
     * @param player  the player in the shop
     * @param spellNo the number of spell the player wants to get
     *                1. FireBall
     *                2. Heal
     *                3. IceSpike
     *                4. Implosion
     *                5. LightningStrike
     *                6. MPRecovery
     *                7. SpeedUp
     *                8. StrengthUp
     *                9. TornadoSpin
     *                10. WaterStrike
     *                11. NoSpell
     * @throws NotEnoughMoneyException if the player doesn't have enough money
     * @throws PlayerHasSpellException if the player already has the spell they are purchasing
     */
    public void buySpell(Player player, int spellNo) throws NotEnoughMoneyException, PlayerHasSpellException {
        Spell selectedSpell = getSpellsInShop().get(spellNo - 1);
        if (player.getGold() < selectedSpell.spellCost()) {
            throw new NotEnoughMoneyException("The player doesn't have enough money to buy this spell");
        }
        if (player.getSpells().contains(selectedSpell)) {
            throw new PlayerHasSpellException("The player already has the spell they selected");
        }
        player.giveSpell(selectedSpell);
        GameUI.displaySpellAdded(player, selectedSpell);
    }

    /**
     * Gives the player a item if they have enough money to buy it
     *
     * @param player the player in the shop
     * @param itemNo the number of item the player wants
     *               1. BigHeal
     *               2. Grenade
     *               3. MaxHealPotion
     *               4. MaxMpPotion
     *               5. MediumHeal
     *               6. MpPotion
     *               7. ShockStick
     *               8. SmallHeal
     *               9. ThrowingKnife
     *               10. Tomahawk
     * @throws NotEnoughMoneyException when the player doesn't have enough money to buy the item
     */
    public void buyItem(Player player, int itemNo) throws NotEnoughMoneyException {
        Item selectedItem = getItemsInShop().get(itemNo - 1);
        if (player.getGold() < selectedItem.costAmount()) {
            throw new NotEnoughMoneyException("The player doesn't have enough money to buy this spell");
        }
        player.giveItem(selectedItem);
        GameUI.displayItemAdded(selectedItem, player);
    }

    public String toString(boolean spells) {
        if (spells) {
            ArrayList<Spell> spellsInShop = getSpellsInShop();
            return "Spells:\n1. FireBall (" + spellsInShop.get(0).spellCost() + ")" +
                    "\n2. Heal (" + spellsInShop.get(1).spellCost() + ")" +
                    "\n3. IceSpike (" + spellsInShop.get(2).spellCost() + ")" +
                    "\n4. Implosion (" + spellsInShop.get(3).spellCost() + ")" +
                    "\n5. LightningStrike (" + spellsInShop.get(4).spellCost() + ")" +
                    "\n6. MPRecovery (" + spellsInShop.get(5).spellCost() + ")" +
                    "\n7. SpeedUp (" + spellsInShop.get(6).spellCost() + ")" +
                    "\n8. StrengthUp (" + spellsInShop.get(7).spellCost() + ")" +
                    "\n9. TornadoSpin (" + spellsInShop.get(8).spellCost() + ")" +
                    "\n10. WaterStrike (" + spellsInShop.get(9).spellCost() + ")" +
                    "\n11. NoSpell (" + spellsInShop.get(10).spellCost() + ")";
        } else {
            ArrayList<Item> itemsInShop = getItemsInShop();
            return "Items:\n1. BigHeal (" + itemsInShop.get(0).costAmount() + ")" +
                    "\n2. Grenade (" + itemsInShop.get(1).costAmount() + ")" +
                    "\n3. MaxHealPotion (" + itemsInShop.get(2).costAmount() + ")" +
                    "\n4. MaxMpPotion (" + itemsInShop.get(3).costAmount() + ")" +
                    "\n5. MediumHeal (" + itemsInShop.get(4).costAmount() + ")" +
                    "\n6. MpPotion (" + itemsInShop.get(5).costAmount() + ")" +
                    "\n7. ShockStick (" + itemsInShop.get(6).costAmount() + ")" +
                    "\n8. SmallHeal (" + itemsInShop.get(7).costAmount() + ")" +
                    "\n9. ThrowingKnife (" + itemsInShop.get(8).costAmount() + ")" +
                    "\n10. Tomahawk (" + itemsInShop.get(9).costAmount() + ")";
        }
    }
}
