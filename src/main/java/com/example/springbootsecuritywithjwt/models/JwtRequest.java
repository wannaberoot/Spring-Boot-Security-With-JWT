package com.example.springbootsecuritywithjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = -672956924441698582L;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;
}
