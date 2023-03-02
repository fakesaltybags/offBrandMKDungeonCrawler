/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:40 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.npc.commonenemy.Lackies;
import edu.neumont.csc150.models.npc.secretbosses.SecretBoss;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;

public class BattleController {
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
        ArrayList<Lackies> enemies = new ArrayList<Lackies>();
        commenceBattle(players, enemies);
    }

    private void commenceBattle(ArrayList<Player> players, ArrayList<Lackies> enemies) {
        boolean inBattle = true;
        String foundEnemies = "";
        for (Lackies enemy :
                enemies) {
            if(enemy instanceof SecretBoss){
                foundEnemies += enemy.getName() + " now prove yourself!!";
            } else{
                foundEnemies += " and a " + enemy.getName();
            }
        }
        System.out.println("You have found " + foundEnemies);
        do {
            boolean enemyGoFirst;
            if (players.get(1) == null) {
                Player player = players.get(0);
                enemyGoFirst = enemies.get(0).badGuySpeed() > player.getSpeed();
                if(enemyGoFirst){
                    for (Lackies enemy :
                            enemies) {
                        int attackDMG = enemy.badGuyAttack();
                        player.setHealth(player.getHealth() - attackDMG);
                        System.out.println(enemy.getName() + " did " + attackDMG + "DMG");
                    }
                    playerTurn(player);
                }
            } else {
                //TODO: make the logic for more than one player
            }
        } while (inBattle);
    }

    private void playerTurn(Player player, ArrayList<Lackies> enemies) {
        int selection;
        do {
            selection = GameUI.getAttackOption(player);
        } while (selection <= 0 || selection >= 6);
        switch(selection){
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
                //Open inventory
                break;
                //TODO: finish this switch with methods
        }
    }

    private void playerAttack(Player player, ArrayList<Lackies> enemies) {
        int attackDMG = player.attack();
        do {
            int response = GameUI.getEnemyBeingAttacked(enemies);
        }
    }
}
