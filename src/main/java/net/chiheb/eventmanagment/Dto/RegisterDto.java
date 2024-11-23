package net.chiheb.eventmanagment.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.chiheb.eventmanagment.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class RegisterDto {
    private User user;
    private String type;

}
