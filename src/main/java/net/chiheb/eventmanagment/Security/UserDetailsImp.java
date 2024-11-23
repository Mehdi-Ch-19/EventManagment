package net.chiheb.eventmanagment.Security;

import net.chiheb.eventmanagment.Entity.Organizator;
import net.chiheb.eventmanagment.Entity.Participant;
import net.chiheb.eventmanagment.Repository.OrganizatorRepository;
import net.chiheb.eventmanagment.Repository.PartcipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImp implements UserDetailsService {

    @Autowired
    public PartcipantRepository partcipantRepository;
    @Autowired
    public OrganizatorRepository organizatorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Participant participant = partcipantRepository.findParticipantByEmail(username);
        if (participant != null) {
            return participant;
        }
        Organizator organizator = organizatorRepository.findOrganizatorByEmail(username);
        if(organizator != null) {
            return organizator;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
