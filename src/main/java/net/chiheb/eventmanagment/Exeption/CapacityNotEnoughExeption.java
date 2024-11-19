package net.chiheb.eventmanagment.Exeption;

public class CapacityNotEnoughExeption extends RuntimeException{
    public CapacityNotEnoughExeption(){
        super("Capacity not enough");
    }
}
