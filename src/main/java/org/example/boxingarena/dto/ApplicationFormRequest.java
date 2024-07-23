package org.example.boxingarena.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationFormRequest {

    private Long tournamentId;

    // todo: 추후에는 성별, 체급별 정보 등 추가
}
