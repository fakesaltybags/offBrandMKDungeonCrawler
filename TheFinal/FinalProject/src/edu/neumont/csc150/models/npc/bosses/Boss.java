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
     * @param players       Players in the game
     * @param isMultiplayer The current game mode
     */
    void badGuySpell(ArrayList<Player> players, boolean isMultiplayer);

    /**
     * Makes a boss use an item, take it away from their inventory, and update the UI if an item was used
     *
     * @param players       the players in the game
     * @param isMultiplayer The current game mode
     * @return true if an item was used false if not
     */
    boolean badGuyItem(ArrayList<Player> players, boolean isMultiplayer);

    int getSpecialAttackUses();

    void setBadGuySpells(ArrayList<Spell> spells);

    void setBadGuyItems(ArrayList<Item> items);

    void setBadGuySpecialAttackUses(int specialAttack);

    /**
     * Makes the boss do a special attack and update the UI with what happened.
     *
     * @param players       Current players in the game
     * @param isMultiplayer The current game mode
     */
    void specialAttack(ArrayList<Player> players, boolean isMultiplayer);
    //TODO: make the special attack method for all the bosses

}
