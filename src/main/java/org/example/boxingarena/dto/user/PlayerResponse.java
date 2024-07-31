package org.example.boxingarena.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.User;

@Getter
@Setter
@Builder
public class PlayerResponse {

    private Long playerId;
    private String name;
    private String email;

    public static PlayerResponse from(User user) {
        return PlayerResponse.builder()
                .playerId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
