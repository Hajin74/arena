package org.example.boxingarena.dto.application;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationFormRequest {

    @NotNull
    private Long tournamentId;

    @NotNull
    private String playerName;

//    private String sex;
//
//    private int age;

    // todo: 추후에는 경력, 체급별 정보 등 추가
}
