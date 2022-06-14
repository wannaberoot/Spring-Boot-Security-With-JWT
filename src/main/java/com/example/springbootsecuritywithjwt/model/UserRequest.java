package com.example.springbootsecuritywithjwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 8970571491597726507L;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;
}
