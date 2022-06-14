package com.example.springbootsecuritywithjwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;


@AllArgsConstructor
public class ItemResponse implements Serializable {

    private static final long serialVersionUID = -1390736524807911286L;

    @Getter
    private int id;

    @Getter
    private String itemName;
}
