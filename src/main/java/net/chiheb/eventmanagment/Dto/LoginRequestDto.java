package net.chiheb.eventmanagment.Dto;

import lombok.Data;
import net.chiheb.eventmanagment.Entity.User;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private String type;
}
