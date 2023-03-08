/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:08 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.views;
 */
package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Chest;
import edu.neumont.csc150.models.Map;
import edu.neumont.csc150.models.Shop;
import edu.neumont.csc150.models.items.*;
import edu.neumont.csc150.models.npc.bosses.Baraka;
import edu.neumont.csc150.models.npc.bosses.Boss;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.npc.secretbosses.SecretBoss;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.models.weapons.*;

import java.util.ArrayList;

public class GameUI {

    public static int displayMainMenu() {
        Console.writeLn("---- Off Brand Mortal Kombat Dungeon Crawler ----", Console.TextColor.RED);
        return Console.getIntInput("""
                1. Single Player
                2. Multiplayer (2P)
                3. Exit
                """);
    }

    /**
     * 1. left
     * 2. right
     * 3. straight
     * 4. turn back
     * 5. next floor
     *
     * @param currentMap the current map
     * @return 1. left
     * 2. right
     * 3. straight
     * 4. turn back
     * 5. next floor
     */
    public static int getMovementOptions(Map currentMap) {
        boolean[] partyMoveability = currentMap.getMoveability();
        Console.writeLn("---- Where do you want to move ----", Console.TextColor.CYAN);
        int response;
        do {
            if (!Map.canGoToNextFloor()) {
                if (partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                    //able to move in all directions
                    response = Console.getIntInput("""
                            1. Turn left
                            2. Turn right
                            3. Keep straight
                            6. Open Inventory
                            """);
                    return response;
                }
                if (partyMoveability[0] && partyMoveability[1] && !partyMoveability[2]) {
                    //only able to move left and right
                    response = Console.getIntInput("""
                            1. Turn left
                            2. Turn right
                            6. Open Inventory
                            """);
                    return response;
                }
                if (partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                    //only able to move left and straight
                    response = Console.getIntInput("""
                            1. Turn left
                            3. Keep straight
                            6. Open Inventory
                            """);
                    return response;
                }
                if (!partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                    //only able to move right and straight
                    response = Console.getIntInput("""
                            2. Turn right
                            3. Keep straight
                            6. Open Inventory
                            """);
                    return response;
                }
                if (!partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                    //only able to move forward
                    response = Console.getIntInput("3. Keep straight\n6. Open Inventory");
                    return response;
                }
                if (partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                    //only able to move left
                    response = Console.getIntInput("1. Turn left\n6. Open Inventory");
                    return response;
                }
                if (!partyMoveability[0] && partyMoveability[1] && !partyMoveability[2]) {
                    //only able to move right
                    response = Console.getIntInput("2. Turn right\n6. Open Inventory");
                    return response;
                }
                if (!partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                    //dead end TURN AROUND!!
                    response = Console.getIntInput("4. Turn back\n6. Open Inventory");
                    return response;
                }
            } else {
                if (partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                    //able to move in all directions
                    response = Console.getIntInput("""
                            1. Turn left
                            2. Turn right
                            3. Keep straight
                            5. Go to next floor
                            6. Open Inventory
                            """);
                    return response;
                }
                if (partyMoveability[0] && partyMoveability[1] && !partyMoveability[2]) {
                    //only able to move left and right
                    response = Console.getIntInput("""
                            1. Turn left
                            2. Turn right
                            5. Go to next floor
                            6. Open Inventory
                            """);
                    return response;
                }
                if (partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                    //only able to move left and straight
                    response = Console.getIntInput("""
                            1. Turn left
                            3. Keep straight
                            5. Go to next floor
                            6. Open Inventory
                            """);
                    return response;
                }
                if (!partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                    //only able to move right and straight
                    response = Console.getIntInput("""
                            2. Turn right
                            3. Keep straight
                            5. Go to next floor
                            6. Open Inventory
                            """);
                    return response;
                }
                if (!partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                    //only able to move forward
                    response = Console.getIntInput("3. Keep straight\n5. Go to next floor\n6. Open Inventory");
                    return response;
                }
                if (partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                    //only able to move left
                    response = Console.getIntInput("1. Turn left\n5. Go to next floor\n6. Open Inventory");
                    return response;
                }
                if (!partyMoveability[0] && partyMoveability[1] && !partyMoveability[2]) {
                    //only able to move right
                    response = Console.getIntInput("2. Turn right\n5. Go to next floor\n6. Open Inventory");
                    return response;
                }
                if (!partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                    //dead end TURN AROUND!!
                    response = Console.getIntInput("4. Turn back\n5. Go to next floor\n6. Open Inventory");
                    return response;
                }
            }
        } while (true);
    }

