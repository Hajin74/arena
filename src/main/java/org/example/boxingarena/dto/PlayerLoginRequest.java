package org.example.boxingarena.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlayerLoginRequest {

    private String email;
    private String password;

}
