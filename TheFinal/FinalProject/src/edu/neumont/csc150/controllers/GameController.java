/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:07 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Map;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;

public class GameController {
    private BattleController battleController;
    private ArrayList<Player> players;

    public void run() {
        do {
            battleController = new BattleController();
            int selection = GameUI.displayMainMenu();
            switch (selection) {
                case 1:
                    //start single player
                    battleController.setMultiplayer(false);
                    setupGame(false);
                    startGame();
                    break;
                case 2:
                    //start multiplayer
                    battleController.setMultiplayer(true);
                    setupGame(true);
                    startGame();
                    break;
                case 3:
                    //exit
                    return;
                default:
                    System.out.println("Enter a valid input!");
                    break;
            }
        } while (true);
    }

    private void startGame() {
        floorOne();
    }

    private void floorOne() {
        boolean floorNotCompleted = true;
        Map currentMap = new Map(1);
        do {
            int movementOption = GameUI.getMovementOptions(currentMap);
            currentMap.moveParty(movementOption);
            if (battleController.checkForBattle(currentMap.getCurrentFloor(), currentMap.getCurrentPos())) {
                if (battleController.battle(currentMap.getCurrentFloor(), currentMap.getCurrentPos(), players)) {
                    battleController.setBattleOneOneDone(true);
                } else {
                    gameOver();
                    return;
                }
            }
        } while (floorNotCompleted);
    }

    private void gameOver() {
        GameUI.displayGameOver();
    }

    private void setupGame(boolean isMultiplayer) {
        players = new ArrayList<>();
        if (isMultiplayer) {
            players.add(new Player());
        }
        players.add(new Player());
    }
}
