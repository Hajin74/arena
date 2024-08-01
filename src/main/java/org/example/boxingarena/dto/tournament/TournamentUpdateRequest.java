package org.example.boxingarena.dto.tournament;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TournamentUpdateRequest {

    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String posterImgUrl;
    private String intro;

}
