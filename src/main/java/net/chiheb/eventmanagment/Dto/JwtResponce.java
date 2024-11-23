package net.chiheb.eventmanagment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.chiheb.eventmanagment.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class JwtResponce {
    private String token;
    private Long id;
    private String email;
    private String username;
    private Role role;


}
