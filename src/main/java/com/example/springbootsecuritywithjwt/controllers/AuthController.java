package com.example.springbootsecuritywithjwt.controllers;

import com.example.springbootsecuritywithjwt.models.JwtRequest;
import com.example.springbootsecuritywithjwt.models.JwtResponse;
import com.example.springbootsecuritywithjwt.services.authentication.AuthService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Tag(name = "Authentication", description = "API for authentication.")
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Authenticate", description = "Authenticate using user credentials.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class))) })
    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) throws DisabledException, BadCredentialsException {
        final String token = authService.createToken(jwtRequest.getUserName(), jwtRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
