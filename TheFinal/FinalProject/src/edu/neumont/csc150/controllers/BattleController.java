/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:40 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.exceptions.EnemyIsRevivedException;
import edu.neumont.csc150.exceptions.WeaponAlreadySelectedException;
import edu.neumont.csc150.models.Chest;
import edu.neumont.csc150.models.Map;
import edu.neumont.csc150.models.items.*;
import edu.neumont.csc150.models.npc.bosses.*;
import edu.neumont.csc150.models.npc.commonenemy.*;
import edu.neumont.csc150.models.npc.secretbosses.HsuHao;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.*;
import edu.neumont.csc150.models.weapons.Weapon;
import edu.neumont.csc150.views.Console;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;
import java.util.Random;

public class BattleController {

    //region variables
    private boolean isMultiplayer = true;
    private boolean battleOneOneDone;
    private boolean battleTwoOneDone;
    private boolean battleThreeOneDone;
    private boolean battleFourOneDone;
    private boolean battleFiveOneDone;
    private boolean battleSixOneDone;
    //Chests are false if not opened
    private boolean chestOneOne;
    private boolean chestTwoTwo;
    //endregion

    //region get/set
    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    public boolean isBattleOneOneDone() {
        return battleOneOneDone;
    }

    public void setBattleOneOneDone(boolean battleOneOneDone) {
        this.battleOneOneDone = battleOneOneDone;
    }

    public boolean isChestOneOne() {
        return chestOneOne;
    }

    public void setChestOneOne(boolean chestOneOne) {
        this.chestOneOne = chestOneOne;
    }

    public boolean isChestTwoTwo() {
        return chestTwoTwo;
    }

    public void setChestTwoTwo(boolean chestTwoTwo) {
        this.chestTwoTwo = chestTwoTwo;
    }

    public boolean isBattleTwoOneDone() {
        return battleTwoOneDone;
    }

    public void setBattleTwoOneDone(boolean battleTwoOneDone) {
        this.battleTwoOneDone = battleTwoOneDone;
    }

    public boolean isBattleThreeOneDone() {
        return battleThreeOneDone;
    }

    public void setBattleThreeOneDone(boolean battleThreeOneDone) {
        this.battleThreeOneDone = battleThreeOneDone;
    }

    public boolean isBattleFourOneDone() {
        return battleFourOneDone;
    }

    public void setBattleFourOneDone(boolean battleFourOneDone) {
        this.battleFourOneDone = battleFourOneDone;
    }

    public boolean isBattleFiveOneDone() {
        return battleFiveOneDone;
    }

    public void setBattleFiveOneDone(boolean battleFiveOneDone) {
        this.battleFiveOneDone = battleFiveOneDone;
    }

    public boolean isBattleSixOneDone() {
        return battleSixOneDone;
    }

    public void setBattleSixOneDone(boolean battleSixOneDone) {
        this.battleSixOneDone = battleSixOneDone;
    }
    //endregion

    //region chests
    public void checkForChest(int currentFloor, int currentPos, ArrayList<Player> players) {
        switch (currentFloor) {
            case 1:
                checkForChestFloorOne(currentPos, players);
                break;
            default:
                break;
        }
    }

    private void checkForChestFloorOne(int currentPos, ArrayList<Player> players) {
        switch (currentPos) {
            case 10:
                openChestOneOne(players);
                break;
            case 11:
                openChestTwoOne(players);
                break;
            default:
                break;
        }
    }

    private void openChestOneOne(ArrayList<Player> players) {
        Chest chest = new Chest(1);
        GameUI.displayChest(chest);
        if (isMultiplayer()) {
            for (Item item :
                    chest.getItems()) {
                Player selectedPlayer;
                if (GameUI.isPlayerOneGettingItem(item, players)) {
                    selectedPlayer = players.get(0);
                    selectedPlayer.giveItem(item);
                } else {
                    selectedPlayer = players.get(1);
                    selectedPlayer.giveItem(item);
                }
                GameUI.displayItemAdded(item, selectedPlayer);
            }
        } else {
            Player currentPlayer = players.get(0);
            ArrayList<Item> chestItems = chest.getItems();
            currentPlayer.giveItems(chestItems);
            GameUI.displayItemsAdded(chestItems, currentPlayer);
        }
        setChestOneOne(true);
    }

    private void openChestTwoOne(ArrayList<Player> players) {

    }
    //endregion

