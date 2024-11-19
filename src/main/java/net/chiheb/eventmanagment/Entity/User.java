package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
@MappedSuperclass
@Data
public abstract class User {
    private String name;
    private String email;
    private String password;

}
