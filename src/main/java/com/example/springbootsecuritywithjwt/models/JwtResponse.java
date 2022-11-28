package com.example.springbootsecuritywithjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -657348295592399322L;

    private final String token;
}
