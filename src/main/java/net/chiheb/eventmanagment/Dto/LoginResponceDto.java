package net.chiheb.eventmanagment.Dto;

import lombok.Data;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.Role;
import net.chiheb.eventmanagment.Entity.User;

import java.util.List;

@Data
public class LoginResponceDto {
    private Long id;
    private String email;
    private String username;
    private Role role;
    private List<Event> eventList;

}
