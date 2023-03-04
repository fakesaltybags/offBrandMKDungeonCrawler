/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:40 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.exceptions.EnemyIsDeadException;
import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.npc.commonenemy.Zombie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.models.spells.Spell;
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
        GameUI.displayEnemies(enemies);
        boolean enemyGoFirst;
        Player player = players.get(0);
        enemyGoFirst = enemies.get(0).getBadGuySpeed() > player.getSpeed();
        GameUI.displayFirstTurn(enemyGoFirst);
        if (!isMultiplayer) {
            if (enemyGoFirst) {
                do {
                    for (Lackie enemy :
                            enemies) {
                        int attackDMG = enemy.badGuyAttack();
                        player.setHealth(player.getHealth() - attackDMG);
                        GameUI.displayPlayerHit(1, attackDMG, player);
                        if (player.isDead()) {
                            return false;
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
                    for (Lackie enemy :
                            enemies) {
                        int attackDMG = enemy.badGuyAttack();
                        player.setHealth(player.getHealth() - attackDMG);
                        GameUI.displayPlayerHit(1, attackDMG, player);
                        if (player.isDead()) {
                            return false;
                        }
                    }
                } while (true);
            }
        } else {
            if(enemyGoFirst){
                do{
                    //enemy turn
                    if (enemyTurn(players, enemies)) return false;
                    //players turn
                    if(getPlayersAlive(players) == 3){
                        player = players.get(0);
                        playerTurn(player, enemies, players);
                        if(isAllEnemiesDead(enemies)){
                            battleWon(enemies, players);
                            return true;
                        }
                        player = players.get(1);
                    } else if(getPlayersAlive(players) == 1){
                        player = players.get(0);
                    } else if(getPlayersAlive(players) == 2){
                        player = players.get(1);
                    } else {
                        return false;
                    }
                    playerTurn(player, enemies, players);
                    if(isAllEnemiesDead(enemies)){
                        battleWon(enemies, players);
                        return true;
                    }
                }while(true);
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
                }while(true);
            }
        }
    }

    /**
     * Gets the state of the players' lives
     * @return 0 if both are dead 1 if player one is alive 2 if player 2 is alive and 3 if both are alive
     */
    private int getPlayersAlive(ArrayList<Player> players){
        if(!players.get(0).isDead() && !players.get(1).isDead()){
            return 3;
        }
        if(players.get(0).isDead() &&!players.get(1).isDead()){
            return 2;
        }
        if(!players.get(0).isDead() && players.get(1).isDead()){
            return 1;
        }
        return 0;
    }

    /**
     * Takes the enemies turn in a multiplayer game
     * @param players the players that are in battle
     * @param enemies the enemies that are in battle
     * @return true if all players are dead
     */
    private boolean enemyTurn(ArrayList<Player> players, ArrayList<Lackie> enemies) {
        for (Lackie enemy :
                enemies) {
            int attackDMG = enemy.badGuyAttack();
            int playerNo;
            if (!players.get(0).isDead() && !players.get(1).isDead()) {
                playerNo = new Random().nextInt(2);
            } else if (!players.get(0).isDead() && players.get(1).isDead()) {
                playerNo = 0;
            } else if (players.get(0).isDead() && !players.get(1).isDead()) {
                playerNo = 1;
            } else {
                return true;
            }
            players.get(playerNo).setHealth(players.get(playerNo).getHealth() - attackDMG);
            GameUI.displayPlayerHit(playerNo + 1, attackDMG, players.get(playerNo));
        }
        return false;
    }

    private void playerTurn(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
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
        if(!confirmed){
            return true;
        }
        int damage = player.getSelectedWeapon().specialAttack();
        Lackie enemy;
        do {
            try {
                int selection = GameUI.getEnemyBeingAttacked(enemies);
                enemy = enemies.get(selection - 1);
                enemy.setBadGuyHealth(enemy.getBadGuyHealth() - damage);
                break;
            } catch (EnemyIsDeadException e) {
                GameUI.deadEnemySelected();
            }
        }while(true);
        player.setAvailableSpecialAttacks(player.getAvailableSpecialAttacks() - 1);
        GameUI.displaySpecialAttack(player, damage, enemy);
        return false;
    }

    private boolean magicAttack(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        do {
            int selection = GameUI.getSpellBeingUsed(player);
            if(player.getSpells().size() == 0){
                return true;
            }
            Spell selectedSpell = player.getSpells().get(selection - 1);
            if (selection == player.getSpells().size()) {
                return true;
            } else if (selectedSpell.magicPoint() > player.getMagic()) {
                Console.writeLn("You don't have enough MP to do this spell!", Console.TextColor.RED);
            } else {
                try {
                    boolean targetEnemy = GameUI.isTargetEnemy();
                    if (targetEnemy) {
                        do {
                            try {
                                int selectedEnemy = GameUI.getEnemyBeingAttacked(enemies);
                                selectedSpell.useOnEnemy(enemies.get(selectedEnemy - 1));
                                break;
                            }catch(EnemyIsDeadException e){
                                GameUI.deadEnemySelected();
                            }
                        }while(true);
                        //TODO: if you have time you can make a personalized message for each type of spell and the amount of damage it does in the UI
                    } else {
                        int selectedPlayer = GameUI.getSelectedPlayer(players);
                        selectedSpell.useOnPlayer(players.get(selectedPlayer - 1));
                        //TODO: if you have time you can make a personalized message for each type of spell and the amount of damage it does in the UI
                    }
                    player.setMagic(player.getMagic() - selectedSpell.magicPoint());
                    return false;
                } catch (IllegalArgumentException e) {
                    GameUI.spellCantBeUsed();
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
        if(selection == 1 && player.getItems().size() == 0){
            return true;
        }
        if (selection == player.getItems().size() + 1) {
            return true;
        } else {
            boolean targetEnemy = GameUI.isTargetEnemy();
            if (targetEnemy) {
                do {
                    try {
                        int selectedEnemy = GameUI.getEnemyBeingAttacked(enemies);
                        player.getItems().get(selection - 1).useOnEnemy(enemies.get(selectedEnemy - 1));
                        break;
                    }catch(EnemyIsDeadException e){
                        GameUI.deadEnemySelected();
                    }
                }while(true);
            } else {
                int selectedPlayer = GameUI.getSelectedPlayer(players);
                player.getItems().get(selection - 1).useOnPLayer(players.get(selectedPlayer - 1));
            }
            player.getItems().remove(selection - 1);
            return false;
            //TODO: if you want you can make it so that the UI shows that the player used the item and it's effect on the battle
        }
    }

    private boolean selectWeapon(Player player) {
        int selection = GameUI.getWeaponBeingSwitchedTo(player);
        if (selection == player.getWeapons().size() + 1) {
            return true;
        } else {
            player.setSelectedWeapon(player.getWeapons().get(selection - 1));
            //TODO: you can make it so the UI shows the new selected weapon
            return false;
        }
    }

    private void playerAttack(Player player, ArrayList<Lackie> enemies) {
        int attackDMG = player.attack();
        Lackie currentEnemy;
        for (int i = 0; i < player.getAmountOfAttacks() - 1; i++) {
            do {
                try {
                    int response = GameUI.getEnemyBeingAttacked(enemies);
                    currentEnemy = enemies.get(response - 1);
                    currentEnemy.setBadGuyHealth(currentEnemy.getBadGuyHealth() - attackDMG);
                    break;
                }catch(EnemyIsDeadException e){
                    GameUI.deadEnemySelected();
                }
            }while(true);
            GameUI.displayEnemyHit(currentEnemy, attackDMG);
            if(isAllEnemiesDead(enemies)){
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
        if(isMultiplayer()){
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
