/**
 * @author Pachi
 * @createdOn 3/7/2023 at 3:01 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.exceptions;
 */
package edu.neumont.csc150.exceptions;

public class NotEnoughMoneyException extends IllegalStateException{
    public NotEnoughMoneyException(String message){
        super(message);
    }
}
