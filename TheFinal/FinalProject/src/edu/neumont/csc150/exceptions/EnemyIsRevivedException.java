/**
 * @author Pachi
 * @createdOn 3/5/2023 at 2:56 PM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.exceptions;
 */
package edu.neumont.csc150.exceptions;

public class EnemyIsRevivedException extends EnemyIsDeadException{
    public EnemyIsRevivedException(String message) {
        super(message);
    }
}
