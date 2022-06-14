package com.example.springbootsecuritywithjwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest implements Serializable {

    private static final long serialVersionUID = -3919347817565342894L;

    @Getter
    @Setter
    private String itemName;
}
