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
    private BattleController battleController = new BattleController();
    private ArrayList<Player> players;
    private Player currentPLayer;
    private int difficulty;
    private int range;
    private boolean isMultiplayer = false;



    public void run(){
        do {
            int selection = GameUI.displayMainMenu();
            switch (selection) {
                case 1:
                    //start single player
                    isMultiplayer = false;
                    battleController.setMultiplayer(isMultiplayer);
                    setupGame(isMultiplayer);
                    startGame();
                    break;
                case 2:
                    isMultiplayer = true;
                    setupGame(isMultiplayer);
                    //start multiplayer
                    startGame();
                    break;
                case 3:
                    //exit
                    return;
                default:
                    System.out.println("Enter a valid input!");
                    break;
            }
        }while(true);
    }

    private void startGame() {
        boolean floorNotCompleted = true;
        Map currentMap = new Map(1);
        do {
            int movementOption = GameUI.getMovementOptions(currentMap);
            currentMap.moveParty(movementOption);
            if(battleController.checkForBattle(currentMap.getCurrentFloor(), currentMap.getCurrentPos())){
                if(battleController.battle(currentMap.getCurrentFloor(), currentMap.getCurrentPos(), players)){

                } else{
                    gameOver();
                    return;
                }
            }
        }while (floorNotCompleted);
    }

    private void gameOver() {
        GameUI.displayGameOver();
    }

    private void setupGame(boolean isMultiplayer) {
        if(isMultiplayer){
            players = new ArrayList<>();
            players.add(new Player());
            players.add(new Player());
            currentPLayer = players.get(0);
        } else {
            players = new ArrayList<>();
            players.add(new Player());
            currentPLayer = new Player();
        }
        difficulty = 1;
    }
}
