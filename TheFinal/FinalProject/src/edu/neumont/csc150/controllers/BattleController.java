/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:40 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.npc.commonenemy.Lackie;
import edu.neumont.csc150.models.npc.commonenemy.Zombie;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;

public class BattleController {
    private boolean isMultiplayer = true;

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

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
                return true;
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
        ArrayList<Lackie> enemies = new ArrayList<Lackie>();
        enemies.add(new Zombie());
        enemies.add(new Zombie());
        enemies.add(new Zombie());
        enemies.add(new Zombie());
        return commenceBattle(players, enemies);
    }

    private boolean commenceBattle(ArrayList<Player> players, ArrayList<Lackie> enemies) {
        boolean inBattle = true;
        GameUI.displayEnemies(enemies);
        do {
            boolean enemyGoFirst;
            if (!isMultiplayer) {
                Player player = players.get(0);
                enemyGoFirst = enemies.get(0).badGuySpeed() > player.getSpeed();
                if (enemyGoFirst) {
                    for (Lackie enemy :
                            enemies) {
                        int attackDMG = enemy.badGuyAttack();
                        player.setHealth(player.getHealth() - attackDMG);
                        System.out.println(enemy.getName() + " did " + attackDMG + "DMG");
                    }
                    playerTurn(player, enemies, players);
                }
            } else {
                //TODO: make the logic for more than one player
            }
        } while (inBattle);
    }

    private void playerTurn(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        int selection;
        do {
            selection = GameUI.getAttackOption(player);
        } while (selection <= 0 || selection >= 6);
        switch (selection) {
            case 1:
                //attack
                playerAttack(player, enemies);
                break;
            case 2:
                //magic attack
                break;
            case 3:
                //special attack
                break;
            case 4:
                //Item menu
                break;
            case 5:
                openInventory(player, enemies, players);
                //Open inventory
                break;
            //TODO: finish this switch with methods
            //TODO: left off here make the magic attack method
        }
    }

    private void openInventory(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        int response = GameUI.displayPlayerInventory(player);
        switch (response) {
            case 1:
                //select weapon
                selectWeapon(player);
                break;
            case 2:
                //use item
                useItem(player, enemies, players);
                break;
            case 3:
                //exit
                break;
        }
    }

    private void useItem(Player player, ArrayList<Lackie> enemies, ArrayList<Player> players) {
        do{
            int selection = GameUI.getItemBeingUsed(player);
            if(selection == player.getItems().size()){
                return;
            } else {
                boolean targetEnemy = GameUI.isTargetEnemy();
                if(targetEnemy){
                    int selectedEnemy = GameUI.getEnemyBeingAttacked(enemies);
                    player.getItems().get(selection - 1).useOnEnemy(enemies.get(selectedEnemy - 1));
                } else {
                    int selectedPlayer = GameUI.getSelectedPlayer(players);
                    player.getItems().get(selection - 1).useOnPLayer(players.get(selectedPlayer - 1));
                }
                player.getItems().remove(selection - 1);
            }
        }while(true);
    }

    private void selectWeapon(Player player) {
        do {
            int selection = GameUI.getWeaponBeingSwitchedTo(player);
            if(selection == player.getWeapons().size()){
                return;
            } else {
                player.setSelectedWeapon(player.getWeapons().get(selection - 1));
            }
        }while(true);
    }

    private void playerAttack(Player player, ArrayList<Lackie> enemies) {
        int attackDMG = player.attack();
        for (int i = 0; i < player.getAmountOfAttacks() - 1; i++) {
            int response = GameUI.getEnemyBeingAttacked(enemies);
            Lackie currentEnemy = enemies.get(response - 1);
            currentEnemy.setBadGuyHealth(currentEnemy.badGuyHealth() - attackDMG);
            GameUI.displayHit(currentEnemy, attackDMG);
            if (isAllEnemiesDead(enemies)) {
                battleWon(enemies);
                return;
            }
        }

    }

    private void battleWon(ArrayList<Lackie> enemies) {
        int amountOfGold = 0;
        for (Lackie enemy :
                enemies) {
            amountOfGold += enemy.dropGold();
        }
        GameUI.displayWin(amountOfGold, isMultiplayer());

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
