/**
 * @author Pachi
 * @createdOn 3/3/2023 at 8:26 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150;
 */
package edu.neumont.csc150.exceptions;

public class EnemyIsDeadException extends IllegalStateException{
    public EnemyIsDeadException(String message){
        super(message);
    }
}
