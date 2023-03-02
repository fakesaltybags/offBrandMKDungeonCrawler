/**
 * @author Pachi
 * @createdOn 2/27/2023 at 3:08 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.views;
 */
package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Map;
import edu.neumont.csc150.models.npc.commonenemy.Lackies;
import edu.neumont.csc150.models.players.Player;

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
                if(response >= 1 && response <= 2){
                    return response;
                }
            }
            if (partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                //only able to move left and straight
                response = Console.getIntInput("""
                        1. Turn left
                        3. Keep straight
                        """);
                if(response >= 1 && response <= 3 && response != 2){
                    return response;
                }
            }
            if (!partyMoveability[0] && partyMoveability[1] && partyMoveability[2]) {
                //only able to move right and straight
                response = Console.getIntInput("""
                        2. Turn right
                        3. Keep straight
                        """);
                if(response >= 2 && response <= 3){
                    return response;
                }
            }
            if (!partyMoveability[0] && !partyMoveability[1] && partyMoveability[2]) {
                //only able to move forward
                response = Console.getIntInput("3. Keep straight");
                if(response == 3){
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
                if(response == 2){
                    return response;
                }
            }
            if (!partyMoveability[0] && !partyMoveability[1] && !partyMoveability[2]) {
                //dead end TURN AROUND!!
                response = Console.getIntInput("4. Turn back");
                if(response == 4){
                    return response;
                }
            }
        }while (true);
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

    public static int getEnemyBeingAttacked(ArrayList<Lackies> enemies) {
        String listOfEnemies = "";
        for (int i = 0; i < enemies.size() - 1; i++) {
            listOfEnemies += "\n" + (i + 1) + ". " + enemies.get(i).getName();
        }
        Console.getIntInput()
                //TODO: left off here please finish
    }
}
