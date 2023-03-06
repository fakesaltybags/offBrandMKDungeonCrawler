/**
 * @author BradleyStewart
 * @createdOn 2/27/2023 at 9:07 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models.NPC;
 */
package edu.neumont.csc150.models.npc.bosses;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.Spell;

import java.util.ArrayList;

public interface Boss extends Lackie {

    /**
     * Makes a boss use a spell, then update the UI accordingly
     *
     * @param players           Players in the game
     * @param randomEnemyIndex  A random index used to choose a random enemy target
     * @param enemies           Enemies in the battle
     * @param randomPlayerIndex A random index used to choose a random player target
     * @return Spell that was chosen to pass to the UI
     */
    Spell badGuySpell(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex);

    /**
     * Makes a boss use an item, take it away from their inventory, and update the UI if an item was used
     *
     * @param players           the players in the game
     * @param randomEnemyIndex
     * @param enemies
     * @param randomPlayerIndex
     * @return true if an item was used false if not
     */
    Item badGuyItem(ArrayList<Player> players, int randomEnemyIndex, ArrayList<Lackie> enemies, int randomPlayerIndex);

    int getSpecialAttackUses();

    void setBadGuySpells(ArrayList<Spell> spells);

    ArrayList<Spell> getBadGuySpells();

    void setBadGuyItems(ArrayList<Item> items);

    ArrayList<Item> getBadGuyItems();

    void setBadGuySpecialAttackUses(int specialAttack);

    /**
     * Makes the boss do a special attack and update the UI with what happened.
     *
     * @param players           Current players in the game
     * @param randomPlayerIndex The current game mode
     * @return
     */
    int specialAttack(ArrayList<Player> players, int randomPlayerIndex);
    //TODO: make the special attack method for all the bosses

}
