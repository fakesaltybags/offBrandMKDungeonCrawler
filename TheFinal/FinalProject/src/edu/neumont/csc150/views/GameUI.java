/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:08 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.views;
 */
package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Map;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.npc.secretbosses.SecretBoss;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.weapons.Weapon;

import java.util.ArrayList;

public class GameUI {

    public static int displayMainMenu() {
        return Console.getIntInput("""
                ---- Off Brand Mortal Kombat Dungeon Crawler ----
                1. Single Player
                2. Multiplayer (2P)
                3. Exit
                """);
    }

    public static int getMovementOptions(Map currentMap) {
        boolean[] partyMoveability = currentMap.getMoveability();
        System.out.println("---- Where do you want to move ----");
        int response;
        do {
            if (partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                //able to move in all directions
                response = Console.getIntInput("""
                        1. Turn left
                        2. Turn right
                        3. Keep straight
                        """);
                if (response >= 1 && response <= 3) {
                    return response;
                }
            }
            if (partyMoveability[0] && partyMoveability[1] && !partyMoveability[2]) {
                //only able to move left and right
                response = Console.getIntInput("""
                        1. Turn left
                        2. Turn right
                        """);
                if (response >= 1 && response <= 2) {
                    return response;
                }
            }
            if (partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                //only able to move left and straight
                response = Console.getIntInput("""
                        1. Turn left
                        3. Keep straight
                        """);
                if (response >= 1 && response <= 3 && response != 2) {
                    return response;
                }
            }
            if (!partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                //only able to move right and straight
                response = Console.getIntInput("""
                        2. Turn right
                        3. Keep straight
                        """);
                if (response >= 2 && response <= 3) {
                    return response;
                }
            }
            if (!partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                //only able to move forward
                response = Console.getIntInput("3. Keep straight");
                if (response == 3) {
                    return response;
                }
            }
            if (partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                //only able to move left
                response = Console.getIntInput("1. Turn left");
            }
            if (!partyMoveability[0] && partyMoveability[1] && !partyMoveability[2]) {
                //only able to move right
                response = Console.getIntInput("2. Turn right");
                if (response == 2) {
                    return response;
                }
            }
            if (!partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                //dead end TURN AROUND!!
                response = Console.getIntInput("4. Turn back");
                if (response == 4) {
                    return response;
                }
            }
        } while (true);
    }

    public static void displayGameOver() {
        Console.writeLn("You lost!", Console.TextColor.RED);
    }

    public static int getAttackOption(Player player) {
        return Console.getIntInput("""
                ---- What do you want to do? ----
                1. Attack
                2. Magic Attack
                3. Special Attack (""" + player.getAvailableSpecialAttacks() + ")" +
                "\n4. Items" + "\n5. Open Inventory");
    }

