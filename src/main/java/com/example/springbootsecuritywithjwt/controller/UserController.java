package com.example.springbootsecuritywithjwt.controller;

import com.example.springbootsecuritywithjwt.model.UserInfo;
import com.example.springbootsecuritywithjwt.model.UserRequest;
import com.example.springbootsecuritywithjwt.model.UserResponse;
import com.example.springbootsecuritywithjwt.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;


@RestController
@Tag(name = "User", description = "API for user.")
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Operation(summary = "Create User", description = "Create a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))) })
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) throws ValidationException {
        if (userRepository.existsByUserName(userRequest.getUserName())) {
            throw new ValidationException("Username already exist.");
        }

        final String encodedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());
        final UserInfo user = new UserInfo(userRequest.getUserName(), encodedPassword);
        userRepository.save(user);
        return new ResponseEntity<>(new UserResponse(user.getId(), user.getUserName()), HttpStatus.CREATED);
    }
}
