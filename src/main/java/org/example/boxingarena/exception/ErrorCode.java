package org.example.boxingarena.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    PLAYER_NOT_FOUND("선수를 찾을 수 없습니다."),
    PLAYER_ONLY_ALLOWED("선수 본인만 허용되는 작업입니다."),
    PLAYERS_NOT_ENOUGH("선수가 충분하지 않습니다."),
    ORGANIZER_NOT_FOUND("개최자를 찾을 수 없습니다."),
    ORGANIZER_ONLY_ALLOWED("개최자만 허용되는 작업입니다."),
    TOURNAMENT_NOT_FOUND("대회를 찾을 수 없습니다."),
    APPLICATION_NOT_FOUND("신청을 찾을 수 없습니다."),
    APPLICATION_FOR_TOURNAMENT_PERIOD_CLOSED("참여 신청 기간이 아닙니다."),
    MATCH_NOT_FOUND("경기를 찾을 수 없습니다."),
    MATCH_RESULT_NOT_FOUND("경기 결과를 찾을 수 없습니다."),
    ACCESS_NOT_FOUND("Access Token 을 찾을 수 없습니다.");

    private final String errorMessage;

}
