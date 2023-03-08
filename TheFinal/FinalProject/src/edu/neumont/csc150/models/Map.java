/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:49 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models;

import edu.neumont.csc150.controllers.BattleController;
import edu.neumont.csc150.models.players.Player;
import edu.neumont.csc150.views.GameUI;

import java.util.ArrayList;

public class Map {
    //region variables
    private int currentPos;
    private int currentFloor;
    private static boolean canGoToNextFloor = false;
    //endregion

    public Map(int currentFloor) {
        setCurrentFloor(currentFloor);
        setCurrentPos(0);
    }

    //region get/set
    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        if (currentPos < -1 || currentPos > 100) {
            throw new IllegalArgumentException("Current position needs to be between -1 and 100");
        }
        this.currentPos = currentPos;
    }

    public static boolean canGoToNextFloor() {
        return canGoToNextFloor;
    }

    public static void setCanGoToNextFloor(boolean nextFloor) {
        canGoToNextFloor = nextFloor;
    }

    /**
     * Gets the current ways the party can move
     * index 0 represents the ability to move left
     * index 1 represents the ability to move right
     * index 2 represents the ability to move forward
     *
     * @return index 0 is the ability to move left
     * index 1 is the ability to move right
     * index 2 is the ability to move forward
     */
    public boolean[] getMoveability() {
        boolean[] currentMoveability = new boolean[3];
        if (currentFloor == 1) {
            switch (currentPos) {
                case 0, 4:
                    currentMoveability[2] = true;
                    break;
                case 1, 2, 9:
                    currentMoveability[0] = true;
                    currentMoveability[1] = true;
                    break;
                case 3:
                    currentMoveability[2] = true;
                    currentMoveability[0] = true;
                    break;
                case 5, 6, 7:
                    currentMoveability[0] = true;
                    break;
                case 8:
                    currentMoveability[1] = true;
                    currentMoveability[2] = true;
                    break;
                case 10, 11:
                    break;
                default:
                    throw new IllegalStateException("Player is in a position that isn't on the map");
            }
        } else if(currentFloor == 2){
            switch (currentPos){
                case 0:
                    currentMoveability[0] = true;
                    currentMoveability[1] = true;
                    currentMoveability[2] = true;
                    break;
                case 1, 2:
                    break;
                case 3, 8:
                   currentMoveability[0] = true;
                   currentMoveability[1] = true;
                   break;
                case 4, 7:
                    currentMoveability[1] = true;
                    break;
                case 5, 6:
                    currentMoveability[0] = true;
                    break;
                default:
                    throw new IllegalStateException("Player is in a position that isn't on the map");
            }
        }
        return currentMoveability;
    }
    //endregion

    public boolean moveParty(int movementOption, ArrayList<Player> players) {
        //1. left
        //2. right
        //3. straight
        //4. turn back
        //5. next floor
        //6. Open Inventory
        if (movementOption == 6) {
            int selectedPlayer = GameUI.getSelectedPlayer(players);
            if(selectedPlayer == players.size() + 1){
                return true;
            }
            BattleController.openInventory(players.get(selectedPlayer - 1), null, players);
            return true;
        }
        if(movementOption == 5){
            return leaveFloor();
        }
        switch (getCurrentFloor()) {
            case 1:
                return movePartyFloorOne(movementOption);
            case 2:
                return movePartyFloorTwo(movementOption);
            default:
                return false;
        }
    }

    private boolean movePartyFloorTwo(int movementOption) {
        //1. left
        //2. right
        //3. straight
        //4. turn back
        switch(getCurrentPos()){
            case 0:
                switch(movementOption){
                    case 1:
                        setCurrentPos(1);
                        return true;
                    case 2:
                        setCurrentPos(2);
                        return true;
                    case 3:
                        setCurrentPos(3);
                        return true;
                    default:
                        return false;
                }
            case 1, 2:
                if(movementOption == 4){
                    setCurrentPos(0);
                    return true;
                }
                return false;
            case 3:
                switch (movementOption){
                    case 1:
                        setCurrentPos(4);
                        return true;
                    case 2:
                        setCurrentPos(5);
                        return true;
                    default:
                        return false;
                }
            case 4:
                if(movementOption == 2){
                    setCurrentPos(7);
                    return true;
                }
                return false;
            case 5:
                if(movementOption == 1){
                    setCurrentPos(6);
                    return true;
                }
                return false;
            case 6:
                if(movementOption == 1){
                    setCurrentPos(8);
                    return true;
                }
                return false;
            case 7:
                if(movementOption == 2){
                    setCurrentPos(8);
                    return true;
                }
                return false;
            case 8:
                switch (movementOption){
                    case 1:
                        setCurrentPos(7);
                        return true;
                    case 2:
                        setCurrentPos(6);
                        return true;
                    default:
                        return false;
                }
        }
        return false;
    }

    private boolean movePartyFloorOne(int movementOption) {
        //1. left
        //2. right
        //3. straight
        //4. turn back
        switch (getCurrentPos()) {
            case 0 -> {
                if (movementOption == 3) {
                    setCurrentPos(1);
                    return true;
                }
                return false;
            }
            case 1 -> {
                switch (movementOption) {
                    case 1 -> {
                        setCurrentPos(2);
                        return true;
                    }
                    case 2 -> {
                        setCurrentPos(3);
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case 2 -> {
                switch (movementOption) {
                    case 1 -> {
                        setCurrentPos(4);
                        return true;
                    }
                    case 2 -> {
                        setCurrentPos(5);
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case 3 -> {
                switch (movementOption) {
                    case 3 -> {
                        setCurrentPos(10);
                        return true;
                    }
                    case 1 -> {
                        setCurrentPos(7);
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case 4 -> {
                if (movementOption == 3) {
                    setCurrentPos(5);
                    return true;
                }
                return false;
            }
            case 5 -> {
                if (movementOption == 1) {
                    setCurrentPos(6);
                    return true;
                }
                return false;
            }
            case 6 -> {
                if (movementOption == 1) {
                    setCurrentPos(0);
                    return true;
                }
                return false;
            }
            case 7 -> {
                if (movementOption == 1) {
                    setCurrentPos(9);
                    return true;
                }
                return false;
            }
            case 8 -> {
                switch (movementOption) {
                    case 2 -> {
                        setCurrentPos(9);
                        return true;
                    }
                    case 3 -> {
                        setCurrentPos(11);
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case 9 -> {
                switch (movementOption) {
                    case 1 -> {
                        setCurrentPos(8);
                        return true;
                    }
                    case 2 -> {
                        setCurrentPos(7);
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
            case 10 -> {
                if (movementOption == 4) {
                    setCurrentPos(3);
                    return true;
                }
                return false;
            }
            case 11 -> {
                if (movementOption == 4) {
                    setCurrentPos(8);
                    return true;
                }
                return false;
            }
            default -> throw new IllegalStateException("Player is in a location that isn't on the map");
        }
    }

    private boolean leaveFloor() {
        if (canGoToNextFloor()) {
            setCurrentFloor(getCurrentFloor() + 1);
            setCurrentPos(0);
            setCanGoToNextFloor(false);
            return true;
        } else {
            return false;
        }
    }
}
