package net.chiheb.eventmanagment.Exeption;

public class AleardyEnrolled extends RuntimeException {
    public AleardyEnrolled(String aleadyEnrollred) {
        super(aleadyEnrollred);
    }
}
