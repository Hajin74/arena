package org.example.boxingarena.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TournamentCreateRequest {

    @NotBlank(message = "대회 이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "대회 장소를 입력해주세요.")
    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private short totalRoundsCount;

    private String posterImgUrl;

}
