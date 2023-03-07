/**
 * @author Pachi
 * @createdOn 3/7/2023 at 2:58 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.exceptions;
 */
package edu.neumont.csc150.exceptions;

public class PlayerHasSpellException extends IllegalStateException{
    public PlayerHasSpellException(String message){
        super(message);
    }
}
