package net.chiheb.eventmanagment.Controller;

import net.chiheb.eventmanagment.Dto.JwtResponce;
import net.chiheb.eventmanagment.Dto.LoginRequestDto;
import net.chiheb.eventmanagment.Dto.LoginResponceDto;
import net.chiheb.eventmanagment.Dto.RegisterDto;
import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Entity.User;
import net.chiheb.eventmanagment.Security.CustomerUserDetails;
import net.chiheb.eventmanagment.Security.jwt.JwtUtils;
import net.chiheb.eventmanagment.Service.OrganizatorService;
import net.chiheb.eventmanagment.Service.PartcipantService;
import net.chiheb.eventmanagment.config.ResponceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    public AuthenticationManager authenticationManager;
    public PartcipantService partcipantService;
    public OrganizatorService organizatorService;
    public JwtUtils jwtUtils;
    public AuthController(AuthenticationManager authenticationManager,
     PartcipantService partcipantService, JwtUtils jwtUtils,
     OrganizatorService organizatorService) {
        this.authenticationManager = authenticationManager;
        this.partcipantService = partcipantService;
        this.organizatorService = organizatorService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){

        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail()
                        ,loginRequestDto.getPassword())
        );
       /* User userDetails = (User) authentication.getPrincipal();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        if(userDetails.getRole().name().equals("ROLE_ORGANIZATOR")) {
            userDetails =  organizatorService.getOrganizatorById(userDetails.getId());
        }
        if (userDetails.getRole().name().equals("ROLE_PARTICIPANT")){
            userDetails =  partcipantService.getParticipantById(userDetails.getId());
        }
        LoginResponceDto loginResponceDto = new LoginResponceDto();
        loginResponceDto.setEmail(userDetails.getEmail());
        loginResponceDto.setRole(userDetails.getRole());
        loginResponceDto.setId(userDetails.getId());
        loginResponceDto.setUsername(userDetails.getUsername());
        loginResponceDto.setId(userDetails.getId());
        return ResponceHandler.generateResponse("login successufuly", HttpStatus.OK,
                loginResponceDto);
                */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        return ResponceHandler.generateResponse("login successufuly", HttpStatus.OK,
                new JwtResponce(jwt,userDetails.getId(),userDetails.getUsername(), userDetails.getEmail(), userDetails.getRole()));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        try {
            if(registerDto.getType().equals("participant")){
                Participant user = new Participant();
                user.setEmail(registerDto.getUser().getEmail());
                user.setPassword(registerDto.getUser().getPassword());
                user.setName(registerDto.getUser().getName());
                user.setAddrese(((Participant)registerDto.getUser()).getAddrese());
                partcipantService.addParticipant(user);
            } else if (registerDto.getType().equals("organizator")) {
                Organizator organizator = (Organizator) registerDto.getUser();
                organizator.setWebsite(((Organizator) registerDto.getUser()).getWebsite());
                organizator.setEmail(registerDto.getUser().getEmail());
                organizator.setName(registerDto.getUser().getName());
                organizator.setPhoneNumber(((Organizator) registerDto.getUser()).getPhoneNumber());
                organizator.setPassword(registerDto.getUser().getPassword());
                organizatorService.createOrganizator(organizator);
            }
            return ResponceHandler.generateResponse("register successufuly ",
                    HttpStatus.CREATED,null);
        }catch (Exception e){
            return ResponceHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,null);
        }

    }
}
