package org.example.boxingarena.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequest {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String userRole;

}
