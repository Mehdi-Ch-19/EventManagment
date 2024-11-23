package net.chiheb.eventmanagment.Security;

import net.chiheb.eventmanagment.Entity.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
    User getCurrentAuthenticatedUser();
}
