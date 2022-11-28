package com.example.springbootsecuritywithjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 5368935657326733113L;

    private int id;
    private String userName;
}
