package ua.artcode.exception;

/**
 * Created by dexter on 30.12.15.
 */
public class LoginHasAlreadyUsed extends Exception {
    public LoginHasAlreadyUsed(String message){
        super(message);
    }
}
