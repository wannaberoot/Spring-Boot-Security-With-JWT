package com.example.springbootsecuritywithjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ItemResponse implements Serializable {

    private static final long serialVersionUID = -1390736524807911286L;

    private int id;
    private String itemName;
}