    //region battleChecks
    public boolean checkForBattle(int currentFloor, int currentPos) {
        switch (currentFloor) {
            case 1:
                return checkForBattleFloorOne(currentPos);
            default:
                return false;
        }
    }

    private boolean checkForBattleFloorOne(int currentPos) {
        return switch (currentPos) {
            case 1 -> !isBattleOneOneDone();
            case 3 -> !isBattleTwoOneDone();
            case 4 -> !isBattleThreeOneDone();
            case 6 -> !isBattleFourOneDone();
            case 8 -> !isBattleFiveOneDone();
            case 9 -> !isBattleSixOneDone();
            default -> false;
        };
    }

    public boolean battle(int currentFloor, int currentPos, ArrayList<Player> players) {
        switch (currentFloor) {
            case 1:
                return battleFloorOne(currentPos, players);
            default:
                return false;
        }
    }

    private boolean battleFloorOne(int currentPos, ArrayList<Player> players) {
        switch (currentPos) {
            case 1:
                return battleOneOne(players);
            case 3:
                return battleTwoOne(players);
            case 4:
                return battleThreeOne(players);
            case 6:
                return battleFourOne(players);
            case 8:
                return battleFiveOne(players);
            case 9:
                return battleSixOne(players);
            default:
                return false;
        }
    }
    //endregion