    public static void displayGameOver() {
        Console.writeLn("\n****---- You lost! ----****\n", Console.TextColor.RED);
    }

    public static int getAttackOption(Player player) {
        Console.writeLn("---- What do you want to do? ----", Console.TextColor.YELLOW);
        return Console.getIntInput("""
                1. Attack
                2. Magic
                3. Special Attack (""" + player.getAvailableSpecialAttacks() + ")" +
                "\n4. Items" + "\n5. Open Inventory");
    }

    public static int getEnemyBeingAttacked(ArrayList<Lackie> enemies) {
        String listOfEnemies = getListOfEnemies(enemies);
        do {
            Console.writeLn("---- Which enemy are you targeting ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput(listOfEnemies);
            if (response > 0 && response <= enemies.size() + 1) {
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
            if (!currentEnemy.isDead()) {
                listOfEnemies += "\n" + (i + 1) + " " + currentEnemy.getName() + " HP: " + currentEnemy.getBadGuyHealth() + "/" + currentEnemy.getMaxHealth();
            }
        }
        listOfEnemies += "\n" + (enemies.size() + 1) + ". Back";
        return listOfEnemies;
    }

    public static void displayEnemyHit(Lackie enemy, int amountOfDamage) {
        Console.writeLn(enemy.getName() + " was hit for " + amountOfDamage + "DMG!", Console.TextColor.BLUE);
        displayEnemyHealth(enemy);
    }

    public static void displayPlayerHit(int playerNo, int amountOfDamage, Player player) {
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
        Console.writeLn("\n****---- YOU WIN! ----****", Console.TextColor.YELLOW);
        if (isMultiPlayer) {
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
            } else if(enemy != enemies.get(0)){
                foundEnemies += " and a " + enemy.getName();
            } else {
                foundEnemies += "a " + enemy.getName();
            }
        }
        Console.writeLn("You have found " + foundEnemies, Console.TextColor.RED);
        for (Lackie enemy :
                enemies) {
            displayEnemyHealth(enemy);
        }
    }

    public static int getWeaponBeingSwitchedTo(Player player) {
        String weaponString = getWeaponString(player);
        do {
            Console.writeLn("---- Choose wisely ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput(weaponString);
            if (response > 0 && response <= player.getWeapons().size() + 1) {
                return response;
            } else {
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        } while (true);
    }

    private static String getWeaponString(Player player) {
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
        do {
            Console.writeLn("---- Items ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput(itemString);
            if (response == 1 && player.getItems().size() == 0) {
                return response;
            }
            if (response > 0 && response <= player.getItems().size() + 1) {
                return response;
            } else {
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        } while (true);
    }

    private static String getItemString(Player player) {
        String itemString = "";
        int amountOfItems = player.getItems().size();
        if (amountOfItems == 0) {
            return "You currently have no items\n1. Back";
        }
        for (int i = 0; i < amountOfItems; i++) {
            itemString += "\n" + (i + 1) + ". " + player.getItems().get(i).getItemName();
        }
        itemString += "\n" + (amountOfItems + 1) + ". Back";
        return itemString;
    }

    public static int getSpellBeingUsed(Player player) {
        String spellString = getSpellString(player);
        do {
            int response = Console.getIntInput(spellString);
            if (player.getSpells().size() == 0 && response == 1) {
                return response;
            }
            if (response > 0 && response <= player.getSpells().size() + 1) {
                return response;
            } else {
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        } while (true);
    }

    private static String getSpellString(Player player) {
        String spellString = "";
        int amountOfSpells = player.getSpells().size();
        if (amountOfSpells == 0) {
            return "You Currently have no spells\n1. Back";
        }
        for (int i = 0; i < amountOfSpells; i++) {
            Spell currentSpell = player.getSpells().get(i);
            spellString += "\n" + (i + 1) + ". " + currentSpell.getSpellName() + "\nMP Cost: " + currentSpell.magicPoint();
        }
        spellString += "\n" + (amountOfSpells + 1) + ". Back";
        return spellString;
    }

    public static boolean isTargetEnemy() {
        Console.writeLn("---- Are you targeting a enemy? ----", Console.TextColor.YELLOW);
        return Console.getBooleanInput("""
                1. Yes
                2. No
                """, "1", "2");
    }

    public static int getSelectedPlayer(ArrayList<Player> players) {
        String playerString = getPlayerString(players);
        do {
            Console.writeLn("---- Which player will you choose ----", Console.TextColor.YELLOW);
            int response = Console.getIntInput(playerString);
            if (response > 0 && response <= players.size() + 1) {
                return response;
            } else {
                Console.writeLn("Please enter a valid input!", Console.TextColor.RED);
            }
        } while (true);
    }

    private static String getPlayerString(ArrayList<Player> players) {
        String playerString = "";
        for (int i = 0; i < players.size(); i++) {
            int playerNum = i + 1;
            Player currentPlayer = players.get(i);
            playerString += "\n" + playerNum + ". Player " + playerNum +
                    " HP: " + currentPlayer.getHealth() + "/" + currentPlayer.getMaxHP() +
                    " MP: " + currentPlayer.getMagic() + "/" + currentPlayer.getMaxMagic();
        }
        playerString += "\n" + (players.size() + 1) + ". Back";
        return playerString;
    }

    public static void spellCantBeUsed() {
        Console.writeLn("This spell cannot be used in this way", Console.TextColor.RED);
    }

    public static void itemCantBeUsed() {
        Console.writeLn("This item cannot be used in this way", Console.TextColor.RED);
    }

    public static boolean getSpecialConfirmation() {
        return Console.getBooleanInput("""
                ---- Are you sure you want to do this? ----
                1. Yes
                2. No
                """, "1", "2", Console.TextColor.YELLOW);
    }

    public static void displayPlayerSpecialAttack(Player player, int damage, Lackie enemy) {
        Console.writeLn("!!!!SPECIAL ATTACK!!!!", Console.TextColor.CYAN);
        Console.writeLn(player.getSelectedWeapon().getWeaponName() +
                " did " + damage + " DMG to " + enemy.getName() + "\nRemaining uses: " + player.getAvailableSpecialAttacks());
        displayEnemyHealth(enemy);
    }

    public static void displayTurnOver() {
        Console.writeLn("Your turn is now over", Console.TextColor.GREEN);
    }

    public static void displayGold(ArrayList<Player> players, boolean multiplayer) {
        Console.writeLn("Player 1 now has " + players.get(0).getGold() + " GP!", Console.TextColor.YELLOW);
        if (multiplayer) {
            Console.writeLn("Player 2 now has " + players.get(1).getGold() + " GP!", Console.TextColor.YELLOW);
        }
    }

    public static void displayFirstTurn(boolean enemyGoFirst) {
        if (enemyGoFirst) {
            Console.writeLn("Enemies are going first!", Console.TextColor.RED);
        } else {
            Console.writeLn("Player(s) are going first!", Console.TextColor.GREEN);
        }
    }

    public static void deadEnemySelected() {
        Console.writeLn("The enemy that you chose is already dead!", Console.TextColor.RED);
    }

    public static void displayPlayerHeal(int healAmount, Player player) {
        Console.writeLn("You healed " + healAmount + " HP\nCurrent HP: " +
                player.getHealth() + "/" + player.getMaxHP(), Console.TextColor.GREEN);
    }

    public static void weaponAlreadySelected() {
        Console.writeLn("The weapon you chose is already selected!", Console.TextColor.RED);
    }

    public static void displayNewSelectedWeapon(Weapon newWeapon) {
        Console.writeLn(newWeapon.getWeaponName() + " is now selected!!", Console.TextColor.YELLOW);
    }

    public static void displaySpellOnEnemy(Spell spell, Lackie enemy) {
        String spellName = spell.getSpellName();
        if (spell instanceof FireBall fireBall) {
            Console.writeLn(spellName + " has been thrown at " + enemy.getName() + " and did " + fireBall.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof Heal heal) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and healed " + heal.HEAL_AMOUNT + " HP", Console.TextColor.BLUE);
        } else if (spell instanceof IceSpike iceSpike) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and did " + iceSpike.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof Implosion implosion) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and did " + implosion.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof LightningStrike lightningStrike) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and did " + lightningStrike.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof SpeedUp speedUp) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and brought their speed up by " +
                    speedUp.AMOUNT_UP + " points", Console.TextColor.BLUE);
            displayEnemySpeed(enemy);
            return;
        } else if (spell instanceof StrengthUp) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " their next attack will deal double damage!", Console.TextColor.BLUE);
            return;
        } else if (spell instanceof TornadoSpin tornadoSpin) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and did " + tornadoSpin.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof WaterStrike waterStrike) {
            Console.writeLn(spellName + " has been used on " + enemy.getName() + " and did " + waterStrike.DAMAGE + " DMG", Console.TextColor.BLUE);
        }
        displayEnemyHealth(enemy);
    }

