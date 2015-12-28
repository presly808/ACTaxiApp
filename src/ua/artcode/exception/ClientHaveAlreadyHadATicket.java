package ua.artcode.exception;

/**
 * Created by dexter on 28.12.15.
 */
public class ClientHaveAlreadyHadATicket extends Exception{
    public ClientHaveAlreadyHadATicket(String message){
        super(message);
    }
}