    //region battle setup
    private boolean battleOneOne(ArrayList<Player> players) {
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new Zombie());
        enemies.add(new Zombie());
        boolean win = commenceBattle(players, enemies);
        setBattleOneOneDone(win);
        return win;
    }

    private boolean battleTwoOne(ArrayList<Player> players) {
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new Skeleton());
        enemies.add(new Spider());
        boolean win = commenceBattle(players, enemies);
        setBattleTwoOneDone(win);
        return win;
    }

    private boolean battleThreeOne(ArrayList<Player> players) {
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new BlackDragonsGoons());
        boolean win = commenceBattle(players, enemies);
        setBattleThreeOneDone(win);
        return win;
    }

    private boolean battleFourOne(ArrayList<Player> players) {
        //Secret Boss Fight
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new HsuHao());
        boolean win = commenceBattle(players, enemies);
        if (win) {
            Map.setCanGoToNextFloor(true);
            //extract this code and make it a method for the secret bosses to drop items/spells/mp/hp upgrades
            if(players.size() == 2){
                int selectedPlayer = GameUI.getSelectedPlayer(players);
                if(selectedPlayer == 1){
                    players.get(0).giveItem
                }
            }
        }
        setBattleFourOneDone(win);
        return win;
    }

    private boolean battleFiveOne(ArrayList<Player> players) {
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new BlackDragonsGoons());
        enemies.add(new BlackDragonsGoons());
        boolean win = commenceBattle(players, enemies);
        setBattleFiveOneDone(win);
        return win;
    }

    private boolean battleSixOne(ArrayList<Player> players) {
        //Boss fight
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new Baraka());
        enemies.add(new Tarktans());
        enemies.add(new Tarktans());
        boolean win = commenceBattle(players, enemies);
        if (win) {
            Map.setCanGoToNextFloor(true);
        }
        setBattleSixOneDone(win);
        return win;
    }
    //endregion

    //region battleMethods
    private boolean commenceBattle(ArrayList<Player> players, ArrayList<Lackie> enemies) {
        //TODO: DO THIS NEXT! make it so the enemy has AI if they are a boss/secretBoss
        GameUI.displayStartBattle();
        GameUI.displayEnemies(enemies);
        boolean enemyGoFirst;
        Player player = players.get(0);
        enemyGoFirst = enemies.get(0).getBadGuySpeed() > player.getSpeed();
        GameUI.displayFirstTurn(enemyGoFirst);
        if (!isMultiplayer()) {
            if (enemyGoFirst) {
                do {
                    GameUI.displayEnemyTurn();
                    for (Lackie enemy :
                            enemies) {
                        if (!enemy.isDead()) {
                            if (enemy instanceof Boss) {
                                bossTurn((Boss) enemy, players, false, enemies);
                            } else {
                                enemyAttack(enemy, players, false);
                            }
                            if (player.isDead()) {
                                return false;
                            }
                        }
                    }
                    playerTurn(player, enemies, players);
                    if (isAllEnemiesDead(enemies)) {
                        battleWon(enemies, players);
                        return true;
                    }
                } while (true);
            } else {
                do {
                    playerTurn(player, enemies, players);
                    if (isAllEnemiesDead(enemies)) {
                        battleWon(enemies, players);
                        return true;
                    }
                    GameUI.displayEnemyTurn();
                    for (Lackie enemy :
                            enemies) {
                        if (!enemy.isDead()) {
                            if (enemy instanceof Boss) {
                                bossTurn((Boss) enemy, players, false, enemies);
                            } else {
                                enemyAttack(enemy, players, false);
                            }
                            if (player.isDead()) {
                                return false;
                            }
                        }
                    }
                } while (true);
            }
        } else {
            if (enemyGoFirst) {
                do {
                    //enemy turn
                    if (enemyTurn(players, enemies)) return false;
                    //players turn
                    if (getPlayersAlive(players) == 3) {
                        player = players.get(0);
                        playerTurn(player, enemies, players);
                        if (isAllEnemiesDead(enemies)) {
                            battleWon(enemies, players);
                            return true;
                        }
                        player = players.get(1);
                    } else if (getPlayersAlive(players) == 1) {
                        player = players.get(0);
                    } else if (getPlayersAlive(players) == 2) {
                        player = players.get(1);
                    } else {
                        return false;
                    }
                    if (!player.isDead()) {
                        playerTurn(player, enemies, players);
                    }
                    if (isAllEnemiesDead(enemies)) {
                        battleWon(enemies, players);
                        return true;
                    }
                } while (true);
            } else {
                do {
                    //players turn
                    if (getPlayersAlive(players) == 3) {
                        player = players.get(0);
                        playerTurn(player, enemies, players);
                        if (isAllEnemiesDead(enemies)) {
                            battleWon(enemies, players);
                            return true;
                        }
                        player = players.get(1);
                    } else if (getPlayersAlive(players) == 1) {
                        player = players.get(0);
                    } else if (getPlayersAlive(players) == 2) {
                        player = players.get(1);
                    } else {
                        return false;
                    }
                    playerTurn(player, enemies, players);
                    if (isAllEnemiesDead(enemies)) {
                        battleWon(enemies, players);
                        return true;
                    }
                    //enemy turn
                    if (enemyTurn(players, enemies)) return false;
                } while (true);
            }
        }
    }

    private void bossTurn(Boss enemy, ArrayList<Player> players, boolean isMultiplayer, ArrayList<Lackie> enemies) {
        do {
            int aiChoice = new Random().nextInt(4) + 1;
            if (enemy.isStrengthUp()) {
                aiChoice = 4;
            }
            switch (aiChoice) {
                case 1:
                    //special attack
                    if (bossSpecialAttack(enemy, players, isMultiplayer)) {
                        return;
                    }
                    break;
                case 2:
                    //spell
                    if (bossSpell(enemy, players, isMultiplayer, enemies)) {
                        return;
                    }
                    break;
                case 3:
                    //useItem
                    if (bossUseItem(enemy, players, isMultiplayer, enemies)) {
                        return;
                    }
                    break;
                default:
                    //attack
                    enemyAttack(enemy, players, isMultiplayer);
                    return;
            }
        } while (true);
    }

    private void enemyAttack(Lackie enemy, ArrayList<Player> players, boolean isMultiplayer) {
        int playerNo = getRandomPlayerIndex(players, isMultiplayer);
        Player selectedPlayer = players.get(playerNo);
        int damage = enemy.badGuyAttack();
        selectedPlayer.setHealth(selectedPlayer.getHealth() - damage);
        GameUI.displayPlayerHit(playerNo + 1, damage, selectedPlayer);
    }

    private boolean bossUseItem(Boss enemy, ArrayList<Player> players, boolean isMultiplayer, ArrayList<Lackie> enemies) {
        int randomPlayerIndex = getRandomPlayerIndex(players, isMultiplayer);
        int randomEnemyIndex = getRandomEnemyIndex(enemy, enemies, false);
        Item item = enemy.badGuyItem(players, randomEnemyIndex, enemies, randomPlayerIndex);
        if (item == null) {
            return false;
        }
        if (item instanceof BigHeal || item instanceof MaxHealPotion ||
                item instanceof MediumHeal || item instanceof SmallHeal) {
            GameUI.displayItemUsedOnEnemy(item, enemies.get(randomEnemyIndex));
        } else {
            GameUI.displayItemUsedOnPlayer(item, players.get(randomPlayerIndex));
        }
        return true;
    }

    private boolean bossSpell(Boss enemy, ArrayList<Player> players, boolean isMultiplayer, ArrayList<Lackie> enemies) {
        int randomEnemyIndex = getRandomEnemyIndex(enemy, enemies, true);
        int randomPlayerIndex = getRandomPlayerIndex(players, isMultiplayer);
        Spell spell = enemy.badGuySpell(players, randomEnemyIndex, enemies, randomPlayerIndex);
        if (spell instanceof Heal || spell instanceof SpeedUp || spell instanceof StrengthUp) {
            GameUI.displaySpellOnEnemy(spell, enemies.get(randomEnemyIndex));
        } else {
            GameUI.displaySpellOnPlayer(spell, players.get(randomPlayerIndex));
        }
        return true;
    }

    private int getRandomEnemyIndex(Boss enemy, ArrayList<Lackie> enemies, boolean isSpell) {
        int randomEnemyIndex = new Random().nextInt(enemies.size());
        boolean enemyHasHeal = false;
        if (isSpell) {
            for (Spell spell :
                    enemy.getBadGuySpells()) {
                if (spell instanceof Heal) {
                    enemyHasHeal = true;
                    break;
                }
            }
        } else {
            for (Item item :
                    enemy.getBadGuyItems()) {
                if (item instanceof BigHeal || item instanceof MaxHealPotion ||
                        item instanceof MediumHeal || item instanceof SmallHeal) {
                    enemyHasHeal = true;
                    break;
                }
            }
        }
        if (!enemyHasHeal) {
            int[] notDeadIndexes = new int[enemies.size()];
            int notDeadCount = 0;
            for (int i = 0; i < enemies.size(); i++) {
                if (!enemies.get(i).isDead()) {
                    notDeadIndexes[notDeadCount] = i;
                    notDeadCount++;
                }
            }
            randomEnemyIndex = notDeadIndexes[new Random().nextInt(notDeadCount)];
        }
        return randomEnemyIndex;
    }

    private int getRandomPlayerIndex(ArrayList<Player> players, boolean isMultiplayer) {
        int randomPlayerIndex = 0;
        int playersAlive = getPlayersAlive(players);
        if (playersAlive == 3) {
            randomPlayerIndex = isMultiplayer ? new Random().nextInt(players.size()) : 0;
        } else if (playersAlive == 2) {
            randomPlayerIndex = 1;
        }
        return randomPlayerIndex;
    }

    private boolean bossSpecialAttack(Boss enemy, ArrayList<Player> players, boolean isMultiplayer) {
        if (enemy.getSpecialAttackUses() > 0) {
            int randomPlayerIndex = getRandomPlayerIndex(players, isMultiplayer);
            int damage = enemy.specialAttack(players, randomPlayerIndex);
            enemy.setBadGuySpecialAttackUses(enemy.getSpecialAttackUses() - 1);
            GameUI.displayEnemySpecialAttack(enemy);
            GameUI.displayPlayerHit(randomPlayerIndex + 1, damage, players.get(randomPlayerIndex));
            return true;
        }
        return false;
    }

    /**
     * Gets the state of the players' lives
     *
     * @return 0 if both are dead 1 if player one is alive 2 if player 2 is alive and 3 if both are alive
     */
    private int getPlayersAlive(ArrayList<Player> players) {
        if (!isMultiplayer) {
            if (!players.get(0).isDead()) {
                return 1;
            } else {
                return 0;
            }
        }
        if (!players.get(0).isDead() && !players.get(1).isDead()) {
            return 3;
        }
        if (players.get(0).isDead() && !players.get(1).isDead()) {
            return 2;
        }
        if (!players.get(0).isDead() && players.get(1).isDead()) {
            return 1;
        }
        return 0;
    }

    /**
     * Takes the enemies turn in a multiplayer game
     *
     * @param players the players that are in battle
     * @param enemies the enemies that are in battle
     * @return true if all players are dead
     */
    private boolean enemyTurn(ArrayList<Player> players, ArrayList<Lackie> enemies) {
        GameUI.displayEnemyTurn();
        for (Lackie enemy :
                enemies) {
            if (!enemy.isDead()) {
                if (enemy instanceof Boss) {
                    bossTurn((Boss) enemy, players, true, enemies);
                } else {
                    enemyAttack(enemy, players, isMultiplayer);
                }
            }
        }
        return false;
    }

    private void playerTurn(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        GameUI.displayPlayerTurn(player);
        int selection;
        boolean isPlayerTurn = true;
        do {
            do {
                selection = GameUI.getAttackOption(player);
            } while (selection <= 0 || selection >= 6);
            switch (selection) {
                case 1:
                    //attack
                    playerAttack(player, enemies);
                    isPlayerTurn = false;
                    break;
                case 2:
                    //magic attack
                    isPlayerTurn = magicAttack(player, enemies, players);
                    break;
                case 3:
                    //special attack
                    isPlayerTurn = specialAttack(player, enemies);
                    break;
                case 4:
                    //Item menu
                    isPlayerTurn = useItem(player, enemies, players);
                    break;
                case 5:
                    //Open inventory
                    isPlayerTurn = openInventory(player, enemies, players);
                    break;
            }
        } while (isPlayerTurn);
        GameUI.displayTurnOver();
    }

    private boolean specialAttack(Player player, ArrayList<Lackie> enemies) {
        boolean confirmed = GameUI.getSpecialConfirmation();
        if (!confirmed) {
            return true;
        }
        int damage = player.getSelectedWeapon().specialAttack();
        Lackie enemy;
        do {
            try {
                int selection = GameUI.getEnemyBeingAttacked(enemies);
                if (selection == enemies.size() + 1) {
                    return true;
                }
                enemy = enemies.get(selection - 1);
                enemy.setBadGuyHealth(enemy.getBadGuyHealth() - damage);
                break;
            } catch (EnemyIsDeadException e) {
                GameUI.deadEnemySelected();
            }
        } while (true);
        player.setAvailableSpecialAttacks(player.getAvailableSpecialAttacks() - 1);
        GameUI.displayPlayerSpecialAttack(player, damage, enemy);
        return false;
    }

    private boolean magicAttack(Player currentPlayer, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        do {
            GameUI.displayMagicMenu();
            int selection = GameUI.getSpellBeingUsed(currentPlayer);
            if (currentPlayer.getSpells().size() == 0) {
                return true;
            }
            if (selection == currentPlayer.getSpells().size() + 1) {
                return true;
            }
            Spell selectedSpell = currentPlayer.getSpells().get(selection - 1);
            if (selectedSpell.magicPoint() > currentPlayer.getMagic()) {
                Console.writeLn("You don't have enough MP to do this spell!", Console.TextColor.RED);
            } else {
                try {
                    boolean targetEnemy = GameUI.isTargetEnemy();
                    if (targetEnemy) {
                        do {
                            try {
                                int selectedEnemy = GameUI.getEnemyBeingAttacked(enemies);
                                if (selectedEnemy == enemies.size() + 1) {
                                    return true;
                                }
                                Lackie enemy = enemies.get(selectedEnemy - 1);
                                try {
                                    selectedSpell.useOnEnemy(enemy);
                                } catch (EnemyIsRevivedException e) {
                                    GameUI.displayEnemyIsRevived(e.getMessage());
                                }
                                GameUI.displaySpellOnEnemy(selectedSpell, enemy);
                                break;
                            } catch (EnemyIsDeadException e) {
                                GameUI.deadEnemySelected();
                            }
                        } while (true);
                    } else {
                        int selectedPlayer = GameUI.getSelectedPlayer(players);
                        if (selectedPlayer == players.size() + 1) {
                            return true;
                        }
                        Player player = players.get(selectedPlayer - 1);
                        selectedSpell.useOnPlayer(player);
                        GameUI.displaySpellOnPlayer(selectedSpell, player);
                    }
                    currentPlayer.setMagic(currentPlayer.getMagic() - selectedSpell.magicPoint());
                    GameUI.displayPlayerMP(currentPlayer);
                    return false;
                } catch (IllegalArgumentException e) {
                    GameUI.spellCantBeUsed();
                    return true;
                }
            }
        } while (true);
    }

    public static boolean openInventory(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        int response = GameUI.displayPlayerInventory(player);
        switch (response) {
            case 1:
                //select weapon
                return selectWeapon(player);
            case 2:
                //use item
                return useItem(player, enemies, players);
            case 3:
                //exit
                break;
        }
        return true;
    }

    private static boolean useItem(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        int selection = GameUI.getItemBeingUsed(player);
        if (selection == 1 && player.getItems().size() == 0) {
            return true;
        }
        if (selection == player.getItems().size() + 1) {
            return true;
        } else {
            if (enemies != null) {
                boolean targetEnemy = GameUI.isTargetEnemy();
                if (targetEnemy) {
                    try {
                        do {
                            try {
                                int selectedEnemyNo = GameUI.getEnemyBeingAttacked(enemies);
                                if (selectedEnemyNo == enemies.size() + 1) {
                                    return true;
                                }
                                Item selectedItem = player.getItems().get(selection - 1);
                                Lackie selectedEnemy = enemies.get(selectedEnemyNo - 1);
                                try {
                                    selectedItem.useOnEnemy(selectedEnemy);
                                } catch (EnemyIsRevivedException e) {
                                    GameUI.displayEnemyIsRevived(e.getMessage());
                                }
                                GameUI.displayItemUsedOnEnemy(selectedItem, selectedEnemy);
                                player.getItems().remove(selection - 1);
                                return false;
                            } catch (EnemyIsDeadException e) {
                                GameUI.deadEnemySelected();
                            }
                        } while (true);
                    } catch (IllegalArgumentException e) {
                        GameUI.itemCantBeUsed();
                        return true;
                    }
                }
            }
            int selectedPlayerNo = GameUI.getSelectedPlayer(players);
            if (selectedPlayerNo == players.size() + 1) {
                return true;
            }
            Item selectedItem = player.getItems().get(selection - 1);
            Player selectedPlayer = players.get(selectedPlayerNo - 1);
            selectedItem.useOnPLayer(selectedPlayer);
            GameUI.displayItemUsedOnPlayer(selectedItem, selectedPlayer);
            player.getItems().remove(selection - 1);
            return false;
        }
    }

    private static boolean selectWeapon(Player player) {
        do {
            try {
                int selection = GameUI.getWeaponBeingSwitchedTo(player);
                if (selection == player.getWeapons().size() + 1) {
                    return true;
                } else {
                    Weapon newWeapon = player.getWeapons().get(selection - 1);
                    if (player.getSelectedWeapon() == newWeapon) {
                        throw new WeaponAlreadySelectedException("The weapon has already been selected");
                    }
                    player.setSelectedWeapon(newWeapon);
                    GameUI.displayNewSelectedWeapon(newWeapon);
                    //TODO: you can make it so the UI shows the new selected weapon
                    return false;
                }
            } catch (WeaponAlreadySelectedException e) {
                GameUI.weaponAlreadySelected();
            }
        } while (true);
    }

    private void playerAttack(Player player, ArrayList<Lackie> enemies) {
        int attackDMG = player.attack();
        Lackie currentEnemy;
        for (int i = 0; i < player.getAmountOfAttacks(); i++) {
            do {
                try {
                    GameUI.displayAttackScreen();
                    GameUI.displayAttacksLeft(player.getAmountOfAttacks() - i);
                    int response = GameUI.getEnemyBeingAttacked(enemies);
                    if (response == enemies.size() + 1) {
                        return;
                    }
                    currentEnemy = enemies.get(response - 1);
                    currentEnemy.setBadGuyHealth(currentEnemy.getBadGuyHealth() - attackDMG);
                    break;
                } catch (EnemyIsDeadException e) {
                    GameUI.deadEnemySelected();
                }
            } while (true);
            GameUI.displayEnemyHit(currentEnemy, attackDMG);
            if (isAllEnemiesDead(enemies)) {
                return;
            }
        }
    }

    private void battleWon(ArrayList<Lackie> enemies, ArrayList<Player> players) {
        int amountOfGold = 0;
        for (Lackie enemy :
                enemies) {
            amountOfGold += enemy.dropGold();
        }
        if (isMultiplayer()) {
            players.get(0).setGold(players.get(0).getGold() + amountOfGold / 2);
            players.get(1).setGold(players.get(1).getGold() + amountOfGold / 2);
        } else {
            players.get(0).setGold(players.get(0).getGold() + amountOfGold);
        }
        GameUI.displayWin(amountOfGold, isMultiplayer());
        GameUI.displayGold(players, isMultiplayer());
    }

    private boolean isAllEnemiesDead(ArrayList<Lackie> enemies) {
        for (Lackie enemy :
                enemies) {
            if (!enemy.isDead()) {
                return false;
            }
        }
        return true;
    }
    //endregion
}
