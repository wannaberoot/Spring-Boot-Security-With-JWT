package com.example.springbootsecuritywithjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -657348295592399322L;

    @Getter
    private final String token;
}
