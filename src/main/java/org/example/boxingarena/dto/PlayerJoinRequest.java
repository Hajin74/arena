package org.example.boxingarena.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerJoinRequest {

    private String playerName;
    private String email;
    private String password;
    private String gym;

}
