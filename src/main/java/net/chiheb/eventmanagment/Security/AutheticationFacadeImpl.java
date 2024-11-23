package net.chiheb.eventmanagment.Security;

import net.chiheb.eventmanagment.Entity.User;
import net.chiheb.eventmanagment.Repository.OrganizatorRepository;
import net.chiheb.eventmanagment.Repository.PartcipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutheticationFacadeImpl implements AuthenticationFacade{
    @Autowired
    public PartcipantRepository partcipantRepository;
    @Autowired
    public OrganizatorRepository organizatorRepository;
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getCurrentAuthenticatedUser() {
        if (!getAuthentication().isAuthenticated()) {
            return null;
        }

        CustomerUserDetails userDetails = (CustomerUserDetails) getAuthentication().getPrincipal();
        if(userDetails.getRole().name().equals("ROLE_ORGANIZATOR")) {
            return organizatorRepository.findOrganizatorByEmail(userDetails.getUsername());
        }
        if (userDetails.getRole().name().equals("ROLE_PARTICIPANT")){
            return partcipantRepository.findParticipantByEmail(userDetails.getUsername());
        }
        return null;
    }
}
