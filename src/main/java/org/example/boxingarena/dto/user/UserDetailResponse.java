package org.example.boxingarena.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.boxingarena.domain.User;

@Getter
@Setter
@Builder
public class UserDetailResponse {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String userRole;

    public static UserDetailResponse from(User user) {
        return UserDetailResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userRole(user.getRole())
                .build();
    }

}
