package net.chiheb.eventmanagment.Exeption;

public class EmailAleadyExists extends RuntimeException{
    public EmailAleadyExists(String emailAleardyExists) {
        super(emailAleardyExists);
    }
}