    public static int getEnemyBeingAttacked(ArrayList<Lackie> enemies) {
        String listOfEnemies = getListOfEnemies(enemies);
        do {
            Console.writeLn("---- Which enemy are you targeting ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput(listOfEnemies);
            if (response > 0 && response <= enemies.size()) {
                return response;
            } else {
                Console.writeLn("Please enter a valid enemy number!", Console.TextColor.RED);
            }
        } while (true);
    }

    private static String getListOfEnemies(ArrayList<Lackie> enemies) {
        String listOfEnemies = "";
        for (int i = 0; i < enemies.size(); i++) {
            Lackie currentEnemy = enemies.get(i);
            if(!currentEnemy.isDead()) {
                listOfEnemies += "\n" + (i + 1) + " " + currentEnemy.getName() + " HP: " + currentEnemy.getBadGuyHealth() + "/" + currentEnemy.getMaxHealth();
            }
        }
        return listOfEnemies;
    }

    public static void displayEnemyHit(Lackie enemy, int amountOfDamage) {
        Console.writeLn(enemy.getName() + " was hit for " + amountOfDamage + "DMG!", Console.TextColor.BLUE);
    }

    public static void displayPlayerHit(int playerNo, int amountOfDamage, Player player){
        Console.writeLn("Player " + playerNo + " was hit for " + amountOfDamage + "DMG!" +
                "\nRemaining HP: " + player.getHealth() + "/" + player.getMaxHP());
    }

    public static int displayPlayerInventory(Player player) {
        Console.writeLn("---- Inventory ----", Console.TextColor.YELLOW);
        Console.writeLn(player.getInventory());
        do {
            Console.writeLn("---- What do you want to do? ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput("""
                    1. Select a new Weapon
                    2. Use an Item
                    3. Exit
                    """);
            if (response > 0 && response < 4) {
                return response;
            } else {
                Console.writeLn("Please enter a valid input", Console.TextColor.RED);
            }
        } while (true);
    }

    public static void displayWin(int amountOfGold, boolean isMultiPlayer) {
        Console.writeLn("YOU WIN!", Console.TextColor.YELLOW);
        if(isMultiPlayer){
            Console.writeLn("Each player got " + (amountOfGold / 2) + " GP");
        } else {
            Console.writeLn("Player got " + amountOfGold + " GP");
        }
    }

    public static void displayEnemies(ArrayList<Lackie> enemies) {
        String foundEnemies = "";
        for (Lackie enemy :
                enemies) {
            if (enemy instanceof SecretBoss) {
                foundEnemies += enemy.getName() + " now prove yourself!!";
            } else {
                foundEnemies += " and a " + enemy.getName();
            }
        }
        System.out.println("You have found " + foundEnemies);
    }

    public static int getWeaponBeingSwitchedTo(Player player) {
        String weaponString = getWeaponString(player);
        do {
            int response = Console.getIntInput(weaponString);
            if(response > 0 && response <= player.getWeapons().size()){
                return response;
            } else {
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        }while(true);
    }

    private static String getWeaponString(Player player){
        String weaponString = "";
        int amountOfWeapons = player.getWeapons().size();
        for (int i = 0; i < amountOfWeapons; i++) {
            Weapon weapon = player.getWeapons().get(i);
            if (player.getSelectedWeapon() == weapon) {
                weaponString += "\n" + (i + 1) + ". " + weapon.getWeaponName() + " (selected)";
            } else {
                weaponString += "\n" + (i + 1) + ". " + weapon.getWeaponName();
            }
        }
        weaponString += "\n" + (amountOfWeapons + 1) + ". Back";
        return weaponString;
    }

    public static int getItemBeingUsed(Player player) {
        String itemString = getItemString(player);
        do{
            int response = Console.getIntInput(itemString);
            if(response == 1 && player.getItems().size() == 0){
                return response;
            }
            if(response > 0 && response <= player.getItems().size()){
                return response;
            } else {
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        }while(true);
    }

    private static String getItemString(Player player) {
        String itemString = "";
        int amountOfItems = player.getItems().size();
        if(amountOfItems == 0){
            return "You currently have no items\n1. Back";
        }
        for (int i = 0; i < amountOfItems; i++) {
            itemString += "\n" + (i + 1) + ". " + player.getItems().get(i);
        }
        itemString += "\n" + amountOfItems + ". Back";
        return itemString;
    }

    public static int getSpellBeingUsed(Player player) {
        String spellString = getSpellString(player);
        do{
            int response = Console.getIntInput(spellString);
            if(player.getSpells().size() == 0 && response == 1){
                return response;
            }
            if(response > 0 && response <= player.getSpells().size()){
                return response;
            } else{
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        }while(true);
    }

    private static String getSpellString(Player player){
        String spellString = "";
        int amountOfSpells = player.getSpells().size();
        if(amountOfSpells == 0){
            return "You Currently have no spells\n1. Back";
        }
        for (int i = 0; i < amountOfSpells; i++) {
            spellString += "\n" + (i + 1) + ". " + player.getSpells().get(i);
        }
        spellString += "\n" + amountOfSpells + ". Back";
        return spellString;
    }

    public static boolean isTargetEnemy() {
        return Console.getBooleanInput("""
                ---- Are you targeting a enemy? ----
                1. Yes
                2. No
                """, "1", "2");
    }

    public static int getSelectedPlayer(ArrayList<Player> players) {
        String playerString = getPlayerString(players);
        do {
            Console.writeLn("---- Which player will you choose ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput(playerString);
            if(response > 0 && response <= players.size()){
                return response;
            } else{
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        }while(true);
    }

    private static String getPlayerString(ArrayList<Player> players) {
        String playerString = "";
        for (int i = 0; i < players.size(); i++) {
            int playerNum = i + 1;
            Player currentPlayer = players.get(i);
            playerString += "\n" + playerNum + "Player " + playerNum +
                    " HP: " + currentPlayer.getHealth() + "/" + currentPlayer.getMaxHP() +
                    " MP: " + currentPlayer.getMagic() + "/" + currentPlayer.getMaxMagic();
        }
        playerString += "\n" + players.size() + ". Back";
        return playerString;
    }

    public static void spellCantBeUsed() {
        Console.writeLn("This spell cannot be used in this way", Console.TextColor.RED);
    }

    public static boolean getSpecialConfirmation() {
        return Console.getBooleanInput("""
                ---- Are you sure you want to do this? ----
                1. Yes
                2. No
                """, "1", "2", Console.TextColor.YELLOW);
    }

    public static void displaySpecialAttack(Player player, int damage, Lackie enemy) {
        Console.writeLn("!!!!SPECIAL ATTACK!!!!\n" + player.getSelectedWeapon().getWeaponName() +
                " did " + damage + " DMG to " + enemy.getName() + "\nRemaining uses: " + player.getAvailableSpecialAttacks());
    }

    public static void displayTurnOver() {
        Console.writeLn("Your turn is now over", Console.TextColor.GREEN);
    }

    public static void displayGold(ArrayList<Player> players, boolean multiplayer) {
        Console.writeLn("Player 1 now has " + players.get(0).getGold() + " GP!", Console.TextColor.YELLOW);
        if(multiplayer){
            Console.writeLn("Player 2 now has " + players.get(1).getGold() + " GP!", Console.TextColor.YELLOW);
        }
    }

    public static void displayFirstTurn(boolean enemyGoFirst) {
        if(enemyGoFirst){
            Console.writeLn("Enemies are going first!", Console.TextColor.RED);
        } else {
            Console.writeLn("Player(s) are going first!", Console.TextColor.RED);
        }
    }

    public static void deadEnemySelected() {
        Console.writeLn("The enemy that you chose is already dead!", Console.TextColor.RED);
    }
}