    private static void displayEnemyHealth(Lackie enemy) {
        Console.writeLn(enemy.getName() + " HP: " + enemy.getBadGuyHealth() + "/" + enemy.getMaxHealth());
    }

    public static void displaySpellOnPlayer(Spell spell, Player player) {
        String spellName = spell.getSpellName();
        if (spell instanceof FireBall fireBall) {
            Console.writeLn(spellName + " has been used on a player and did " + fireBall.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof Heal heal) {
            Console.writeLn(spellName + " has been used on a player and healed " + heal.HEAL_AMOUNT + " HP", Console.TextColor.BLUE);
            displayPlayerHeal(heal.HEAL_AMOUNT, player);
            return;
        } else if (spell instanceof IceSpike iceSpike) {
            Console.writeLn(spellName + " has been used on a player and did " + iceSpike.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof Implosion implosion) {
            Console.writeLn(spellName + " has been used on a player and did " + implosion.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof LightningStrike lightningStrike) {
            Console.writeLn(spellName + " has been used on a player and did " + lightningStrike.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof MPRecovery mpRecovery) {
            Console.writeLn(spellName + " has been used on a player and they received " + mpRecovery.RECOVER_AMOUNT + " MP", Console.TextColor.BLUE);
            displayPlayerMP(player);
            return;
        } else if (spell instanceof SpeedUp speedUp) {
            Console.writeLn(spellName + " has been used on a player and they received " + speedUp.AMOUNT_UP + " SPD", Console.TextColor.BLUE);
            displayPlayerSpeed(player);
        } else if (spell instanceof StrengthUp) {
            Console.writeLn(spellName + " has been used on a player and their next attack will deal double damage!", Console.TextColor.BLUE);
            return;
        } else if (spell instanceof TornadoSpin tornadoSpin) {
            Console.writeLn(spellName + " has been used on a player and did " + tornadoSpin.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof WaterStrike waterStrike) {
            Console.writeLn(spellName + " has been used on a player and did " + waterStrike.DAMAGE + " DMG", Console.TextColor.BLUE);
        } else if (spell instanceof NoSpell) {
            Console.writeLn(spellName + " has been used on a player... but it did nothing!", Console.TextColor.BLUE);
            return;
        }
        displayPlayerHealth(player);
    }

    public static void displayItemUsedOnEnemy(Item item, Lackie enemy) {
        String itemName = item.getItemName();
        if (item instanceof BigHeal bigHeal) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and healed " + bigHeal.HEAL_AMOUNT + " HP", Console.TextColor.YELLOW);
        }
        if (item instanceof Grenade grenade) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and did " + grenade.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        if (item instanceof MaxHealPotion) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and fully healed them", Console.TextColor.YELLOW);
        }
        if (item instanceof MediumHeal mediumHeal) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and healed " + mediumHeal.HEAL_AMOUNT + " HP", Console.TextColor.YELLOW);
        }
        if (item instanceof ShockStick shockStick) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and did " + shockStick.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        if (item instanceof SmallHeal smallHeal) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and healed " + smallHeal.HEAL_AMOUNT + " HP", Console.TextColor.YELLOW);
        }
        if (item instanceof ThrowingKnife throwingKnife) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and did " + throwingKnife.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        if (item instanceof Tomahawk tomahawk) {
            Console.writeLn(itemName + " has been used on " + enemy.getName() + " and did " + tomahawk.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        displayEnemyHealth(enemy);
    }

