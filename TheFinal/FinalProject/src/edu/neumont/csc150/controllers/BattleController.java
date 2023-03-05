/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:40 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.exceptions.WeaponAlreadySelectedException;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.npc.bosses.Boss;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.npc.commonenemy.Zombie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.Spell;
import edu.neumont.csc150.models.weapons.Weapon;
import edu.neumont.csc150.views.Console;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;
import java.util.Random;

public class BattleController {
    private boolean battleOneOneDone;
    private boolean isMultiplayer = true;

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

    //endregion

    public boolean checkForBattle(int currentFloor, int currentPos) {
        switch (currentFloor) {
            case 1:
                return checkForBattleFloorOne(currentPos);
            default:
                return false;
        }
    }

    private boolean checkForBattleFloorOne(int currentPos) {
        switch (currentPos) {
            case 1:
                return !isBattleOneOneDone();
            default:
                return false;
        }
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
                return battleFloorOnePosOne(players);
            default:
                return false;
        }
    }

    private boolean battleFloorOnePosOne(ArrayList<Player> players) {
        ArrayList<Lackie> enemies = new ArrayList<>();
        enemies.add(new Zombie());
        enemies.add(new Zombie());
        return commenceBattle(players, enemies);
    }

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
                        if(!enemy.isDead()) {
                            int attackDMG = enemy.badGuyAttack();
                            if (enemy instanceof Boss) {
                                bossTurn((Boss) enemy, players, false);
                            } else {
                                player.setHealth(player.getHealth() - attackDMG);
                                GameUI.displayPlayerHit(1, attackDMG, player);
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
                        if(!enemy.isDead()) {
                            int attackDMG = enemy.badGuyAttack();
                            if (enemy instanceof Boss) {
                                bossTurn((Boss) enemy, players, false);
                            } else {
                                player.setHealth(player.getHealth() - attackDMG);
                                GameUI.displayPlayerHit(1, attackDMG, player);
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
                    if(!player.isDead()) {
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

    private void bossTurn(Boss enemy, ArrayList<Player> players, boolean isMultiplayer) {
        do {
            int aiChoice = new Random().nextInt(4) + 1;
            switch (aiChoice) {
                case 1:
                    //special attack
                    if (bossSpecialAttack(enemy, players, isMultiplayer)) {
                        return;
                    }
                    break;
                case 2:
                    //spell
                    if (bossSpell(enemy, players, isMultiplayer)) {
                        return;
                    }
                    break;
                case 3:
                    //useItem
                    if (bossUseItem(enemy, players, isMultiplayer)) {
                        return;
                    }
                    break;
                default:
                    //attack
                    int playerNo = 0;
                    if (isMultiplayer) {
                        playerNo = new Random().nextInt(2);
                    }
                    Player selectedPlayer = players.get(playerNo);
                    selectedPlayer.setHealth(selectedPlayer.getHealth() - enemy.badGuyAttack());
                    GameUI.displayPlayerHit(playerNo + 1, enemy.badGuyAttack(), selectedPlayer);
                    return;
            }
        } while (true);
    }

    private boolean bossUseItem(Boss enemy, ArrayList<Player> players, boolean isMultiplayer) {
        return enemy.badGuyItem(players, isMultiplayer);
    }

    private boolean bossSpell(Boss enemy, ArrayList<Player> players, boolean isMultiplayer) {
        enemy.badGuySpell(players, isMultiplayer);
        return true;
    }

    private boolean bossSpecialAttack(Boss enemy, ArrayList<Player> players, boolean isMultiplayer) {
        if (enemy.getSpecialAttackUses() > 0) {
            enemy.specialAttack(players, isMultiplayer);
            enemy.setBadGuySpecialAttackUses(enemy.getSpecialAttackUses() - 1);
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
            if(!enemy.isDead()) {
                int attackDMG = enemy.badGuyAttack();
                int playerNo;
                if (getPlayersAlive(players) == 3) {
                    playerNo = new Random().nextInt(2);
                } else if (getPlayersAlive(players) == 2) {
                    playerNo = 1;
                } else if (getPlayersAlive(players) == 1) {
                    playerNo = 0;
                } else {
                    return true;
                }
                if (enemy instanceof Boss) {
                    bossTurn((Boss) enemy, players, true);
                } else {
                    players.get(playerNo).setHealth(players.get(playerNo).getHealth() - attackDMG);
                    GameUI.displayPlayerHit(playerNo + 1, attackDMG, players.get(playerNo));
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
                if(selection == enemies.size() + 1){
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
        GameUI.displaySpecialAttack(player, damage, enemy);
        return false;
    }

    private boolean magicAttack(Player currentPlayer, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        do {
            GameUI.displayMagicMenu();
            int selection = GameUI.getSpellBeingUsed(currentPlayer);
            if (currentPlayer.getSpells().size() == 0) {
                return true;
            }
            if(selection == currentPlayer.getSpells().size() + 1){
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
                                if(selectedEnemy == enemies.size() + 1){
                                    return true;
                                }
                                Lackie enemy = enemies.get(selectedEnemy - 1);
                                selectedSpell.useOnEnemy(enemy);
                                GameUI.displaySpellOnEnemy(selectedSpell, enemy);
                                break;
                            } catch (EnemyIsDeadException e) {
                                GameUI.deadEnemySelected();
                            }
                        } while (true);
                    } else {
                        int selectedPlayer = GameUI.getSelectedPlayer(players);
                        if(selectedPlayer == players.size() + 1){
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

    private boolean openInventory(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
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

    private boolean useItem(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        int selection = GameUI.getItemBeingUsed(player);
        if (selection == 1 && player.getItems().size() == 0) {
            return true;
        }
        if (selection == player.getItems().size() + 1) {
            return true;
        } else {
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
                            selectedItem.useOnEnemy(selectedEnemy);
                            GameUI.displayItemUsedOnEnemy(selectedItem, selectedEnemy);
                            //TODO: if you want display what happened in the UI with more detail
                            break;
                        } catch (EnemyIsDeadException e) {
                            GameUI.deadEnemySelected();
                        }
                    } while (true);
                }catch(IllegalArgumentException e){
                    GameUI.itemCantBeUsed();
                    return true;
                }
            } else {
                int selectedPlayerNo = GameUI.getSelectedPlayer(players);
                if(selectedPlayerNo == players.size() + 1){
                    return true;
                }
                Item selectedItem = player.getItems().get(selection - 1);
                Player selectedPlayer = players.get(selectedPlayerNo - 1);
                selectedItem.useOnPLayer(selectedPlayer);
                GameUI.displayItemUsedOnPlayer(selectedItem, selectedPlayer);
                //TODO: same as above ^^^
            }
            player.getItems().remove(selection - 1);
            return false;
        }
    }

    private boolean selectWeapon(Player player) {
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
            }catch(WeaponAlreadySelectedException e){
                GameUI.weaponAlreadySelected();
            }
        }while(true);
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
                    if (response == enemies.size() + 1){
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
}
