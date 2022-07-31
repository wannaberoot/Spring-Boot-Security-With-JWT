package com.example.springbootsecuritywithjwt.controllers;

import com.example.springbootsecuritywithjwt.models.UserInfo;
import com.example.springbootsecuritywithjwt.models.UserRequest;
import com.example.springbootsecuritywithjwt.models.UserResponse;
import com.example.springbootsecuritywithjwt.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;

@RestController
@Tag(name = "User", description = "API for user.")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create User", description = "Create a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))) })
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) throws ValidationException {
        final UserInfo newUser = userService.createUser(userRequest.getUserName(), userRequest.getPassword());
        return new ResponseEntity<>(new UserResponse(newUser.getId(), newUser.getUserName()), HttpStatus.CREATED);
    }
}
