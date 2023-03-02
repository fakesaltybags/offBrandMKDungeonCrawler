/**
 * @author Pachi
 * @createdOn 2/28/2023 at 2:49 PM
 * @projectName FinalProject
 * @packageName edu.neumont.csc150.models;
 */
package edu.neumont.csc150.models;

public class Map {
    private int currentPos;
    private int branchingPaths;
    private int currentFloor;
    private final int MAX_FLOORS = 1;

    public Map(int currentFloor){
        setCurrentFloor(currentFloor);
        switch(currentFloor){
            case 1:
                setBranchingPaths(3);
                break;
            case 2:
                setBranchingPaths(5);
                break;
            //TODO: add some stuff for the next floors
        }
        setCurrentPos(0);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        if(currentFloor < 1 || currentFloor > MAX_FLOORS){
            throw new IllegalArgumentException("Current floor cant be lower than 1 or higher than MAX_FLOORS");
        }
        this.currentFloor = currentFloor;
    }

    public int getCurrentPos(){
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        //TODO: use branching paths to create a current pos int using binary :bigBrain:
        if(currentPos < - 1 || currentPos > 100){
            throw new IllegalArgumentException("Current position needs to be between -1 and 100");
        }
        this.currentPos = currentPos;
    }

    public int getBranchingPaths() {
        return branchingPaths;
    }

    public void setBranchingPaths(int branchingPaths) {
        this.branchingPaths = branchingPaths;
    }

    /**
     * Gets the current ways the party can move
     * index 0 represents the ability to move left
     * index 1 represents the ability to move right
     * index 2 represents the ability to move forward
     * @return index 0 is the ability to move left
     * index 1 is the ability to move right
     * index 2 is the ability to move forward
     */
    public boolean[] getMoveability(){
        boolean[] currentMoveability = new boolean[3];
        if(currentFloor == 1){
            if(currentPos == 0){
                currentMoveability[2] = true;
                return currentMoveability;
            }
        }
        return currentMoveability;
    }

    public void moveParty(int movementOption) {
        switch (getCurrentFloor()){
            case 1:
                movePartyFloorOne(movementOption);
                break;
        }
    }

    private void movePartyFloorOne(int movementOption) {
        switch(getCurrentPos()){
            case 0:
                setCurrentPos(1);
                break;
        }
    }
}
