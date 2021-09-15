package ch.inss.virtualan.idaiserver.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
/* Only needed for user/password auth. */
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
