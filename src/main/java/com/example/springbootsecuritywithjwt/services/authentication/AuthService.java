package com.example.springbootsecuritywithjwt.services.authentication;

import com.example.springbootsecuritywithjwt.configs.JwtTokenUtil;
import com.example.springbootsecuritywithjwt.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String createToken(final String userName, final String password) throws DisabledException, BadCredentialsException {
        try {
            authenticate(userName, password);
        } catch (DisabledException exception) {
            throw new DisabledException("User is disabled.");
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Bad credentials.");
        }

        final UserDetails userDetails = userService.loadUserByUsername(userName);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    private void authenticate(final String userName, final String password) throws DisabledException, BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
    }
}