    public static void displayItemUsedOnPlayer(Item item, Player player) {
        String itemName = item.getItemName();
        if (item instanceof BigHeal bigHeal) {
            Console.writeLn(itemName + " has been used on a player and healed " + bigHeal.HEAL_AMOUNT + " HP", Console.TextColor.YELLOW);
        }
        if (item instanceof Grenade grenade) {
            Console.writeLn(itemName + " has been used on a player and did " + grenade.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        if (item instanceof MaxHealPotion) {
            Console.writeLn(itemName + " has been used on a player and fully healed them", Console.TextColor.YELLOW);
        }
        if (item instanceof MediumHeal mediumHeal) {
            Console.writeLn(itemName + " has been used on a player and healed " + mediumHeal.HEAL_AMOUNT + " HP", Console.TextColor.YELLOW);
        }
        if (item instanceof MaxMpPotion) {
            Console.writeLn(itemName + " has been used on a player and fully restored their MP");
            displayPlayerMP(player);
            return;
        }
        if (item instanceof MpPotion mpPotion) {
            Console.writeLn(itemName + " has been used on a player and restored " + mpPotion.RECOVER_AMOUNT + " MP", Console.TextColor.YELLOW);
            displayPlayerMP(player);
            return;
        }
        if (item instanceof ShockStick shockStick) {
            Console.writeLn(itemName + " has been used on a player and did " + shockStick.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        if (item instanceof SmallHeal smallHeal) {
            Console.writeLn(itemName + " has been used on a player and healed " + smallHeal.HEAL_AMOUNT + " HP", Console.TextColor.YELLOW);
        }
        if (item instanceof ThrowingKnife throwingKnife) {
            Console.writeLn(itemName + " has been used on a player and did " + throwingKnife.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        if (item instanceof Tomahawk tomahawk) {
            Console.writeLn(itemName + " has been used on a player and did " + tomahawk.DAMAGE + " DMG", Console.TextColor.YELLOW);
        }
        displayPlayerHealth(player);
    }

    private static void displayPlayerSpeed(Player player) {
        Console.writeLn("Player Speed: " + player.getSpeed(), Console.TextColor.YELLOW);
    }

    private static void displayEnemySpeed(Lackie enemy) {
        Console.writeLn(enemy.getName() + " Speed: " + enemy.getBadGuySpeed(), Console.TextColor.YELLOW);
    }

    private static void displayPlayerHealth(Player player) {
        Console.writeLn("Player Health: " + player.getHealth() + "/" + player.getMaxHP(), Console.TextColor.RED);
    }

    public static void displayPlayerTurn(Player player) {
        Console.writeLn("---- Player Turn ----", Console.TextColor.YELLOW);
        Console.writeLn("HP: " + player.getHealth() + "/" + player.getMaxHP(), Console.TextColor.RED);
        displayPlayerMP(player);
        Console.writeLn("Selected Weapon: " + player.getSelectedWeapon().getWeaponName() +
                "\nAmount of attacks per turn " + player.getAmountOfAttacks(), Console.TextColor.PURPLE);
    }

    public static void displayPlayerMP(Player player) {
        Console.writeLn("MP: " + player.getMagic() + "/" + player.getMaxMagic(), Console.TextColor.BLUE);
    }

    public static void displayStartBattle() {
        Console.writeLn("---- BATTLE HAS STARTED ----", Console.TextColor.RED);
    }

    public static void displayAttacksLeft(int attacksRemaining) {
        Console.writeLn("You have " + attacksRemaining + " attacks remaining!", Console.TextColor.CYAN);
    }

    public static void displayAttackScreen() {
        Console.writeLn("---- Attacking ----", Console.TextColor.RED);
    }

    public static void displayEnemyTurn() {
        Console.writeLn("---- Enemy Turn ----", Console.TextColor.RED);
    }

    public static void displayMagicMenu() {
        Console.writeLn("---- Magic ----", Console.TextColor.BLUE);
    }

    public static void displayPlayerRevived() {
        Console.writeLn("---- PLAYER HAS BEEN REVIVED ----", Console.TextColor.CYAN);
    }

    public static void displayEnemyIsRevived(String message) {
        Console.writeLn(message, Console.TextColor.CYAN);
    }

    public static void displayEnemySpecialAttack(Boss enemy) {
        if (enemy instanceof Baraka baraka) {
            Console.writeLn(baraka.getName() + " used ");
        }
        Console.writeLn(enemy.getName() + " used a special attack!!");
    }

    public static void displayInvalidMovement() {
        Console.writeLn("Please enter a valid movement option", Console.TextColor.RED);
    }

    public static void displayChest(Chest chest) {
        ArrayList<Item> items = chest.getItems();
        String itemString = "";
        for (Item item : items) {
            itemString += "\n" + item.getItemName();
        }
        Console.writeLn("**-- You found a chest! --**", Console.TextColor.PURPLE);
        Console.writeLn("Inside the chest you found" + itemString);
    }

    public static boolean isPlayerOneGettingItem(Item item, ArrayList<Player> players) {
        Console.writeLn("---- Who is getting " + item.getItemName() + "? ----", Console.TextColor.YELLOW);
        Console.writeLn("1. Player 1", Console.TextColor.YELLOW);
        Console.writeLn(players.get(0).getInventory());
        Console.writeLn("2. Player 2", Console.TextColor.YELLOW);
        return Console.getBooleanInput(players.get(1).getInventory(), "1", "2");
    }

    public static void displayItemAdded(Item item, Player selectedPlayer) {
        Console.writeLn("You stuff your new " + item.getItemName() + " into your fanny pack", Console.TextColor.YELLOW);
        Console.writeLn("---- Your new inventory ----", Console.TextColor.GREEN);
        Console.writeLn(selectedPlayer.getInventory());
    }

    public static void displayItemsAdded(ArrayList<Item> items, Player player) {
        Console.writeLn("You grasp the items out of the chest as fast as you can and shove them into your fanny pack", Console.TextColor.YELLOW);
        Console.writeLn("---- Items added ----", Console.TextColor.GREEN);
        for (Item item :
                items) {
            Console.writeLn(item.getItemName());
        }
        Console.writeLn("---- Your new inventory ----", Console.TextColor.GREEN);
        Console.writeLn(player.getInventory());
    }

    public static void displayShop(Shop shop) {
        Console.writeLn(shop.toString(true));
        Console.writeLn("");
        Console.writeLn(shop.toString(false));
    }

    public static int displayShopSpells(Shop shop) {
        do {
            int response = Console.getIntInput(shop.toString(true) + "\n---- What spell do you want to buy ----");
            if (response >= 1 && response <= 11) {
                return response;
            } else {
                Console.writeLn("Enter a valid input", Console.TextColor.RED);
            }
        } while (true);
    }

    public static int displayShopItems(Shop shop) {
        do {
            int response = Console.getIntInput(shop.toString(false) + "\n---- What item do you want to buy ----");
            if (response >= 1 && response <= 10) {
                return response;
            } else {
                Console.writeLn("Enter a valid input", Console.TextColor.RED);
            }
        } while (true);
    }

    public static void displaySpellAdded(Player player, Spell selectedSpell) {
        Console.writeLn("You grab the scroll of " + selectedSpell.getSpellName() + " and it burns away in your hand.", Console.TextColor.YELLOW);
        Console.writeLn("You are now able to cast " + selectedSpell.getSpellName(), Console.TextColor.BLUE);
        Console.writeLn(player.getInventory());
    }

    public static int getShopSelection() {
        Console.writeLn("---- What do you want to do ----", Console.TextColor.YELLOW);
        do {
            int response = Console.getIntInput("""
                    1. Buy a spell
                    2. Buy an item
                    3. Don't spend your money >:(
                    """);
            if (response >= 1 && response <= 3) {
                return response;
            } else {
                Console.writeLn("Enter a valid input", Console.TextColor.RED);
            }
        } while (true);
    }

    public static void displayNotEnoughMoney() {
        Console.writeLn("You don't have enough money", Console.TextColor.RED);
    }

    public static void displaySpellException() {
        Console.writeLn("You already have that spell", Console.TextColor.RED);
    }

    public static boolean getLevelMagicMore(boolean isPlayer1) {
        if (isPlayer1) {
            Console.writeLn("Player 1", Console.TextColor.YELLOW);
        } else {
            Console.writeLn("Player 2", Console.TextColor.YELLOW);
        }
        return Console.getBooleanInput("""
                Do you want to level your MP or HP more?
                1. MP
                2. HP
                """, "1", "2");
    }

    public static void displayLevelUp(Player player, boolean isPlayer1) {
        if (isPlayer1) {
            Console.write("Player 1 ", Console.TextColor.YELLOW);
        } else {
            Console.write("Player 2 ", Console.TextColor.YELLOW);
        }
        Console.writeLn("Leveled UP!", Console.TextColor.YELLOW);
        Console.writeLn("** NEW STATS **", Console.TextColor.CYAN);
        Console.writeLn("MAX HP: " + player.getMaxHP(), Console.TextColor.RED);
        Console.writeLn("MAX MP: " + player.getMaxMagic(), Console.TextColor.BLUE);
    }

    public static void displayBossBeaten(Boss boss) {
        Console.writeLn(boss.getName() + " has been beaten", Console.TextColor.GREEN);
    }

    public static void displayBossDropWeapon(Boss boss) {
        Console.writeLn(boss.getName() + " has dropped " + boss.dropWeapon().getWeaponName());
    }

    public static void displayWhoWillGetWeapon() {
        Console.writeLn("Who do you want to get the weapon", Console.TextColor.YELLOW);
    }

    public static void displayPlayerGotWeapon(Player player, Weapon weapon) {
        Console.writeLn("You pick up the " + weapon.getWeaponName() +
                " and store it away in your pocket dimension", Console.TextColor.GREEN);
        Console.writeLn("---- Your new inventory ----", Console.TextColor.YELLOW);
        Console.writeLn(player.getInventory());
    }

    public static void displayEnemyDroppedUpgrades(int healthUpgradeAmount, int magicUpgradeAmount, SecretBoss secret, ArrayList<Player> players) {
        Console.writeLn(secret.getName() + " dropped a red and blue orb", Console.TextColor.GREEN);
        for (Player player :
                players) {
            if(player == players.get(0)) {
                Console.writeLn("Player 1 touched the red orb and their max health went up " + healthUpgradeAmount + " points!", Console.TextColor.RED);
                Console.writeLn("Player 1's new health is " + player.getHealth() + "/" + player.getMaxHP());
                Console.writeLn("Player 1 touched the blue orb and their max magic went up " + magicUpgradeAmount + " points!", Console.TextColor.BLUE);
                Console.writeLn("Player 1's new magic is " + player.getMagic() + "/" + player.getMaxMagic());
            } else {
                Console.writeLn("Player 2 touched the red orb and their max health went up " + healthUpgradeAmount + " points!", Console.TextColor.RED);
                Console.writeLn("Player 2's new health is " + player.getHealth() + "/" + player.getMaxHP());
                Console.writeLn("Player 2 touched the blue orb and their max magic went up " + magicUpgradeAmount + " points!", Console.TextColor.BLUE);
                Console.writeLn("Player 2's new magic is " + player.getMagic() + "/" + player.getMaxMagic());
            }
        }
    }

    public static void displayGoldInShop(Player player) {
        Console.writeLn("---- You have " + player.getGold() + "GP ----", Console.TextColor.YELLOW);
    }
}
