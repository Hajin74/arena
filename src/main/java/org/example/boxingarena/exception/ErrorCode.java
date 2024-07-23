package org.example.boxingarena.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PLAYER_NOT_FOUND("선수를 찾을 수 없습니다."),
    ORGANIZER_NOT_FOUND("개최자를 찾을 수 없습니다."),
    TOURNAMENT_NOT_FOUND("대회를 찾을 수 없습니다."),
    MATCH_NOT_FOUND("경기를 찾을 수 없습니다."),
    MATCH_RESULT_NOT_FOUND("경기 결과를 찾을 수 없습니다.");

    private final String errorMessage;

}
