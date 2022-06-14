package com.example.springbootsecuritywithjwt.controller;

import com.example.springbootsecuritywithjwt.config.JwtTokenUtil;
import com.example.springbootsecuritywithjwt.model.JwtRequest;
import com.example.springbootsecuritywithjwt.model.JwtResponse;
import com.example.springbootsecuritywithjwt.service.authentication.AuthService;
import com.example.springbootsecuritywithjwt.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@Tag(name = "Authentication", description = "API for authentication.")
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Operation(summary = "Authenticate", description = "Authenticate using user credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class))) })
    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authService.authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        } catch (DisabledException disabledException) {
            throw new Exception("USER_DISABLED", disabledException);
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("INVALID_CREDENTIALS", badCredentialsException);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
