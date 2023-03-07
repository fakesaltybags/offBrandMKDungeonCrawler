/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:07 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.controllers;
 */
package edu.neumont.csc150.controllers;

import edu.neumont.csc150.exceptions.NotEnoughMoneyException;
import edu.neumont.csc150.exceptions.PlayerHasSpellException;
import edu.neumont.csc150.models.Map;
import edu.neumont.csc150.models.Shop;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.views.Console;
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
        startFloor(1);
        startFloor(2);
        startFloor(3);
        //TODO: etc^
    }

    private void startFloor(int givenFloor) {
        Map currentMap = new Map(givenFloor);
        int currentFloor = currentMap.getCurrentFloor();
        moveAndBattle(currentMap, currentFloor);
        levelUp(currentFloor);
        openShop(currentFloor, players);
    }

    private void levelUp(int currentFloor) {
        for (Player player :
                players) {
            boolean isPlayer1;
            isPlayer1 = player == players.get(0);
            boolean levelMagicMore = GameUI.getLevelMagicMore(player, isPlayer1);
            int magicUpAmount = 10;
            int healthUpAmount = 10;
            switch(currentFloor){
                case 1:
                    if(levelMagicMore){
                        healthUpAmount = 5;
                    } else {
                        magicUpAmount = 5;
                    }
                    break;
                case 2:
                    if(levelMagicMore){
                        magicUpAmount = 20;
                        healthUpAmount = 15;
                    } else {
                        magicUpAmount = 15;
                        healthUpAmount = 20;
                    }
                    break;
                    //TODO: finish this with the newer floors
            }
            player.setMaxMagic(player.getMaxMagic() + magicUpAmount);
            player.setMaxHP(player.getMaxHP() +healthUpAmount);
            GameUI.displayLevelUp(player, isPlayer1);
        }
    }

    private void openShop(int currentFloor, ArrayList<Player> players) {
        for (Player player :
                players) {
            boolean player1;
            if (player == players.get(0)) {
                Console.writeLn("---- Player 1 enters the shop ----", Console.TextColor.YELLOW);
                player1 = true;
            } else {
                Console.writeLn("---- Player 2 enters the shop ----", Console.TextColor.YELLOW);
                player1 = false;
            }
            boolean inShop = true;
            boolean displayedShop = false;
            do {
                Shop shop = new Shop(currentFloor);
                if(!displayedShop) {
                    GameUI.displayShop(shop);
                    displayedShop = true;
                }
                int selection = GameUI.getShopSelection();
                switch (selection) {
                    case 1:
                        //buy spell
                        int spellNo = GameUI.displayShopSpells(shop);
                        try {
                            shop.buySpell(player, spellNo);
                        }catch(NotEnoughMoneyException e){
                            GameUI.displayNotEnoughMoney();
                            break;
                        }catch(PlayerHasSpellException e){
                            GameUI.displaySpellException();
                            break;
                        }
                        break;
                    case 2:
                        //buy item
                        int itemNo = GameUI.displayShopItems(shop);
                        try {
                            shop.buyItem(player, itemNo);
                        }catch(NotEnoughMoneyException e){
                            GameUI.displayNotEnoughMoney();
                            break;
                        }
                        break;
                    case 3:
                        //leave
                        inShop = false;
                        break;
                }
            } while (inShop);
            if(player1){
                Console.writeLn("---- Player 1 leaves the shop ----", Console.TextColor.YELLOW);
            } else {
                Console.writeLn("---- Player 2 leaves the shop ----", Console.TextColor.YELLOW);
            }
        }
        Console.writeLn("The party leaves the shop behind, ready for the next floor", Console.TextColor.GREEN);
    }

    private void moveAndBattle(Map currentMap, int currentFloor) {
        do {
            boolean validOption;
            do {
                int movementOption = GameUI.getMovementOptions(currentMap);
                validOption = currentMap.moveParty(movementOption, players);
                if(!validOption){
                    GameUI.displayInvalidMovement();
                }
            }while(!validOption);
            if(currentFloor < currentMap.getCurrentFloor()){
                break;
            }
            if (battleController.checkForBattle(currentMap.getCurrentFloor(), currentMap.getCurrentPos())) {
                if (battleController.battle(currentMap.getCurrentFloor(), currentMap.getCurrentPos(), players)) {
                    battleController.setBattleOneOneDone(true);
                } else {
                    gameOver();
                    return;
                }
            }
            battleController.checkForChest(currentMap.getCurrentFloor(), currentMap.getCurrentPos(), players);
        } while (true);
    }

    private void gameOver() {
        GameUI.displayGameOver();
    }

    private void setupGame(boolean isMultiplayer) {
        players = new ArrayList<>();
        if (isMultiplayer) {
            players.add(new Player(true));
        }
        players.add(new Player(isMultiplayer));
    }
}
