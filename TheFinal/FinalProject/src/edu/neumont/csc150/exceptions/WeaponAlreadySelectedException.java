/**
 * @author Pachi
 * @createdOn 3/4/2023 at 1:18 AM
 * @projectName TheFinal
 * @packageName edu.neumont.csc150.exceptions;
 */
package edu.neumont.csc150.exceptions;

public class WeaponAlreadySelectedException extends IllegalStateException{
    public WeaponAlreadySelectedException(String message){
        super(message);
    }
}
